package model_Runner;
// This class is now only used indirectly,
// from the generated JUnit Run files from the .vm template

import com.model.base.BasePage;

import java.io.*;
import java.util.Properties;

public class model_TestRunner {
    public static String TR_Signature;

    static synchronized void setupBeforeClass() throws Exception {
        if (TR_Signature == null) initTestRun();
        System.out.println("--------------------------------------------------");
        BasePage.log.info("-- START: @BeforeClass ----------------------------");
        System.out.println("--------------------------------------------------");
    }

    static void tearDownAfterClass() {
        System.out.println("--------------------------------------------------");
        BasePage.log.info("-- END: @AfterClass ----------------------------");
        System.out.println("--------------------------------------------------");
    }

    public static void initTestRun() throws Exception {
//NOTE: reclaim stdout and stderr from any plugin or logger; currently is dictated by TestCaseFinished
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.setErr(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        String filePath = System.getProperty("user.dir")
                + File.separator
                + String.join(File.separator,
                "target", "test-classes", "envs", "config.properties");
        BasePage.log.info(" >> Properties file path: " + filePath);
        FileInputStream input = new FileInputStream(filePath);
        BasePage.prop.load(input);
        input.close();
        /* pom file, can be used to pass command-line settings.
         * Read file, then use to create-or-update to main prop from getPropEnv
         */
        PropertiesReader reader = new PropertiesReader("properties-from-pom.properties");
        Properties pomProps = reader.getFileProperties();
        for (String key : pomProps.stringPropertyNames()) {
            BasePage.log.debug(key + ": " + pomProps.getProperty(key));
            BasePage.prop.setProperty(key, pomProps.getProperty(key));
        }
        //TODO: REVIEW: Review if this is applicable going forward, or if it should be put into Excel
        BasePage.environment = BasePage.prop.getProperty("environment").toUpperCase();
        if (BasePage.environment.endsWith("SB")) {
            BasePage.environment = "DEVSB";
            BasePage.env = BasePage.environment.substring(0, BasePage.environment.length() - 2);
            BasePage.env2 = "SB";
        } else if (BasePage.environment.endsWith("TEST")) {
            BasePage.environment = "DEVSBTEST";
            BasePage.env = BasePage.environment.substring(0, BasePage.environment.length() - "SBTEST".length());
            BasePage.env2 = "SBTEST";
        } else if (BasePage.environment.equals("IMPL") || BasePage.environment.endsWith("AWS")) {
            BasePage.environment = "VALAWS";
            BasePage.env = BasePage.environment.substring(0, BasePage.environment.length() - "AWS".length());
            BasePage.env2 = "AWS";
        } else {
            BasePage.env = BasePage.environment.substring(0, BasePage.environment.length() - 1);
            BasePage.env2 = BasePage.environment.substring(BasePage.environment.length() - 1);
        }
        if (!BasePage.REQUESTS_XLSX.contains(BasePage.prop.getProperty("externalFilesWrite"))) {
            BasePage.REQUESTS_XLSX = BasePage.prop.getProperty("externalFilesWrite") + BasePage.REQUESTS_XLSX;
        }
    }

    private static class PropertiesReader {
        private final Properties properties;

        private PropertiesReader(String propertyFileName) throws IOException {
            InputStream is = this.getClass().getClassLoader()
                    .getResourceAsStream(propertyFileName);
            this.properties = new Properties();
            this.properties.load(is);
        }

        public Properties getFileProperties() {
            return properties;
        }

        public String getProperty(String propertyName) {
            return this.properties.getProperty(propertyName);
        }
    }
}
