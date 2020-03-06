import chatRoom.utils.CommUtil;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.alibaba.druid.support.spring.stat.annotation.Stat;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtilWizDruid {
private static DataSource dateSource;

static {
    Properties properties = CommUtil.loadProperties("database.properties");

    try {
        dateSource = DruidDataSourceFactory.createDataSource(properties);
    } catch (Exception e) {
        //  e.printStackTrace();

        System.err.println("获取数据源失败");
    }
}
    public static DruidPooledConnection getConnection()
    {
        try {
            return (DruidPooledConnection) dateSource.getConnection();
        } catch (SQLException e) {
           // e.printStackTrace();
            System.err.println("连接数据库失败");
        }

return null;
    }

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
        if(statement!=null)
        {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void closeResource(Connection connection, Statement statement, ResultSet resultSet)
    {closeResource(connection,statement);
    if(resultSet!=null) {
        try {
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }

}




