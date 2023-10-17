package com.model.utility;

import com.model.base.BasePage;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

//TODO: DATA: Review uses of these methods for:
// Hard-Coded data or Row# in Java.
//  - in Java, if the row, or column (such as column = env2), would recommend a more descriptive column name
//  - is Column = Env (Envs) used to find the row# where = value; would  loop -> return/use   row = appData.("Environment").equals(environment)
// Passing Request ID or other data via write-then-read from Excel or other file.
// - especially when run in CB/Jenkins FilePathForRead + "Requests/" and FilePathForWrite + "Requests/" are not reliably the same
// - when run in parallel, the "last row" is not going to relate to the same Test Suite / Feature File:
public class ExcelFile extends BasePage {
    private static final String ExcelPathRead = BasePage.prop.getProperty("externalFilesRead");
    private static final String ExcelPathWrite = BasePage.prop.getProperty("externalFilesWrite");

    //Create an array with the data in the same order in which you expect to be filled in excel file
    public static void writeDataToExcel(String fileName, String sheetName, String text4Row0, String text4Row1, String text4Row2) throws IOException {
        //Create an object of current class
        String[] valueToWrite = {text4Row0, text4Row1, text4Row2};
        ExcelFile textValue = new ExcelFile();
        //Write the file using file name, sheet name and the data to be filled
        textValue.writeToExcelFile(fileName, sheetName, valueToWrite);
    }

    public static void writeReqWithStsToExc(String fileName, String sheetName, String userID, String reqID, String reqStatus, String appID, String date) throws IOException {
        //Create an object of current class
        String[] valueToWrite = {userID, reqID, reqStatus, appID, date};
        ExcelFile textValue = new ExcelFile();
        //Write the file using file name, sheet name and the data to be filled
        writeTheReqToExcFile(fileName, sheetName, valueToWrite);
        System.out.println("Writing the values to an excel file");
    }

    public static void writeReqWithStsToExc(String fileName, String sheetName, String userID, String reqID, String reqStatus, String appID, String date, String en) throws IOException {
        //Create an object of current class
        String[] valueToWrite = {userID, reqID, reqStatus, appID, date, en};
        ExcelFile textValue = new ExcelFile();
        //Write the file using file name, sheet name and the data to be filled
        writeTheReqToExcFile(fileName, sheetName, valueToWrite);
        System.out.println("Writing the values to an excel file");
    }

    public static void writeAppDataToExc(String fileName, String sheetName, String creationDate, String appName, String appAcroName,
                                         String appBOUserID, String environment, String appStatus, String multRoles, String fetchLablAttr) throws IOException {
        //Create an object of current class
        String[] valueToWrite = {creationDate, appName, appAcroName, appBOUserID, environment, appStatus, multRoles, fetchLablAttr};
        ExcelFile textValue = new ExcelFile();
        //Write the file using file name, sheet name and the data to be filled
        textValue.newAppExcFile(fileName, sheetName, valueToWrite);
        System.out.println("Writing the values to an excel file");
    }

    public static void writeRequestDataToExc(String fileName, String sheetName,
                                             String userID, String appName, String roleName,String requestID, String requestDate, String reqStatus) throws IOException {
        String[] valueToWrite = {userID, appName, roleName, requestID, requestDate, reqStatus};
        ExcelFile textValue = new ExcelFile();
        //Write the file using file name, sheet name and the data to be filled
        textValue.newAppExcFile(fileName, sheetName, valueToWrite);
    }

    public static void writeUVRevSts(String fileName, String sheetName, String status, String userID, String reviewSts,
                                     String reviewDate, String actPracUser, String pastUser) throws IOException {
        //Create an object of current class
        String[] valueToWrite = {status, userID, reviewSts, reviewDate, actPracUser, pastUser};
        ExcelFile textValue = new ExcelFile();
        //Write the file using file name, sheet name and the data to be filled
        writeTheReqToExcFile(fileName, sheetName, valueToWrite);
        System.out.println("Writing the values to an excel file");
    }

