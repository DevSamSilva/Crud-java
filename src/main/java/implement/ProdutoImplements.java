package implement;

import dao.ProdutoDao;
import db.DB;
import exceptions.DbException;
import model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoImplements implements ProdutoDao {

    @Override
    public void inserirProduto(Produto produto){
        String sql = "INSERT INTO produto (nome,preco,quantidade) VALUES (?, ?, ?)";

        try (Connection conn = DB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQtd());

            stmt.executeUpdate();
            DB.fecharStatement(stmt);
            System.out.println("Produto inserido com sucesso!");
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public  List<Produto> listarProduto(){

        List<Produto> listar = new ArrayList<>();
        String url = "SELECT * FROM produto";

        try(Connection conn = DB.conectar();
            PreparedStatement stmt = conn.prepareStatement(url);
            ResultSet rs = stmt.executeQuery();) {
            while (rs.next()){
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setPreco(rs.getDouble("preco"));
                p.setQtd(rs.getInt("quantidade"));
                listar.add(p);
            }

            DB.fecharResultSet(rs);
            DB.fecharStatement(stmt);


        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return listar;
    }

    @Override
    public  void atualizar(Produto produto){
        String sql = "UPDATE produto SET nome=?, preco=?, quantidade=? WHERE id=?";

        try(Connection conn = DB.conectar();PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQtd());
            stmt.setInt(4, produto.getId());

            stmt.executeUpdate();
            System.out.println("Produto atualizado com sucesso!");
            DB.fecharStatement(stmt);

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public  void deletar(int id){
        String sql = "DELETE FROM produto WHERE id=?";
        try(Connection conn = DB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Produto deletado com sucesso");
            DB.fecharStatement(stmt);
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
}
