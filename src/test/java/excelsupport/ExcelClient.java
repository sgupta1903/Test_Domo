package excelsupport;
/*
Created By: Praveen,Sapna
Date: 02/12/2020
*/
import java.io.*;
import java.util.*;

import config.EnvProperty;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;


public class ExcelClient {

    private EnvProperty envProperty;
    private String section;
    private Map<Integer, ExcelRow> sheet;
    private static XSSFWorkbook book;

    public ExcelClient(EnvProperty envProperty, String section) {
        this.envProperty = envProperty;
        this.section = section;
        try {
            loadSheet(new FileInputStream(getConfigParamValue("path")), getConfigParamValue("sheetName"));
        } catch (FileNotFoundException fnf) {
            Assert.fail("Unable to find the configured excel sheet, path: " + getConfigParamValue("path"));
        }
    }

    public ExcelClient(EnvProperty envProperty, String content, String sheetName) {
        this.envProperty = envProperty;
        try {
            loadSheet(new ByteArrayInputStream(content.getBytes("ISO-8859-1")), sheetName);
        } catch (IOException ioe) {
            Assert.fail("Unable to the content : " + content);
        }
    }

    public ExcelClient(String path, String sheetName) {
        try {
            loadSheet(new FileInputStream(path),sheetName);
        } catch (FileNotFoundException fnf) {
            Assert.fail("Unable to find the configured excel sheet, path: " + path);
        }
    }


    public Object[][] get_all_rows() {
        Object[][] array = new Object[sheet.size()][2];
        int count = 0;
        for(Map.Entry<Integer,ExcelRow> entry : sheet.entrySet()){
            array[count][0] = entry.getKey();
            array[count][1] = entry.getValue();
            count++;
        }
        return array;
    }

    public Object[][] get_all_rows_except_header() {
        return get_multiple_rows(2, sheet.size());  //assuming the header on row 1
    }

    public Map<Integer, ExcelRow> get_all_rows_as_map() {
        return sheet;
    }

    public Object[][] get_single_row(int row) {
        validate_row_no(row);
        Object[][] array = new Object[1][2];
        array[0][0] = row;
        array[0][1] = sheet.get(row);
        return array;
    }

    public ExcelRow get_single_row_as_obj(int row) {
        validate_row_no(row);
        return sheet.get(row);
    }

    public Object[][] get_multiple_rows(int rowBegin, int rowEnd) {
        Object[][] array = new Object[(rowEnd-rowBegin)+1][2];
        int count = 0;
        for(Map.Entry<Integer,ExcelRow> entry : sheet.entrySet()){
            if ((entry.getKey() >= rowBegin) && (entry.getKey() <= rowEnd)) {
                array[count][0] = entry.getKey();
                array[count][1] = entry.getValue();
                count++;
            }
        }
        return array;
    }

    public Map<Integer, ExcelRow> get_multiple_rows_as_map(int rowBegin, int rowEnd) {
        Map<Integer, ExcelRow> map = new HashMap<Integer, ExcelRow>();
        for(Map.Entry<Integer,ExcelRow> entry : sheet.entrySet()){
            if ((entry.getKey() >= rowBegin) && (entry.getKey() <= rowEnd)) {
                map.put(entry.getKey(), entry.getValue());
            }
        }
        return map;
    }

    public int get_total_no_of_rows() {
        return sheet.size();
    }

    private void validate_row_no(int row) {
        if (!sheet.containsKey(row)) {
            Assert.fail("row does not exist in the excel sheet, row no. is: " + row);
        }
    }

