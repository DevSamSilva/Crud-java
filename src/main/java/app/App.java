package app;

import dao.ProdutoDao;
import db.DB;
import model.Produto;
import service.ProdutoService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws SQLException {
        Connection conn = DB.conectar();
        System.out.println("Banco conectado");

        ProdutoService produtoService = new ProdutoService();
        produtoService.produtoService(new Scanner(System.in), new Produto());
    }
}

