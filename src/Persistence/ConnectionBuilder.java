package Persistence;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBuilder {

    private Connection c;
    public Connection getConnection(){
        String hostName = "localhost";
        String dbName = "Aluno";
        String user = "Luan";
        String password = "123456";


        try {
            c = DriverManager.getConnection(String.format("jdbc:jtds:sqlserver://%s:1433;databaseName=%s;user=%s;password=%s",
                    hostName, dbName, user, password));
        } catch (SQLException e) {
            System.out.println("Erro ao connectar com o banco de dados" + e.getMessage());
        }


        return c;
    }


}