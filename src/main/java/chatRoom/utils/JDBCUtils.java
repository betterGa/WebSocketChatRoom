import chatRoom.utils.CommUtil;

import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
   private static String driverName;
   private static String url;
   private static String username;
    private static String password;

    static
    {Properties properties=CommUtil.loadProperties("db.properties");

        driverName=properties.getProperty("drivername");
        url=properties.getProperty("url");
        username=properties.getProperty("username");
        password=properties.getProperty("password");


        //接下来，加载驱动
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
          //  e.printStackTrace();
        System.out.println("加载数据库驱动出错");
        }
    }


//不同用户获取的连接是不同的，所以写成static 方法即可
    public static Connection getConnection()
    {

        try {
            return DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
         //   e.printStackTrace();
            System.err.println("获取数据库连接出错");
        }

return null;
    }



    //关闭数据库资源
    //方法的重载

    public static void closeResource(Connection connection, Statement statement)
    {

        if(connection!=null)
        {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(statement!=null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void closeResources(Connection connection,Statement statement,ResultSet resultSet)
    {

        //调用上面的两个参数的方法
        closeResource(connection,statement);
if(resultSet!=null){
        try {
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }}
    }



}
