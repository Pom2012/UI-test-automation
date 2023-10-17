package com.model.utility;

import com.model.base.BasePage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileActions {
    //ENH: Ideally, account for existing files (by timestamp?) before Download button is pressed, and look only at a "more recent" file.
// That would help against an existing file that has the same pattern (local /Downloads/), -or-
// Parallel tests which run at the same time and download similarly named files.
    public static String getDownloadedFileNameByDefaultFileName(String defaultFileName, WebDriver driver) throws Exception {
        if (BasePage.prop.getProperty("selenium.server").equalsIgnoreCase("BOX"))
            return getFileByDriverSessionIdAndDefaultFileName(defaultFileName, driver);
        int retries = 10; // x wait 2 seconds
        while (retries > 0) {
            File[] files = new File(System.getProperty("user.home") + "/Downloads/").listFiles();
            if (files == null || files.length == 0) {
                Thread.sleep(2000);
                retries--;
                continue;
            }
            Arrays.sort(files, new Comparator<Object>() {
                public int compare(Object o1, Object o2) {
                    return compare((File) o1, (File) o2);
                }

                private int compare(File f1, File f2) {
                    long result = f2.lastModified() - f1.lastModified();
                    if (result > 0) return 1;
                    else if (result < 0) return -1;
                    else return 0;
                }
            });

            for (File file : files) {
                String fileName = file.toString().replace("\\", "/");
                fileName = fileName.substring((fileName.lastIndexOf("/") + 1));
                String[] fileNameParts = fileName.split("\\.");
                String[] defaultFileNameParts = defaultFileName.split("\\.");
                if (fileNameParts[0].startsWith(defaultFileNameParts[0])
                        && fileNameParts[1].equals(defaultFileNameParts[1])) {
                    return fileName;
                }
            }
            Thread.sleep(2000);
            retries--;
        }
        Assert.fail("FAIL: No file matching default expectation was found: " + defaultFileName);
        return "FAIL: No file matching default expectation was found: " + defaultFileName;
    }

    public static String getFileByDriverSessionIdAndDefaultFileName(String defaultFileName, WebDriver driver) throws Exception {
        int retries = 15; // x wait 2 seconds, or only 200ms if API is not HTTP Status 200
        URL url = new URL(BasePage.prop.getProperty("BOX_API_DOWNLOAD_FILE") + ((RemoteWebDriver) driver).getSessionId());
        while (retries > 0) {
            try {
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();
                if (conn.getResponseCode() != 200) {
                    if (retries == 1)
                        throw new Exception("ERROR: HttpResponseCode: " + conn.getResponseCode() + ": " + url);
                    System.err.println("Interim error: HTTP Status: " + conn.getResponseCode() + " : url:" + url);
                    Thread.sleep(200);
                    retries--;
                }
            } catch (Exception e) {
                if (retries == 1) throw e;
            }
            Scanner scanner = new Scanner(url.openStream());
            StringBuilder inline = new StringBuilder();
            while (scanner.hasNext()) inline.append(scanner.nextLine());
            scanner.close();

            List<String> allMatches = new ArrayList<>();
            Matcher m = Pattern.compile("\"url\":\"[^\"]{5,150}\"")
                    .matcher(inline);
            System.out.println("Matcher = " + m);
            while (m.find()) {
                String fileWPath = m.group().replaceAll("\"url\":\"", "").replace("\"", "");
                allMatches.add(fileWPath);
                System.out.println("fileWPath = " + fileWPath);
            }

            for (String fileName : allMatches) {
                fileName = fileName.substring((fileName.lastIndexOf("/") + 1));
                String[] fileNameParts = fileName.split("\\.");
                String[] defaultFileNameParts = defaultFileName.split("\\.");
                if (fileNameParts[0].startsWith(defaultFileNameParts[0])
                        && fileNameParts[1].equals(defaultFileNameParts[1])) {
                    return fileName;
                }
            }
            Thread.sleep(2000);
            retries--;
        }
        Assert.fail("FAIL: No file matching default expectation was found: " + defaultFileName);
        return "FAIL: No file matching default expectation was found: " + defaultFileName;
    }

    public static int getRecordsCountInCSV(String csvFileName) throws IOException {
        int lineNumberCount = 0;
        try {
            if (!csvFileName.isEmpty() || csvFileName != null) {
                String filePath = System.getProperty("user.home") + "/Downloads/" + csvFileName;
                System.out.println(filePath);
                File file = new File(filePath);
                if (file.exists()) {
                    System.out.println("File found :" + csvFileName);
                    FileReader fr = new FileReader(file);
                    LineNumberReader linenumberreader = new LineNumberReader(fr);
                    while (linenumberreader.readLine() != null) {
                        lineNumberCount++;
                    }
                    //To remove the header
                    lineNumberCount = lineNumberCount - 1;
                    System.out.println("Total number of lines found in the downloaded csv file : " + (lineNumberCount));
                    linenumberreader.close();
                } else {
                    System.out.println("File does not exists");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineNumberCount;
    }

    public static void detecteAndDeletedownloadedFile(String csvFileName) {
        try {
            if (!csvFileName.isEmpty() || csvFileName != null) {
                String filePath = System.getProperty("user.home") + "/Downloads/" + csvFileName;
                System.out.println(filePath);
                File file = new File(filePath);
                if (file.exists()) {
                    System.out.println("File found :" + csvFileName);
                    file.delete();
                    System.out.println("File deleted :" + csvFileName);
                } else {
                    System.out.println("File does not exists");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
