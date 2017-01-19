package projectMarket.connectionDB;




import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;


public class DatabaseConnection {

    private static String URL = "jdbc:mysql://localhost:3306/project_market";
    private static String userName = "root";
    private static String password = "root";
    private static boolean isInitialized;
    private static DataSource dataSource;
    private DatabaseConnection() {}

    public static Connection getConnection() { //написать отдельный статический метод для тестов и тут делать провеку ,если не тест то иницилизироваь
        try {
            if (!isInitialized) {
                PoolProperties poolProperties = new PoolProperties();
                poolProperties.setUrl(URL);
                poolProperties.setUsername(userName);
                poolProperties.setPassword(password);
                poolProperties.setDriverClassName("com.mysql.jdbc.Driver");
                dataSource = new DataSource(poolProperties);
                isInitialized = true;
            }

            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }




    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
