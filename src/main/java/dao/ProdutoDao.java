package dao;

import db.Conexao;
import exceptions.DbException;
import model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProdutoDao {
    public static void inserirProduto(Produto produto) throws SQLException {
        String sql = "INSERT INTO produto (nome,preco,quantidade) VALUES (?, ?, ?)";

        try(Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQtd());

            stmt.executeUpdate();
            System.out.println("Produto inserido com sucesso!");
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }
}
