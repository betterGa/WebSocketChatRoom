

import chatRoom.utils.CommUtil;
import entity.user;
import org.junit.Test;

import java.sql.*;
import java.util.Properties;

import static org.junit.Assert.*;

public class CommUtilTest {

    @Test
    public void loadProperties() {
        String fileName = "db.properties";
        Properties properties = CommUtil.loadProperties(fileName);
        System.out.println(properties);
    }

    @Test
    public void test1() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
//当使用JDBCUtils这个类时,就会调用它的静态代码块，就先进行了加载驱动
            String sql = "select * from user";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                System.out.println("id为" + id + "，用户名为" + username + ",密码为" + password);
            }
        } catch (SQLException e) {
        } finally {


            JDBCUtils.closeResources(connection, statement, resultSet);
        }

    }


    @Test
    public void test2() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select * from user where id = ? and username = ?";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, 1);
            statement.setString(2, "ohh");
            //替代第几个占位符，从1开始

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                System.out.println("id为" + id + "，用户名为" + username + ",密码为" + password);
            }

        } catch (SQLException e) {
        } finally {
            JDBCUtils.closeResources(connection, statement, resultSet);

        }
    }


    @Test
    public void gsaonTest1() {
        user user1 = new user();
        user1.setId(12);
        user1.setUsername("xiaoJia");
        user1.setPassword("eat");
        String Jsonstr = CommUtil.objectToJson(user1);
        System.out.println(Jsonstr);
    }

    @Test
    public void gsonTest2() {
        String jsonstr = "{\"id\":12,\"username\":\"xiaoJia\",\"password\":\"eat\"}";
        user user2 = (user) CommUtil.jsonToObject(jsonstr, user.class);
        System.out.println(user2);

    }

}



