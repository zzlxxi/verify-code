package ink.icopy.verifycode.common.http;

import lombok.Data;

/**
 * @author lizhengguang
 * @date 2019-08-18 21:05
 */
@Data
public final class Result<T> {

  private int status = 1;
  private String errorCode = "";
  private String errorMsg = "";

  private T resultBody;

  public Result() {}

  public Result(T resultBody) {
    this.resultBody = resultBody;
  }
}
