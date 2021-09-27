package curso;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private ConnectionFactory(){
        throw new UnsupportedOperationException();
    }

    public static Connection getConnection(){
        Connection connection = null;

        try(InputStream input = ConnectionFactory.class.getClassLoader().getResourceAsStream("connection.yml")) {

            Properties prop = new Properties();
            prop.load(input);

            String driver = prop.getProperty("jdbc.driver");
            String dataBaseAddress = prop.getProperty("address");
            String dataBaseName = prop.getProperty("name");
            String user = prop.getProperty("login");
            String password = prop.getProperty("password");

            String connectionUrl = "jdbc:"+driver+"://"+dataBaseAddress+"/"+dataBaseName+"?useTimezone=true&serverTimezone=UTC";

            try {
                connection = DriverManager.getConnection(connectionUrl,user,password);
            } catch (SQLException e){
                System.out.println("Falha ao tentar criar a conexão");
                throw new RuntimeException(e);
            }

        } catch (IOException e){
            System.out.println("Falha ao tentar carregar arquivos com as propriedades");
            e.printStackTrace();
        }
        return connection;
    }
}
