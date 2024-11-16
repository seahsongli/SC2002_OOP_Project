import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import patient.Patient;
import hospitalManagement.Staff;

public class ExcelWriter {

    public static void writeExcelData(String filePath, List<?> dataList) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Data");

            // If the list is empty, no data to write
            if (dataList.isEmpty()) return;

            // Create the header row at the first row
            Row headerRow = sheet.createRow(0);
            if (dataList.get(0) instanceof Staff) {
                headerRow.createCell(0).setCellValue("Username");
                headerRow.createCell(1).setCellValue("Password");
                headerRow.createCell(2).setCellValue("Staff ID");
                headerRow.createCell(3).setCellValue("Name");
                headerRow.createCell(4).setCellValue("Role");
                headerRow.createCell(5).setCellValue("Gender");
                headerRow.createCell(6).setCellValue("Age");
            } else if (dataList.get(0) instanceof Patient) {
                headerRow.createCell(0).setCellValue("Username");
                headerRow.createCell(1).setCellValue("Password");
                headerRow.createCell(2).setCellValue("Patient ID");
                headerRow.createCell(3).setCellValue("Name");
                headerRow.createCell(4).setCellValue("DOB");
                headerRow.createCell(5).setCellValue("Gender");
                headerRow.createCell(6).setCellValue("Blood Type");
                headerRow.createCell(7).setCellValue("Email");
                headerRow.createCell(8).setCellValue("Phone Number");
            }

            // Start writing the data from the second row (index 1)
            int rowNum = 1;
            for (Object obj : dataList) {
                Row row = sheet.createRow(rowNum++);
                
                if (obj instanceof Staff) {
                    Staff staff = (Staff) obj;
                    row.createCell(0).setCellValue(staff.getName());
                    row.createCell(1).setCellValue(staff.getPassword());
                    row.createCell(2).setCellValue(staff.getStaffId());
                    row.createCell(3).setCellValue(staff.getName());
                    row.createCell(4).setCellValue(staff.getRole());
                    row.createCell(5).setCellValue(staff.getGender());
                    row.createCell(6).setCellValue(staff.getAge());
                } else if (obj instanceof Patient) {
                    Patient patient = (Patient) obj;
                    row.createCell(0).setCellValue(patient.getName());
                    row.createCell(1).setCellValue(patient.getPassword());
                    row.createCell(2).setCellValue(patient.getHospitalId());
                    row.createCell(3).setCellValue(patient.getName());
                    row.createCell(4).setCellValue(patient.getDob());
                    row.createCell(5).setCellValue(patient.getGender());
                    row.createCell(6).setCellValue(patient.getBloodGroup());
                    row.createCell(7).setCellValue(patient.getEmail());
                    row.createCell(8).setCellValue(patient.getContactNumber());
                }
            }

            // Write the data to the file
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }

            System.out.println("Data saved to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
