package ink.icopy.verifycode.service.iml;

import ink.icopy.verifycode.service.IAccountService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/** @author lizhengguang */
public class AccountServiceImpl implements IAccountService {

  @Resource(name = "jdbcTemplate")
  private JdbcTemplate jdbcTemplate;

  @Transactional
  public void increaseAmount(String accountId, double amount) throws ServiceException {
    this.jdbcTemplate.update(
        "update tb_account set frozen = frozen + ? where acct_id = ?", amount, accountId);
  }
}
