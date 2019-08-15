package ink.icopy.verifycode.entity;

import ink.icopy.verifycode.util.excel.annotation.ExcelTitleAnnotation;
import lombok.Data;

/**
 * @author lizhengguang
 * @date 2019-8-15 17:20:34
 */
@Data
public class UserEntity {
    @ExcelTitleAnnotation(value = "id")
    private Long id;
    @ExcelTitleAnnotation(value = "name")
    private String name;
    @ExcelTitleAnnotation(value = "phone")
    private String phone;
    @ExcelTitleAnnotation(value = "address")
    private String address;
}
