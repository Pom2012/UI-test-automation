pipeline {
    agent {
        kubernetes {
            yaml """
apiVersion: v1
kind: Pod
metadata:
  labels:
    purpose:  App-automation 
spec:
  containers:
  - name: maven 
    image: 'maven:3.8.4-openjdk-11-slim' 
    command:
    - cat
    tty: true
      """
/*
//TODO: Error response from daemon: toomanyrequests: You have reached your pull rate limit. You may increase the limit by authentAppating and upgrading: https://www.docker.com/increase-rate-limit
// Trying hardcoded     image: 'maven:3.8.4-openjdk-11-slim'   that matches App as of 4/15/2022.
// It used to be   maven:latest  -- hoping a hardcoded version may cause fewer pulls therefore prevent future problems.
//TODO: Kill process error, may be solved if FORKS <=4 , but just in case, may want to explore distinct settings along
// the lines of App:
            containerTemplate(
                    command: 'cat',
                    image: 'maven:3.8.4-openjdk-11-slim',
                    name: 'jdk11',
                    ttyEnabled: true,
                    resourceRequestMemory: '2048',
                    resourceRequestCpu: '1000m',
                    resourceLimitMemory: '4096Mi',
                    resourceLimitCpu: '2000m',
            ),
 */
        }
    }
    environment {
        emailTo = "${env.JOB_BASE_NAME.contains('QA') ? 'Sulaimoni.Shokhzoda@asdf.io' : 'fa@asf.io'}"
        emailToOnFAIL = "${env.JOB_BASE_NAME.contains('QA') ? 'Sulaimoni.Shokhzoda@asf.io' : 'asf@asf.io'}"
    }
    parameters {
        // Reference https://binaries.sonarsource.com/CommercialDistribution/sonarqube-datacenter
        choAppe(name: 'ENVIRONMENT', choAppes: ['VALAWS', 'DEVSBTEST'], description: 'App Environment')
//WIP: csv list should work ~ need multi-select?
        choAppe(name: 'TAGS', choAppes: ['healthCheck', 'sanityCheck', 'amHealthCheck'], description: 'Tag(s) to run')
//        choAppe(name: 'BROWSER', choAppes: ['chrome','firefox' ], description: 'Web Browser')
        choAppe(name: 'VIDEO', choAppes: ['false', 'true'], description: 'Selenium Box should record the real-time video')
        choAppe(name: 'FORKS', choAppes: ['1', '2'], description: 'SureFire ForkCount')
        choAppe(name: 'rerunFailingTestsCount', choAppes: ['0', '1'], description: 'SureFire rerunFailingTestsCount')
    }
    triggers {
        parameterizedCron(
                "${env.JOB_BASE_NAME.contains('QA') ? '' : '''
TZ=US/Eastern
00 8 * * 1-5 % ENVIRONMENT=VALAWS;TAGS=healthCheck;VIDEO=false;FORKS=1
30 8 * * 1-5 % ENVIRONMENT=DEVSBTEST;TAGS=healthCheck;VIDEO=false;FORKS=1
'''}")
//  00 8-19 * * 1-5 % ENVIRONMENT=DEVSBTEST;TAGS=@amHealthCheck;VIDEO=false;FORKS=1
    }
    stages {
        stage('CheckOut') {
            steps {
                deleteDir()
                checkout scm
            }
        }
        stage('Build') {
            steps {
                container(name: 'maven') {
                    sh 'mvn -P CloudBees --settings=settings-mag.xml -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true clean install -e -X -Denvironment=$ENVIRONMENT "-Dcucumber.filter.tags=@$TAGS" "-Dcucumber.options=--tags @$TAGS" -De34.video=$VIDEO -DforkCount=$FORKS -DrerunFailingTestsCount=$rerunFailingTestsCount -DvarSrc=CloudBees_Pipeline'
                }
            }
        }
        stage('Post-Deploy') {
            steps {
                echo "Publishing Reports..."
                cucumber buildStatus: currentBuild.currentResult,
                        reportTitle: env.JOB_BASE_NAME,
                        fileIncludePattern: 'target/cucumber-parallel/*.json',
                        trendsLimit: 10,
                        sortingMethod: 'ALPHABETAppAL',
                        classifAppations: [
                                [
                                        'key'  : 'ENVIRONMENT',
                                        'value': params.ENVIRONMENT
                                ],
                                [
                                        'key'  : 'TAGS',
                                        'value': params.TAGS
                                ],
                                [
                                        'key'  : 'VIDEO was captured?',
                                        'value': params.VIDEO
                                ],
                                [
                                        'key'  : 'Forks used for Parallel Testing',
                                        'value': params.FORKS
                                ],
                                [
                                        'key'  : 'SureFire rerunFailingTestsCount',
                                        'value': params.rerunFailingTestsCount
                                ]
                        ]

                junit keepLongStdio: true, skipPublishingChecks: true, testDataPublishers: [[$class: 'JUnitFlakyTestDataPublisher']], testResults: 'target/surefire-reports/TEST*.xml'

                publishHTML(target: [
                        allowMissing         : false,
                        alwaysLinkToLastBuild: false,
                        keepAll              : true,
                        reportDir            : 'target',
                        reportFiles          : 'index.html',
                        reportName           : "App ${env.JOB_BASE_NAME}: ${params.ENVIRONMENT} - ${params.TAGS} ${currentBuild.currentResult}"
                ])

                script {
                    if (currentBuild.currentResult == 'SUCCESS') {
                        emailext to: env.emailTo, from: 'jenkins@d.com', replyTo: 'jenkins@d.com',
                                subject: "App ${env.JOB_BASE_NAME}: ${params.ENVIRONMENT} - ${params.TAGS} ${currentBuild.currentResult}",
                                mimeType: 'text/html', body: '''${SCRIPT, template="groovy-html.template"}'''
                    } else {
                        emailext to: env.emailToOnFAIL, from: 'jenkins@d.com', replyTo: 'jenkins@d.com',
                                subject: "App ${env.JOB_BASE_NAME}: ${params.ENVIRONMENT} - ${params.TAGS} ${currentBuild.currentResult}",
                                attachLog: true, compressLog: true, attachmentsPattern: '**/FAILED/*.png',
                                mimeType: 'text/html', body: '''${SCRIPT, template="groovy-html.template"}'''
                    }
                }
            }
        }
    }
}
