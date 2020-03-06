package chatRoom.Service;

import chatRoom.Dao.AccountDao;
import entity.user;

import javax.servlet.annotation.WebServlet;
import java.sql.SQLException;

public class AccountService {
    private AccountDao accountDao=new AccountDao();

    //用户注册
    public boolean userLogin(String username,String password)
    {
        entity.user user1=null;
        user1=accountDao.userLogin(username,password);
        if(user1==null) return false;
        return true;
    }


    //用户登录
    public boolean userResgister(String username,String password) throws SQLException {
        entity.user user1=new user();
        user1.setUsername(username);
        user1.setPassword(password);
        return accountDao.userRegister(user1);

    }

}
