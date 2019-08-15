package ink.icopy.verifycode.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@SpringBootTest
public class ExcelTest {

    private final static String XLS = ".xls";
    private final static String XLSX = ".xlsx";
    private final static String FOLDER_NAME = "D:" + File.separator + "excel-test/";

    @Test
    public void CreateHSSFWorkbook() throws IOException {
        Workbook wb = new HSSFWorkbook();
        OutputStream fileOutputStream = new FileOutputStream(FOLDER_NAME + "workbook" + XLS);
        wb.write(fileOutputStream);
    }

    @Test
    public void CreateXSSFWorkbook() throws IOException {
        Workbook wb = new XSSFWorkbook();

        OutputStream fileOutputStream = new FileOutputStream(FOLDER_NAME + "workbook" + XLSX);
        wb.write(fileOutputStream);
    }

    @Test
    public void CreateNewSheet() throws IOException {

        Workbook wb = new HSSFWorkbook();
        OutputStream fileOutputStream = new FileOutputStream(FOLDER_NAME + "newWorkBook" + XLS);

        Sheet sheet1 = wb.createSheet("new sheet");
        Sheet sheet2 = wb.createSheet("second sheet");
        String safeSheetName = WorkbookUtil.createSafeSheetName("[O'Brien's sales*?]");
        Sheet sheet3 = wb.createSheet(safeSheetName);

        wb.write(fileOutputStream);
    }

    @Test
    public void CreateCell() throws IOException {
        Workbook wb = new HSSFWorkbook();
        CreationHelper creationHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("new sheet");
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue(1);

        row.createCell(1).setCellValue(1.2);
        row.createCell(2).setCellValue(creationHelper.createRichTextString("This is a String"));
        row.createCell(3).setCellValue(true);
        FileOutputStream outputStream = new FileOutputStream(FOLDER_NAME + "newCell" + XLS);
        wb.write(outputStream);
    }

}
