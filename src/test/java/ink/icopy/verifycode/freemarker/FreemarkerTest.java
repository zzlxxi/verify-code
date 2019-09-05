package ink.icopy.verifycode.freemarker;

import freemarker.template.Template;
import ink.icopy.verifycode.entity.ColumnClass;
import ink.icopy.verifycode.util.freemarker.FreeMarkerTemplateUtils;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

import java.io.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class FreemarkerTest {
  private final String AUTHOR = "Ay";
  private final String CURRENT_DATE = "2017/05/03";
  private final String tableName = "ay_test";
  private final String packageName = "ink.icopy.verifycode";
  private final String tableAnnotation = "质量问题";
  private final String URL =
      "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf8";
  private final String USER = "root";
  private final String PASSWORD = "";
  private final String DRIVER = "com.mysql.cj.jdbc.Driver";
  private final String diskPath = "/Users/lizhengguang/Desktop/doWork/code";
  private final String changeTableName = replaceUnderLineAndUpperCase(tableName);

  @Test
  public void generate() throws Exception {
    try {
      Connection connection = getConnection();
      DatabaseMetaData databaseMetaData = connection.getMetaData();
      ResultSet resultSet = databaseMetaData.getColumns(null, "%", tableName, "%");
      // 生成Mapper文件
      generateMapperFile(resultSet);
      // 生成Dao文件
      //      generateDaoFile(resultSet);
      // 生成Repository文件
      generateRepositoryFile(resultSet);
      // 生成服务层接口文件
      generateServiceInterfaceFile(resultSet);
      // 生成服务实现层文件
      generateServiceImplFile(resultSet);
      // 生成Controller层文件
      generateControllerFile(resultSet);
      // 生成DTO文件
      generateDTOFile(resultSet);
      // 生成Model文件
      generateModelFile(resultSet);
    } catch (Exception e) {
      throw new RuntimeException(e);
    } finally {

    }
  }

  public Connection getConnection() throws Exception {
    Class.forName(DRIVER);
    Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
    return connection;
  }

  private void generateModelFile(ResultSet resultSet) throws Exception {

    final String suffix = ".java";
    final String path = diskPath + File.separator + changeTableName + suffix;
    final String templateName = "Model.ftl";
    File mapperFile = new File(path);
    List<ColumnClass> columnClassList = new ArrayList<>();
    ColumnClass columnClass = null;
    while (resultSet.next()) {
      // id字段略过
      if (resultSet.getString("COLUMN_NAME").equals("id")) continue;
      columnClass = new ColumnClass();
      // 获取字段名称
      columnClass.setColumnName(resultSet.getString("COLUMN_NAME"));
      // 获取字段类型
      columnClass.setColumnType(resultSet.getString("TYPE_NAME"));
      // 转换字段名称，如 sys_name 变成 SysName
      columnClass.setChangeColumnName(
          replaceUnderLineAndUpperCase(resultSet.getString("COLUMN_NAME")));
      // 字段在数据库的注释
      columnClass.setColumnComment(resultSet.getString("REMARKS"));
      columnClassList.add(columnClass);
    }
    Map<String, Object> dataMap = new HashMap<>();
    dataMap.put("model_column", columnClassList);
    generateFileByTemplate(templateName, mapperFile, dataMap);
  }

  private void generateDTOFile(ResultSet resultSet) throws Exception {
    final String suffix = "DTO.java";
    final String path = diskPath + File.separator + changeTableName + suffix;
    final String templateName = "DTO.ftl";
    File mapperFile = new File(path);
    Map<String, Object> dataMap = new HashMap<>();
    generateFileByTemplate(templateName, mapperFile, dataMap);
  }

  private void generateControllerFile(ResultSet resultSet) throws Exception {
    final String suffix = "Controller.java";
    final String path = diskPath + File.separator + changeTableName + suffix;
    final String templateName = "Controller.ftl";
    File mapperFile = new File(path);
    Map<String, Object> dataMap = new HashMap<>();
    generateFileByTemplate(templateName, mapperFile, dataMap);
  }

  private void generateServiceImplFile(ResultSet resultSet) throws Exception {
    final String suffix = "ServiceImpl.java";
    final String path = diskPath + File.separator + changeTableName + suffix;
    final String templateName = "ServiceImpl.ftl";
    File mapperFile = new File(path);
    Map<String, Object> dataMap = new HashMap<>();
    generateFileByTemplate(templateName, mapperFile, dataMap);
  }

  private void generateServiceInterfaceFile(ResultSet resultSet) throws Exception {
    final String prefix = "I";
    final String suffix = "Service.java";
    final String path = diskPath + prefix + changeTableName + suffix;
    final String templateName = "Service.ftl";
    File mapperFile = new File(path);
    Map<String, Object> dataMap = new HashMap<>();
    generateFileByTemplate(templateName, mapperFile, dataMap);
  }

  private void generateRepositoryFile(ResultSet resultSet) throws Exception {
    final String suffix = "Repository.java";
    final String path = diskPath + File.separator + changeTableName + suffix;
    final String templateName = "Repository.ftl";
    File mapperFile = new File(path);
    Map<String, Object> dataMap = new HashMap<>();
    generateFileByTemplate(templateName, mapperFile, dataMap);
  }

  private void generateDaoFile(ResultSet resultSet) throws Exception {
    final String suffix = "DAO.ftl.java";
    final String path = diskPath + File.separator + changeTableName + suffix;
    final String templateName = "DAO.ftl";
    File mapperFile = new File(path);
    Map<String, Object> dataMap = new HashMap<>();
    generateFileByTemplate(templateName, mapperFile, dataMap);
  }

  private void generateMapperFile(ResultSet resultSet) throws Exception {
    final String suffix = "Mapper.xml";
    final String path = diskPath + File.separator + changeTableName + suffix;
    final String templateName = "Mapper.ftl";
    File mapperFile = new File(path);
    Map<String, Object> dataMap = new HashMap<>();
    generateFileByTemplate(templateName, mapperFile, dataMap);
  }

  private void generateFileByTemplate(
      final String templateName, File file, Map<String, Object> dataMap) throws Exception {
    Template template = FreeMarkerTemplateUtils.getTemplate(templateName);
    FileOutputStream fos = new FileOutputStream(file);
    dataMap.put("table_name_small", tableName);
    dataMap.put("table_name", changeTableName);
    dataMap.put("author", AUTHOR);
    dataMap.put("date", CURRENT_DATE);
    dataMap.put("package_name", packageName);
    dataMap.put("table_annotation", tableAnnotation);
    Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"), 10240);
    template.process(dataMap, out);
  }

  public String replaceUnderLineAndUpperCase(String str) {
    StringBuffer sb = new StringBuffer();
    sb.append(str);
    int count = sb.indexOf("_");
    while (count != 0) {
      int num = sb.indexOf("_", count);
      count = num + 1;
      if (num != -1) {
        char ss = sb.charAt(count);
        char ia = (char) (ss - 32);
        sb.replace(count, count + 1, ia + "");
      }
    }
    String result = sb.toString().replaceAll("_", "");
    return StringUtils.capitalize(result);
  }
}
