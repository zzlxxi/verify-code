package ink.icopy.verifycode.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.apache.poi.ss.usermodel.CellType.*;


@SpringBootTest
public class ExcelTest {

    private final static String XLS = ".xls";
    private final static String XLSX = ".xlsx";
    private final static String FOLDER_NAME = "D:" + File.separator + "excel-test/";

    private static Logger logger = LoggerFactory.getLogger(ExcelTest.class);

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

    @Test
    public void CreateDateCell() throws IOException {
        Workbook wb = new HSSFWorkbook();
        CreationHelper creationHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("new sheet");
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue(new Date());

        final CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("m/d/yy h:mm"));
        cell = row.createCell(1);
        cell.setCellValue(new Date());
        cell.setCellStyle(cellStyle);
        cell = row.createCell(2);
        cell.setCellValue(Calendar.getInstance());
        cell.setCellStyle(cellStyle);
        FileOutputStream outputStream = new FileOutputStream(FOLDER_NAME + "newDateCell" + XLS);

        row = sheet.createRow(2);
        row.createCell(0).setCellValue(1.1);
        row.createCell(1).setCellValue(new Date());
        row.createCell(2).setCellValue(Calendar.getInstance());
        row.createCell(3).setCellValue("a string");
        row.createCell(4).setCellValue(true);
        row.createCell(5).setCellType(ERROR);

        wb.write(outputStream);
    }

    @Test
    public void ReadWorkBookByFile() throws IOException {
        final String fileName = FOLDER_NAME + "newDateCell" + XLS;
        Workbook wb = WorkbookFactory.create(new File(fileName));
        Sheet newSheet = wb.getSheet("new sheet");
        Row row = newSheet.getRow(0);
        Cell cell = row.getCell(1);
        Date dateCellValue = cell.getDateCellValue();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm");
        logger.error(sdf.format(dateCellValue));
    }

    @Test
    public void ReadWorkBookByInputStream() throws IOException {
        String fileName = FOLDER_NAME + "newDateCell" + XLS;
        Workbook wb = WorkbookFactory.create(new FileInputStream(fileName));
        Sheet newSheet = wb.getSheet("new sheet");
        Row row = newSheet.getRow(0);
        Cell cell = row.getCell(1);
        Date dateCellValue = cell.getDateCellValue();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm");
        logger.error(sdf.format(dateCellValue));
    }

    @Test
    public void ReadWorkBookByInputStreamLoopCell() throws IOException {
        DataFormatter formatter = new DataFormatter();
        String fileName = FOLDER_NAME + "newDateCell" + XLS;
        Workbook wb = WorkbookFactory.create(new FileInputStream(fileName));
        Sheet sheet1 = wb.getSheetAt(0);
        for (Row row : sheet1) {
            for (Cell cell : row) {
                CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
                System.out.print(cellRef.formatAsString());
                System.out.print(" - ");

                // get the text that appears in the cell by getting the cell value and applying any data formats (Date, 0.00, 1.23e9, $1.23, etc)
                String text = formatter.formatCellValue(cell);
                System.out.println(text);

                // Alternatively, get the value and format it yourself
                switch (cell.getCellType()) {
                    case STRING:
                        System.out.println(cell.getRichStringCellValue().getString());
                        break;
                    case NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            System.out.println(cell.getDateCellValue());
                        } else {
                            System.out.println(cell.getNumericCellValue());
                        }
                        break;
                    case BOOLEAN:
                        System.out.println(cell.getBooleanCellValue());
                        break;
                    case FORMULA:
                        System.out.println(cell.getCellFormula());
                        break;
                    case BLANK:
                    default:
                        System.out.println();
                }
            }
        }
    }

    @Test
    public void HSSFWorkbookWithAlignment() throws IOException {
        Workbook wb = new XSSFWorkbook(); //or new HSSFWorkbook();

        Sheet sheet = wb.createSheet();
        Row row = sheet.createRow(2);
        row.setHeightInPoints(30);

        createCell(wb, row, 0, HorizontalAlignment.CENTER, VerticalAlignment.BOTTOM);
        createCell(wb, row, 1, HorizontalAlignment.CENTER_SELECTION, VerticalAlignment.BOTTOM);
        createCell(wb, row, 2, HorizontalAlignment.FILL, VerticalAlignment.CENTER);
        createCell(wb, row, 3, HorizontalAlignment.GENERAL, VerticalAlignment.CENTER);
        createCell(wb, row, 4, HorizontalAlignment.JUSTIFY, VerticalAlignment.JUSTIFY);
        createCell(wb, row, 5, HorizontalAlignment.LEFT, VerticalAlignment.TOP);
        createCell(wb, row, 6, HorizontalAlignment.RIGHT, VerticalAlignment.TOP);

        // Write the output to a file
        final String name = FOLDER_NAME + "xssf-align" + XLSX;
        try (OutputStream fileOut = new FileOutputStream(name)) {
            wb.write(fileOut);
        }

        wb.close();
    }

    @Test
    public void HSSFWorkbookWithBorders() {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");

        // Create a row and put some cells in it. Rows are 0 based.
        Row row = sheet.createRow(1);

        // Create a cell and put a value in it.
        Cell cell = row.createCell(1);
        cell.setCellValue(4);

        // Style the cell with borders all around.
        CellStyle style = wb.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.GREEN.getIndex());
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLUE.getIndex());
        style.setBorderTop(BorderStyle.MEDIUM_DASHED);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        cell.setCellStyle(style);

        final String name = FOLDER_NAME + "workbook" + XLS;
        // Write the output to a file
        try (OutputStream fileOut = new FileOutputStream(name)) {
            wb.write(fileOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * createCell
     *
     * @param wb
     * @param row
     * @param column
     * @param alignment
     * @param verticalAlignment
     */
    public void createCell(Workbook wb, Row row, int column, HorizontalAlignment alignment, VerticalAlignment verticalAlignment) {
        Cell cell = row.createCell(column);
        cell.setCellValue("Align It");
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(alignment);
        cellStyle.setVerticalAlignment(verticalAlignment);
        cell.setCellStyle(cellStyle);
    }

}
