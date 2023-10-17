pipeline {
    agent {
        kubernetes {
            yaml """
apiVersion: v1
kind: Pod
metadata:
  labels:
    purpose:  app-automation 
spec:
  containers:
  - name: maven 
    image: 'maven:3.8.4-openjdk-11-slim' 
    command:
    - cat
    tty: true
      """
/*
//TODO: Error response from daemon: toomanyrequests: You have reached your pull rate limit. You may increase the limit by authentappating and upgrading: https://www.docker.com/increase-rate-limit
// Trying hardcoded     image: 'maven:3.8.4-openjdk-11-slim'   that matches app as of 4/15/2022.
// It used to be   maven:latest  -- hoping a hardcoded version may cause fewer pulls therefore prevent future problems.
//TODO: Kill process error, may be solved if FORKS <=4 , but just in case, may want to explore distinct settings along
//
            the lines of app:

            containerTemplate(
                    command: 'cat',
                    image: 'maven:3.8.4-openjdk-11-slim',
                    name: 'jdk11',
                    ttyEnabled: true,
                    resourceRequestMemory: '2048',
                    resourceRequestCpu: '1000m',
                    resourceLimitMemory: '4096Mi',
                    resourceLimitCpu: '2000m',
            )
             */
        }
    }
    environment {
        emailTo = "${env.JOB_BASE_NAME.contains('QA') ? 'Sulaimoni@abc.io' : 'abc@abcd.com'}"
        emailToOnFAIL = "${env.JOB_BASE_NAME.contains('QA') ? 'abc@abc.io' : 'Sulaimoni.Shokhzoda@abc.io'}"
    }
    parameters {
        // Reference https://binaries.sonarsource.com/CommercialDistribution/sonarqube-datacenter
        choappe(name: 'ENVIRONMENT', choappes: ['VALAWS', 'DEVSBTEST', 'DEVSB'], description: 'App Environment')
        choappe(name: 'TAGS', choappes: ['smoke', 'regression', 'healthCheck', 'sanityCheck', 'cmmi-rp', 'cmmi-hd', 'cmmi-am', 'cmmi-ac', 'cmmi-lm', 'cmmi-uv', 'cmmi-ua', 'UTIL'], description: 'Tag(s) to run')
        choappe(name: 'BROWSER', choappes: ['chrome','firefox','edge','safari' ], description: 'Web Browser')
        choappe(name: 'VIDEO', choappes: ['false', 'true'], description: 'Selenium Box should record the real-time video')
        choappe(name: 'FORKS', choappes: ['1', '2', '3', '4', '5'], description: 'SureFire ForkCount')
        choappe(name: 'rerunFailingTestsCount', choappes: ['0', '1', '2', '3'], description: 'SureFire rerunFailingTestsCount')
    }
    triggers {
        parameterizedCron(
                "${env.JOB_BASE_NAME.contains('QA') ? '' : '''
TZ=US/Eastern
00 7 * * 1-5 % ENVIRONMENT=VALAWS;TAGS=smoke;VIDEO=false;FORKS=2
30 7 * * 1-5 % ENVIRONMENT=DEVSBTEST;TAGS=smoke;VIDEO=false;FORKS=2
00 8 * * 1-5 % ENVIRONMENT=DEVSB;TAGS=smoke;VIDEO=false;FORKS=2
'''}")
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
                        sortingMethod: 'ALPHABETappAL',
                        classifappations: [
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

                junit keepLongStdio: false, skipPublishingChecks: true, testDataPublishers: [[$class: 'JUnitFlakyTestDataPublisher']], testResults: 'target/surefire-reports/TEST*.xml'
                publishHTML(target: [
                        allowMissing         : false,
                        alwaysLinkToLastBuild: false,
                        includes             : '**/*',
                        keepAll              : true,
                        reportDir            : 'target',
                        reportFiles          : 'index.html',
                        reportName           : "app ${env.JOB_BASE_NAME}: ${params.ENVIRONMENT} - ${params.TAGS} ${currentBuild.currentResult}"
                ])
                script {
                    if (currentBuild.currentResult == 'SUCCESS') {
                        emailext to: env.emailTo, from: 'app-jenkins@email.com', replyTo: 'jenkins@email.com',
                                subject: "app ${env.JOB_BASE_NAME}: ${params.ENVIRONMENT} - ${params.TAGS} ${currentBuild.currentResult}",
                                mimeType: 'text/html', body: '''${SCRIPT, template="groovy-html.template"}'''
                    } else {
                        emailext to: env.emailToOnFAIL, from: 'jenkins@email.com', replyTo: 'jenkins@email.com',
                                subject: "app ${env.JOB_BASE_NAME}: ${params.ENVIRONMENT} - ${params.TAGS} ${currentBuild.currentResult}",
                                attachLog: true, compressLog: true, attachmentsPattern: '**/FAILED/*.png',
                                mimeType: 'text/html', body: '''${SCRIPT, template="groovy-html.template"}'''
                    }
                }
            }
        }
    }
}