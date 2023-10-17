package com.model.utility;

import com.model.base.BasePage;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

//TODO: DATA: Review uses of these methods for:
// Hard-Coded data or Row# in Java.
// Passing Request ID or other data via write-then-read from Excel or other file.
// - especially when run in CB/Jenkins FilePathForRead + "Requests/" and FilePathForWrite + "Requests/" are not reliably the same
// - when run in parallel, the "last row" is not going to relate to the same Test Suite / Feature File:
public class DataHelper extends Model_CommonFunctions{
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    public static String dValue = dateFormat.format(date);
    private static SimpleDateFormat dateFormat2 = new SimpleDateFormat("HHmmss");
    private static String time = dateFormat2.format(date);
    public static DataFormatter formatter = new DataFormatter();
    private static final String FilePathForRead = BasePage.prop.getProperty("externalFilesRead");
    private static final String FilePathFromTarget = BasePage.prop.getProperty("externalFilesWrite");

    public synchronized static List<HashMap<String, String>> data(String fileName, String sheetName) {
        List<HashMap<String, String>> mydata = new ArrayList<>();
          try {
            FileInputStream fs = new FileInputStream(FilePathForRead + fileName);
            XSSFWorkbook workbook = new XSSFWorkbook(fs);
            XSSFSheet sheet = workbook.getSheet(sheetName);
            Row HeaderRow = sheet.getRow(0);
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row currentRow = sheet.getRow(i);
                HashMap<String, String> currentHash = new HashMap<>();
                for (int j = 0; j < currentRow.getPhysicalNumberOfCells(); j++) {
                    Cell currentCell = currentRow.getCell(j);
                    if (currentCell == null) {
                        currentHash.put(HeaderRow.getCell(j).getStringCellValue(), "");
                    } else {
                        String value = formatter.formatCellValue(currentCell);
                        if (HeaderRow.getCell(j) != null)
                            currentHash.put(HeaderRow.getCell(j).getStringCellValue(),
                                    StringUtils.trim(StringUtils.normalizeSpace((value))));
                    }

                }
                mydata.add(currentHash);
            }
            BasePage.log.info("\nExcel File: " + fileName + " : Sheet Name: " + sheetName +
                    "\nTotal Rows: " + sheet.getPhysicalNumberOfRows());
            fs.close();

        } catch (Exception e) {
            Assert.fail("Test FAILED: Cannot Read Excel File: " + fileName + " : Sheet Name: " + sheetName + " : " + e.getMessage());
        }
        return mydata;
    }

    public static String readDataFromExcel(int rowNumb, String colName, String fileName, String sheetName) {
        return DataHelper.data(fileName + ".xlsx", sheetName).get(rowNumb - 1).get(colName);
    }

    public static String getAppNameFromExcFile(String filename, String sheetName, String IcEnv) throws IOException {
        String value = null;
        String file = FilePathForRead + filename + ".xlsx";
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook wbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = wbook.getSheet(sheetName);
        DataFormatter formatter = new DataFormatter();
        for ( Row row : sheet ) {
            if ((formatter.formatCellValue(row.getCell(5))).equalsIgnoreCase("Enabled")
                    &&
                    formatter.formatCellValue(row.getCell(4)).equalsIgnoreCase(IcEnv)
            ) {
                System.out.println("\nApplication name: " + formatter.formatCellValue(row.getCell(1)));
                System.out.println("\nApplication acronym name: " + formatter.formatCellValue(row.getCell(2)));
                System.out.println("\nApplication Status: " + formatter.formatCellValue(row.getCell(5)));
                row.getCell(2);
                value = formatter.formatCellValue(row.getCell(2));
            }
        }
        fis.close();
        FileOutputStream outputStream = new FileOutputStream(file);
        wbook.write(outputStream);
        outputStream.close();
        return value;
    }

    public static String readUVRepData(String filename, String sheetName, String addValue) throws IOException {
        String value = null;
        String file = FilePathForRead + "Requests/" + filename + ".xlsx";
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook wbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = wbook.getSheet(sheetName);
        DataFormatter formatter = new DataFormatter();
        for ( Row row : sheet ) {
            if (formatter.formatCellValue(row.getCell(4)).equalsIgnoreCase("" + dValue + "")
                    &&
                    formatter.formatCellValue(row.getCell(0)).equalsIgnoreCase("" + addValue + "")
            ) {
                row.getCell(4);
                value = formatter.formatCellValue(row.getCell(1));
            }
        }
        fis.close();
        FileOutputStream outputStream = new FileOutputStream(file);
        wbook.write(outputStream);
        outputStream.close();
        return value;
    }

    public static String getListData(String filename, String sheetName, String lsType, String envValue, String statusValue) throws IOException {
        String value = null;
        String file = FilePathForRead + filename + ".xlsx";
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook wbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = wbook.getSheet(sheetName);
        DataFormatter formatter = new DataFormatter();
        for ( Row row : sheet ) {
            if (formatter.formatCellValue(row.getCell(1)).equalsIgnoreCase("" + lsType + "")
                    &&
                    formatter.formatCellValue(row.getCell(3)).equalsIgnoreCase("" + envValue + "")
                    &&
                    formatter.formatCellValue(row.getCell(4)).equalsIgnoreCase("" + statusValue + "")
            ) {
                row.getCell(4);
                value = formatter.formatCellValue(row.getCell(2));
            }
        }
        fis.close();
        FileOutputStream outputStream = new FileOutputStream(file);
        wbook.write(outputStream);
        outputStream.close();
        return value;
    }

    public static void setListData(String filename, String sheetName, String lsType, String lsName, String envValue, String statusValue) throws IOException {
        String file = FilePathForRead + filename + ".xlsx";
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet(sheetName);
        DataFormatter formatter = new DataFormatter();
        for ( Row row : sheet ) {
            if (formatter.formatCellValue(row.getCell(1)).equalsIgnoreCase("" + lsType + "")
                    &&
                    formatter.formatCellValue(row.getCell(3)).equalsIgnoreCase("" + envValue + "")
                    &&
                    formatter.formatCellValue(row.getCell(4)).equalsIgnoreCase("" + statusValue + "")
            ) {
                System.out.println("old list value: " + row.getCell(2));
                row.getCell(2).setCellValue(lsName);
                System.out.println("new list  after: " + row.getCell(2));
            }
        }
        fis.close();
        FileOutputStream outputStream = new FileOutputStream(file);
        wb.write(outputStream);
        outputStream.close();
    }

    public static String readUVRepData2(String filename, String sheetName, String userID, String envInfo) throws IOException {
        String value = null;
        String file = FilePathForRead + "Requests/" + filename + ".xlsx";
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook wbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = wbook.getSheet(sheetName);
        DataFormatter formatter = new DataFormatter();
        for ( Row row : sheet ) {
            if (formatter.formatCellValue(row.getCell(4)).equalsIgnoreCase("" + dValue + "")
                    &&
                    formatter.formatCellValue(row.getCell(0)).equalsIgnoreCase("" + userID + "")
                    &&
                    formatter.formatCellValue(row.getCell(5)).equalsIgnoreCase("" + envInfo + "")
            ) {
                row.getCell(1);
                value = formatter.formatCellValue(row.getCell(1));
            }
        }
        fis.close();
        FileOutputStream outputStream = new FileOutputStream(file);
        wbook.write(outputStream);
        outputStream.close();
        return value;
    }

    public static String getCsvValue(String csvFile, int rowNum, int colNum) throws Exception {
        if (!csvFile.contains(BasePage.prop.getProperty("externalFilesRead")))
            csvFile = BasePage.prop.getProperty("externalFilesRead") + csvFile;
        String delimiter = ",";
        String value;
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(delimiter);
                records.add(Arrays.asList(values));
            }
        }
        value = records.get(rowNum).get(colNum);
        return value.trim();
    }

    public static String readInfoIconData(String filename, String sheetName, String page, String refText) throws IOException {
        String value = null;
        String file = FilePathForRead + filename + ".xlsx";
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook wbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = wbook.getSheet(sheetName);
        DataFormatter formatter = new DataFormatter();
        for (Row row : sheet) {
            if (formatter.formatCellValue(row.getCell(0)).equalsIgnoreCase("" + page + "")
                    &&
                    formatter.formatCellValue(row.getCell(1)).equalsIgnoreCase("" + refText + "")
            ) {
                row.getCell(2);
                value = formatter.formatCellValue(row.getCell(2));
            }
        }
        fis.close();
        FileOutputStream outputStream = new FileOutputStream(file);
        wbook.write(outputStream);
        outputStream.close();
        return value;
    }

    public static String uvRowVal(int val) throws Exception {
        String value = null;
        String file = FilePathForRead + "newApps.xlsx";
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook wbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = wbook.getSheet("ReviewBtn");
        DataFormatter formatter = new DataFormatter();
        for (Row row : sheet) {
            if (formatter.formatCellValue(row.getCell(0)).equalsIgnoreCase(BasePage.env2)
             ) {
                row.getCell(val);
                value = formatter.formatCellValue(row.getCell(val));
            }
        }
        fis.close();
        FileOutputStream outputStream = new FileOutputStream(file);
        wbook.write(outputStream);
        outputStream.close();
        return value;
    }

    public synchronized static List<HashMap<String, String>> getDataFromTargetFolder(String fileName, String sheetName) {
        List<HashMap<String, String>> mydata = new ArrayList<>();
        try {
            FileInputStream fs = new FileInputStream(FilePathFromTarget + fileName);
            XSSFWorkbook workbook = new XSSFWorkbook(fs);
            XSSFSheet sheet = workbook.getSheet(sheetName);
            Row HeaderRow = sheet.getRow(0);
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row currentRow = sheet.getRow(i);
                HashMap<String, String> currentHash = new HashMap<>();
                for (int j = 0; j < currentRow.getPhysicalNumberOfCells(); j++) {
                    Cell currentCell = currentRow.getCell(j);
                    if (currentCell == null) {
                        currentHash.put(HeaderRow.getCell(j).getStringCellValue(), "");
                    } else {
                        String value = formatter.formatCellValue(currentCell);
                        if (HeaderRow.getCell(j) != null)
                            currentHash.put(HeaderRow.getCell(j).getStringCellValue(),
                                    StringUtils.trim(StringUtils.normalizeSpace((value))));
                    }

                }
                mydata.add(currentHash);
            }
            BasePage.log.info("\nExcel File: " + fileName + " : Sheet Name: " + sheetName +
                    "\nTotal Rows: " + sheet.getPhysicalNumberOfRows());
            fs.close();

        } catch (Exception e) {
            Assert.fail("Test FAILED: Cannot Read Excel File: " + fileName + " : Sheet Name: " + sheetName + " : " + e.getMessage());
        }
        return mydata;
    }



}
