package chatRoom.Dao;


import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;

public class AccountDao extends BaseDao
    {
        //用户注册：insert
//传入 user 对象，作为元组插入到数据库中

        public boolean userRegister(entity.user user1) throws SQLException {
            //注册成功与否的标志，如果没有报错再改为 true
            boolean issuccess=false;
         String username=user1.getUsername();
         String password=user1.getPassword();
         Connection connection=null;
         PreparedStatement statement=null;


         connection=getConnection();
         String sql="insert into user(username,password) values (?,?)";

         //还可获取自增ID
         statement=connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            try {
                statement.setString(1,username);
                statement.setString(2,DigestUtils.md5Hex(password));
                issuccess=(statement.executeUpdate()==1);
            } catch (Exception e) {
               //e.printStackTrace();
                System.err.println("用户注册失败`");
            }
            finally {
                closeResource(connection,statement);
            }
return issuccess;
        }



        //把resultSet数据表信息转换成entity实体中的User类。
        // 怎么不用Gson???

        public entity.user getUserInfo(ResultSet resultSet) throws SQLException
        {
            entity.user user1=new entity.user();
            user1.setId(resultSet.getInt("id"));
            user1.setUsername(resultSet.getString("username"));
            user1.setPassword(resultSet.getString("password"));
            return user1;}







//用户登录：根据输入的用户名和密码进行查询 select

public entity.user userLogin(String username,String password)
{Connection connection=null;
    PreparedStatement statement=null;
    ResultSet resultSet=null;
    entity.user user1=null;
connection=getConnection();
String sql="select * from user where username=? and password=?";
    try {
        statement=connection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2,DigestUtils.md5Hex(password));
    resultSet=statement.executeQuery();
    if(resultSet.next())
    {
        user1=getUserInfo(resultSet);


    }


    } catch (SQLException e) {
        //e.printStackTrace();
        System.err.println("查询用户信息出错");
    }
finally {
        closeResource(connection,statement,resultSet);

}

return user1;

    }

}
