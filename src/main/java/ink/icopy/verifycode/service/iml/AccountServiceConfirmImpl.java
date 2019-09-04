package ink.icopy.verifycode.service.iml;

import ink.icopy.verifycode.service.IAccountService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/** @author lizhengguang */
public class AccountServiceConfirmImpl implements IAccountService {
  @Resource(name = "jdbcTemplate")
  private JdbcTemplate jdbcTemplate;

  @Transactional
  public void increaseAmount(String accountId, double amount) throws ServiceException {
    this.jdbcTemplate.update(
        "update tb_account set amount = amount + ?, frozen = frozen - ? where acct_id = ?",
        amount,
        amount,
        accountId);
  }
}
