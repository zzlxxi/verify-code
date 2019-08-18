package ink.icopy.verifycode.util.excel;

import ink.icopy.verifycode.util.excel.annotation.ExcelTitleAnnotation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author lizhengguang
 * @date 2019-8-15 18:09:47
 */
public class ExcelUtils {

  private static Logger logger = LoggerFactory.getLogger(ExcelUtils.class);

  /**
   * Create HSSFWorkbook
   *
   * @param object
   * @return
   */
  public static Workbook HSSFWorkbook(Object object) {
    List<?> list = (List<?>) object;
    if (list == null || list.size() < 1) {
      return null;
    }

    Workbook wb = new HSSFWorkbook();
    Sheet sheet = wb.createSheet();
    Row row = sheet.createRow(0);

    final Field[] declaredFields = list.get(0).getClass().getDeclaredFields();
    for (int i = 0; i < declaredFields.length; i++) {
      /*
      ExcelTitleAnnotation annotation = declaredFields[i].getAnnotation(ExcelTitleAnnotation.class);
       */
      row.createCell(i).setCellValue(declaredFields[i].getName());
      sheet.setColumnWidth((short) (i + 1), (short) ((50 * 8) / ((double) 1 / 20)));
    }

    for (int i = 0; i < list.size(); i++) {
      row = sheet.createRow(i + 1);
      Object ob = list.get(i);
      Class<?> clazz = ob.getClass();
      Field[] fields = clazz.getDeclaredFields();
      for (int j = 0; j < fields.length; j++) {
        try {
          fields[j].setAccessible(true);
          row.createCell(j).setCellValue(String.valueOf(fields[j].get(ob)));
        } catch (IllegalAccessException e) {
          logger.error(e.toString());
        }
      }
    }
    return wb;
  }
}