    public static void writeDataToExcel(String fileName, String sheetName, String text4Row0, String text4Row1, String text4Row2, String text4Row3, String text4Row4) throws IOException {
        //Create an object of current class
        String[] valueToWrite = {text4Row0, text4Row1, text4Row2, text4Row3,text4Row4};
        ExcelFile textValue = new ExcelFile();
        //Write the file using file name, sheet name and the data to be filled
        textValue.writeAListData(fileName, sheetName, valueToWrite);
    }

    void writeToExcelFile(String fileName, String sheetName, String[] dataToWrite) throws IOException {
        //Create an object of File class to open xlsx file
        File file = new File(ExcelPathWrite + "TestData2/" + fileName);
        if (!file.exists()) {
            FileUtils.copyFile(new File(ExcelPathRead + "TestData2/" + fileName), file);
        }
        //Create an object of FileInputStream class to read excel file
        FileInputStream inputStream = new FileInputStream(file);
        Workbook workbook = null;
        //Find the file extension by splitting  file name in substring and getting only extension name
        String fileExtensionName = fileName.substring(fileName.indexOf("."));
        //Check condition if the file is xlsx file
        if (fileExtensionName.equals(".xlsx")) {
            //If it is xlsx file then create object of XSSFWorkbook class
            workbook = new XSSFWorkbook(inputStream);
        }
        //Check condition if the file is xls file
        else if (fileExtensionName.equals(".xls")) {
            //If it is xls file then create object of XSSFWorkbook class
            workbook = new HSSFWorkbook(inputStream);
        }
        //Read excel sheet by sheet name
        Sheet sheet = workbook.getSheet(sheetName);
        //Get the current count of rows in excel file
        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        //Get the first row from the sheet
        Row row = sheet.getRow(0);
        //Create a new row and append it at last of sheet
        Row newRow = sheet.createRow(rowCount + 1);
        //Create a new row and append it at last of sheet
        for (int j = 0; j < row.getLastCellNum(); j++) {
            //Fill data in row
            Cell cell = newRow.createCell(j);
            cell.setCellValue(dataToWrite[j]);
        }
        //Close input stream
        inputStream.close();
        //Create an object of FileOutputStream class to create write data in excel file
        FileOutputStream outputStream = new FileOutputStream(file);
        //write data in the excel file
        workbook.write(outputStream);
        //close output stream
        outputStream.close();
    }

