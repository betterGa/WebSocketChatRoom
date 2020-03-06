import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.Test;

import java.sql.*;

public class JDBCDemo1 {
    @Test
    public void test() throws ClassNotFoundException, SQLException {
        //加载驱动
        Class.forName("com.mysql.jdbc.Driver");

        //获取连接
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc?useSSL=false","root","jdpy1229jiajia");

        String username="test1";
        String password="123";
        //执行语句
String sql="select * from user where username=? and password=?";
        PreparedStatement statement=connection.prepareStatement(sql);
statement.setString(1,username);
statement.setString(2,password);
        ResultSet resultSet=statement.executeQuery(sql);
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String username1 = resultSet.getString("username");
            String password1 = resultSet.getNString("password");
            System.out.println("id为" + id + "，用户名" + username1 + ",密码为" + password1);
        }
            //释放资源
            connection.close();
            statement.close();
            resultSet.close();
        }

        @Test
    public void test1() throws SQLException, ClassNotFoundException {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");

            //获取连接
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc?useSSL=false","root","jdpy1229jiajia");

            //执行语句
 String sql="insert into user(username,password) values ('test1','123');" ;
Statement statement=null;
            try {
               statement=connection.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            assert statement != null;
            int resultRows=statement.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
System.out.println(resultRows);
//释放资源
            connection.close();
            statement.close();
        }

    @Test
    public void testInsert()
    {Connection connection=null;
        PreparedStatement statement=null;
        try{
            connection=JDBCUtils.getConnection();
            String sql="insert into user (username,password) values(?,?)";
            statement=connection.prepareStatement(sql);
            statement.setString(1,"java1");
            statement.setString(2,DigestUtils.md5Hex("java1"));
            int influeRow=statement.executeUpdate();

            Assert.assertEquals(1,influeRow);
        }
        catch (SQLException e){}
        finally {
            JDBCUtils.closeResource(connection,statement);
        }}


    @Test
    public void testInsertwizGruid()
    {Connection connection=null;
        PreparedStatement statement=null;
        try{
            connection= JDBCUtilWizDruid.getConnection();
            String sql="insert into user (username,password) values(?,?)";
            statement=connection.prepareStatement(sql);
            statement.setString(1,"java2");
            statement.setString(2,DigestUtils.md5Hex("java2"));
            int influeRow=statement.executeUpdate();

            Assert.assertEquals(1,influeRow);
        }
        catch (SQLException e){}
        finally {
            JDBCUtilWizDruid.closeResource(connection,statement);
        }}





}









