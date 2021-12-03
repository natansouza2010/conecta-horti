package ifsp.edu.sqlitedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import org.sqlite.SQLiteDataSource;

public class ConnectionFactory implements AutoCloseable{

    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static Statement statement;

    public static Connection createConnection(){
        try {
            criarConnectionIfNull();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private static void criarConnectionIfNull() throws SQLException {
        SQLiteDataSource sql = new SQLiteDataSource();
        sql.setUrl("jdbc:sqlite:database.db");
        if(connection == null){
            connection = sql.getConnection();
        }
    }

    public static PreparedStatement criarPreparedStatement(String sql){
        try {
            preparedStatement = createConnection().prepareStatement(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return preparedStatement;
    }

    public static Statement criarStatement(){
        try {
            statement = createConnection().createStatement();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return statement;
    }


    @Override
    public void close() throws Exception {

        if(preparedStatement != null){
            preparedStatement.close();
        }
        if(statement != null){
            statement.close();
        }
        if(connection != null){
            connection.close();
        }
    }
}