    static void writeTheReqToExcFile(String fileName, String sheetName, String[] dataToWrite) throws IOException {
//        File file = new File(ExcelPathRead + "Requests/" + fileName);
        File file = new File(ExcelPathRead + "Requests/" + fileName);
        System.out.println("file path = " + file);
        if (!file.exists()) {
            FileUtils.copyFile(new File(ExcelPathRead + "Requests/" + fileName), file);
        }
        FileInputStream inputStream = new FileInputStream(file);
        Workbook workbook = null;
        String fileExtensionName = fileName.substring(fileName.indexOf("."));
        if (fileExtensionName.equals(".xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (fileExtensionName.equals(".xls")) {
            workbook = new HSSFWorkbook(inputStream);
        }
        Sheet sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        Row row = sheet.getRow(0);
        Row newRow = sheet.createRow(rowCount + 1);
        for (int j = 0; j < row.getLastCellNum(); j++) {
            Cell cell = newRow.createCell(j);
            cell.setCellValue(dataToWrite[j]);
        }
        inputStream.close();
        FileOutputStream outputStream = new FileOutputStream(file);
        workbook.write(outputStream);
        outputStream.close();
    }

    void newAppExcFile(String fileName, String sheetName, String[] dataToWrite) throws IOException {
        File file = new File(ExcelPathWrite + fileName);
        if (!file.exists()) {
            FileUtils.copyFile(new File(ExcelPathRead + fileName), file);
        }
        //Create an object of FileInputStream class to read excel file
        FileInputStream inputStream = new FileInputStream(file);
        Workbook workbook = null;
        //Find the file extension by splitting  file name in substring and getting only extension name
        String fileExtensionName = fileName.substring(fileName.indexOf("."));
        //Check condition if the file is xlsx file
        if (fileExtensionName.equals(".xlsx")) {
            //If it is xlsx file then create object of XSSFWorkbook class
            workbook = new XSSFWorkbook(inputStream);
        }
        //Check condition if the file is xls file
        else if (fileExtensionName.equals(".xls")) {
            //If it is xls file then create object of XSSFWorkbook class
            workbook = new HSSFWorkbook(inputStream);
        }
        //Read excel sheet by sheet name
        Sheet sheet = workbook.getSheet(sheetName);
        //Get the current count of rows in excel file
        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        //Get the first row from the sheet
        Row row = sheet.getRow(0);
        //Create a new row and append it at last of sheet
        Row newRow = sheet.createRow(rowCount + 1);
        //Create a new row and append it at last of sheet
        for (int j = 0; j < row.getLastCellNum(); j++) {
            //Fill data in row
            Cell cell = newRow.createCell(j);
            cell.setCellValue(dataToWrite[j]);
        }
        //Close input stream
        inputStream.close();
        //Create an object of FileOutputStream class to create write data in excel file
        FileOutputStream outputStream = new FileOutputStream(file);
        //write data in the excel file
        workbook.write(outputStream);
        //close output stream
        outputStream.close();
    }

    void writeAListData(String fileName, String sheetName, String[] dataToWrite) throws IOException {
        File file = new File(ExcelPathWrite + fileName);
        if (!file.exists()) {
            FileUtils.copyFile(new File(ExcelPathRead + fileName), file);
        }
        FileInputStream inputStream = new FileInputStream(file);
        Workbook workbook = null;
        String fileExtensionName = fileName.substring(fileName.indexOf("."));
        if (fileExtensionName.equals(".xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (fileExtensionName.equals(".xls")) {
            workbook = new HSSFWorkbook(inputStream);
        }
        Sheet sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        Row row = sheet.getRow(0);
        Row newRow = sheet.createRow(rowCount + 1);
        for (int j = 0; j < row.getLastCellNum(); j++) {
            Cell cell = newRow.createCell(j);
            cell.setCellValue(dataToWrite[j]);
        }
        inputStream.close();
        FileOutputStream outputStream = new FileOutputStream(file);
        workbook.write(outputStream);
        outputStream.close();
    }

    public synchronized static File createOutputFileIfNeeded(String fileName) {
        File file = new File(ExcelPathWrite + fileName);
        if (file.exists()) return file;
        Exception potentialException = null;
        //NOTE: Defensive code(?) that forks do not overwrite each other's creating the file.  Should be a better way to do this
        int retries = 6;
        while (retries > 0) {
            try {
                if(!file.exists()) FileUtils.copyFile(new File(ExcelPathRead + fileName), file);
            } catch (Exception e) {
                potentialException = e;
            }
            if (file.exists()) return file;
            wait(new Random().nextInt(5000));
            retries--;
        }
        //NOTE: One last try. Hopefully will never reach here.
        file = new File(ExcelPathWrite + fileName);
        if (file.exists()) return file;
        Assert.fail("FAIL: Could not write an output file: " + ExcelPathWrite + fileName + "\nException: " + potentialException);
        return null;
    }

    //TODO: rewrite using the POI methods:
    // https://poi.apache.org/apidocs/dev/org/apache/poi/poifs/filesystem/POIFSFileSystem.html
    public synchronized static Sheet createOrGetSheet(File file, String sheetName) throws IOException {
        FileInputStream inputStream  = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = null;
        Exception potentialException = null;
        int retries = 6;
        while (retries > 0) {
            try {
                sheet = workbook.getSheet(sheetName);
                if (sheet == null) {
                    workbook.cloneSheet(workbook.getSheetIndex("Template"));
                    workbook.setSheetName(workbook.getSheetIndex("Template (2)"), sheetName);
                    System.out.println("EXCEL: Created Sheet: " + sheetName);
                } else break;
            } catch (Exception e) {
                potentialException = e;
            }
            finally {
                if (inputStream != null) inputStream.close();
            }
            wait(new Random().nextInt(2500 - 500 + 100));
            retries--;
        }
        if (sheet == null) Assert.fail("FAIL: Could not get Tabs: " + sheetName + "\nException: " + potentialException);
        return sheet;
    }

    //ENH: Would be useful to not open and close connection, etc ~ reusable READ state, etc ~ for better parallel performance.
    //ENH: Needs a Template page; would be nice to make row 1 headers by Hash Keys, though that would require ordering them in a meaningful way
    //ENH: Combine the extra columns into just rowData, not an extra array
    public synchronized static void writeToExcelByFileNameSheetAndHashMap(String fileName, String sheetName, HashMap<String, String> orgRowData, String[] dataToWrite) throws IOException {
        File file = createOutputFileIfNeeded(fileName);
        Sheet sheet = null;
        int retries = 6;
        while (retries > 0) {
            try {
                sheet = createOrGetSheet(file, sheetName);
            } catch (Exception ignored) {}
            if (sheet != null) break;
            wait(new Random().nextInt(2500 - 500 + 100));
            retries--;
        }
        if (sheet == null)  sheet = createOrGetSheet(file, sheetName);
        Row Header = sheet.getRow(0);
        List<String> ColumnNames = new ArrayList<>();
        int lastCell = 0;
        for (int i = 0; i < Header.getLastCellNum(); i++) {
            String cellValue = Header.getCell(i).toString();
            //NOTE: Purposely put a barrier between INPUT-driven columns and OUTPUT/Result columns ~ increment lastCell even on a blank
            lastCell++;
            if (cellValue == null || cellValue.equals("")) break;
            ColumnNames.add(Header.getCell(i).toString());
        }
        Row newRow = sheet.createRow(sheet.getLastRowNum() + 1);
        for (String key : orgRowData.keySet()) {
            if (!ColumnNames.contains(key)) {
                Header.createCell(lastCell++).setCellValue(key);
            }
            newRow.createCell(ColumnNames.indexOf(key))
                  .setCellValue(orgRowData.get(key));
        }
        //ENH: Integrate this extra to create column headers in some way.
        for (int j = 0; j < dataToWrite.length; j++) {
            newRow.createCell(lastCell + j).setCellValue(dataToWrite[j]);
        }
        FileOutputStream outputStream = new FileOutputStream(file);
        sheet.getWorkbook().write(outputStream);
        outputStream.close();
    }

     void writeTempPwdToExcFile(String fileName, String sheetName, String[] dataToWrite) throws IOException {
         File file = new File("src\\test\\resources\\externFiles\\" + fileName);
         System.out.println("file = " + file.exists());
         if (!file.exists()) {
             FileUtils.copyFile(new File("target/test-classes/externFiles/" + fileName), file);
         }
        FileInputStream inputStream = new FileInputStream(file);
        Workbook workbook = null;
        String fileExtensionName = fileName.substring(fileName.indexOf("."));
        if (fileExtensionName.equals(".xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (fileExtensionName.equals(".xls")) {
            workbook = new HSSFWorkbook(inputStream);
        }
        Sheet sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        Row row = sheet.getRow(0);
        Row newRow = sheet.createRow(rowCount + 1);
        for (int j = 0; j < row.getLastCellNum(); j++) {
            Cell cell = newRow.createCell(j);
            cell.setCellValue(dataToWrite[j]);
        }
        inputStream.close();
        FileOutputStream outputStream = new FileOutputStream(file);
        workbook.write(outputStream);
        outputStream.close();
    }

    public static void writeTempPwdUIDataToExcel(String fileName, String sheetName, String tempPwd, String userID, String password) throws IOException {
        //Create an object of current class
        String[] valueToWrite = {tempPwd, userID, password};
        ExcelFile textValue = new ExcelFile();
        //Write the file using file name, sheet name and the data to be filled
        textValue.writeTempPwdToExcFile(fileName, sheetName, valueToWrite);
    }

    public static void writePwdUIDataToExcel(String fileName, String sheetName, String userID, String password) throws IOException {
        //Create an object of current class
        String[] valueToWrite = {userID, password};
        ExcelFile textValue = new ExcelFile();
        //Write the file using file name, sheet name and the data to be filled
        textValue.writeTempPwdToExcFile(fileName, sheetName, valueToWrite);
    }
}