    private void loadSheet(InputStream content, String sheetName) {
        Map<Integer, ExcelRow> sheetData = new HashMap<Integer, ExcelRow>();
        String headerArray[] = new String[1];
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(content);
            XSSFSheet sheet = workbook.getSheet(sheetName);

            Assert.assertNotNull(sheet, "Configured excel sheet is not existing, path: " + sheetName);
            int firstRowNumber = sheet.getFirstRowNum();
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                Object[] rowData = new Object[row.getLastCellNum()];
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            rowData[cell.getColumnIndex()] = cell.getNumericCellValue();
                            break;
                        case Cell.CELL_TYPE_STRING:
                            rowData[cell.getColumnIndex()] = cell.getStringCellValue();
                            break;
                        case Cell.CELL_TYPE_BLANK:
                            //rowData[cell.getColumnIndex()] = "BLANK";
                            break;
                        case Cell.CELL_TYPE_BOOLEAN:
                            rowData[cell.getColumnIndex()] = cell.getBooleanCellValue();
                            break;
                    } //end of switch case
                } //loop for cells in a row
                if (row.getRowNum() == firstRowNumber) {
                    headerArray = Arrays.copyOf(rowData, rowData.length, String[].class);
                }
                sheetData.put(row.getRowNum() + 1, new ExcelRow(rowData, headerArray));

            } // loop for rows
        }catch (IOException ioe) {
            Assert.fail("Unable to load the configured excel sheet, path: " + sheetName);
        }

        Assert.assertFalse(sheetData.isEmpty(), "Configured excel sheet is empty, path: " + sheetName);
        this.sheet = sheetData;
    }

    private String getConfigParamValue(String param) {
        return getConfigParamValue(this.section,param);
    }

    private String getConfigParamValue(String section, String param) {
        return this.envProperty.getConfigPropertyValue(section, param);
    }

    /**
     * Read Data from Excel
     *
     * @param filePath Excel File path,  @param sheetName Sheet Name
     */
    public static Object[][] read_excel(String filePath, String sheetName) throws IOException {
        FileInputStream file= new FileInputStream(filePath);
        XSSFWorkbook wb = new XSSFWorkbook(file);
        XSSFSheet sheet = wb.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum();
        int column = sheet.getRow(0).getLastCellNum();
        Object[][] data = new Object[rowCount][column];
        for (int i = 1; i <= rowCount; i++) {
            XSSFRow row = sheet.getRow(i);
            for (int j = 0; j < column; j++) {
                XSSFCell cell = row.getCell(j);
                DataFormatter formatter = new DataFormatter();
                String val = formatter.formatCellValue(cell);
                data[i - 1][j] = val;
            }
        }
        return data;
    }
    /**
     * Write Data To Excel
     *
     * @param data Map
     */
    public static void write_excel(Map data,String filePath) throws IOException
    {
        // valueList to save only Value form Hash Map
        List<String> valueList = new ArrayList<String>(data.values());
        FileInputStream fileInputStream=new FileInputStream(filePath);
        book = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheetToWrite = book.getSheetAt(0);
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        String[] agreementOrg = valueList.get(4).split("-");
        // getRow function used to find Row index from Excel for member
        Row row=sheetToWrite.getRow(find_row(sheetToWrite, agreementOrg[1],agreementOrg[0]));
        // writting values to cell
        for (int x=0;x<valueList.size();x++)
        {
            Cell cell = row.createCell(x+2);
            cell.setCellValue(valueList.get(x));
        }
        book.write(fileOutputStream)  ;
        fileOutputStream.close();
        fileInputStream.close();
        book = new XSSFWorkbook(new FileInputStream(filePath));

    }

    private static int find_row(XSSFSheet sheet, String cellContent,String clubNumber) {
        for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            if (row != null) {
                Cell cell = row.getCell(1);
                if (cell != null && new DataFormatter().formatCellValue(row.getCell(0)).equals(clubNumber)) {
                    if (new DataFormatter().formatCellValue(cell).equals(cellContent)) {
                        return row.getRowNum();
                    }
                }
            }
        }
        return 0;
    }

    /**
     * Write Excel Data To Clipboard
     *
     * @param filePath Excel File path
     * @return
     */
    public static String write_clipboard(String filePath) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        XSSFWorkbook book = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = book.getSheetAt(0);
        int rowCount = sheet.getLastRowNum();
        String value = "";
        for (int i = 0; i <= rowCount; i++) {
            XSSFRow row = sheet.getRow(i);
            for (int j = 0; j < row.getLastCellNum(); j++) {
                XSSFCell cell = row.getCell(j);
                DataFormatter formatter = new DataFormatter();
                value = value + formatter.formatCellValue(cell) + ",";
            }
            value =value+"\n";
        }
        fileInputStream.close();
        return value;

    }

}

