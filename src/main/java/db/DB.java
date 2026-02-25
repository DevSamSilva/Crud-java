package db;

import exceptions.DbException;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;

public class DB {
    private static final Dotenv dotenv = Dotenv.load();

    private static final String URL = dotenv.get("DB_URL");
    private static final String USER = dotenv.get("DB_USER");
    private static final String PASSWORD = dotenv.get("DB_PASSWORD");

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }

    public static void fecharConexao(Connection conn){
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void fecharStatement(PreparedStatement stmt){
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e){
                throw new DbException("Error: " + e.getMessage());
        }
        }
    }

    public static void fecharResultSet(ResultSet rs){
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException e){
                throw new DbException("Error: " + e.getMessage());
            }
        }
    }

}
