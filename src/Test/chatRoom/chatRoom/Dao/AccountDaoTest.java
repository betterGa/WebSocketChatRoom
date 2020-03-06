package chatRoom.Dao;

import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class AccountDaoTest {


    //用户注册测试
    @Test
    public void userRegister() throws SQLException {
        entity.user user1=new entity.user();
        user1.setUsername("xiaoJia");
        user1.setPassword("123");
        boolean isSuccess=new AccountDao().userRegister(user1);
        Assert.assertEquals(true,isSuccess);
    }

    @Test
    public void getUserInfo() {
    }


    //用户登录测试
    @Test
    public void userLogin() {
        entity.user user1=new AccountDao().userLogin("xiaoJia","123");
        System.out.println(user1);
Assert.assertNotNull(user1);


    }
}