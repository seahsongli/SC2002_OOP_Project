import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

    public static Object[][] readExcelData(String excelFilePath) {
        try (FileInputStream fileInputStream = new FileInputStream(new File(excelFilePath));
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {
    
            Sheet sheet = workbook.getSheetAt(0);
            int rows = sheet.getLastRowNum(); // Exclude the header row
            int columns = sheet.getRow(0).getLastCellNum();
    
            Object[][] data = new Object[rows][columns];
    
            // Start reading from the second row (index 1)
            for (int i = 1; i <= rows; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < columns; j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        switch (cell.getCellType()) {
                            case STRING:
                                data[i - 1][j] = cell.getStringCellValue();
                                break;
                            case NUMERIC:
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    data[i - 1][j] = cell.getDateCellValue().toString();
                                } else {
                                    data[i - 1][j] = cell.getNumericCellValue();
                                }
                                break;
                            case BOOLEAN:
                                data[i - 1][j] = cell.getBooleanCellValue();
                                break;
                            default:
                                data[i - 1][j] = "";
                        }
                    } else {
                        data[i - 1][j] = ""; // Handle blank cells
                    }
                }
            }
            return data;
    
        } catch (IOException e) {
            e.printStackTrace();
            return new Object[0][0]; // Return empty array if an error occurs
        }
    }    
}
