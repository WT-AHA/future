package com.aha.common.spring.tx;


import jakarta.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 编程式事物测试
 * 推荐使用编程式事务的原因
 *   1. 声明式事务有很多情况下会失效
 *   2. 编程式事务更加灵活，可以更加贴合业务，编程式事务最小的单元都是方法级别的，声明式可以自定义，只给自己需要的代码加事务即可
 * 可以从此处入手看 spring tx 的源码
 */
@RestController
@RequestMapping("/tx")
public class ProgramTransactionTest {

    @Resource
    private PlatformTransactionManager transactionManager;
    @Resource
    private JdbcTemplate jdbcTemplate;

    public void transfer (String from, String to, int money) {

        // 默认的事物配置，可以自定义事务的隔离级别，是否只读，传播方式，超时时间
        DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
        // 使用事物管理器来进行事物的管理
        TransactionStatus transactionStatus = transactionManager.getTransaction(defaultTransactionDefinition);
        // 模拟转账
        try {

            // 转账语句
            String moneyFrom = "update account set money = money - ? where username = ?";
            String moneyTo = "update account set money = money + ? where username = ?";
            // 扣钱
            jdbcTemplate.update(moneyFrom, money, from);
            // 抛出异常
            int i = 1/0;
            // 加钱
            jdbcTemplate.update(moneyTo, money, to);

        } catch (Exception exception) {
            exception.printStackTrace();
            // 遇到异常回滚
            transactionManager.rollback(transactionStatus);
        }
        // 正常执行 提交事物
        transactionManager.commit(transactionStatus);

    }

}
