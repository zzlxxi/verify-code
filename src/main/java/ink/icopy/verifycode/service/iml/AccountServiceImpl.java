package ink.icopy.verifycode.service.iml;

import ink.icopy.verifycode.service.IAccountService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/** @author lizhengguang */
public class AccountServiceImpl implements IAccountService {

  @Resource(name = "jdbcTemplate")
  private JdbcTemplate jdbcTemplate;

  @Transactional
  public void increaseAmount(String accountId, double amount) {
    this.jdbcTemplate.update(
        "update tb_account set frozen = frozen + ? where acct_id = ?", amount, accountId);
  }
}
