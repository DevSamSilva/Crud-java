package app;

import db.Conexao;

import java.sql.Connection;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException {
        Connection conn = Conexao.conectar();
        System.out.println("Banco conectado");
    }
}

