package dao;

import db.DB;
import exceptions.DbException;
import model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ProdutoDao {
    public  void inserirProduto(Produto produto);

    public  List<Produto> listarProduto();

    public  void atualizar(Produto produto);

    public  void deletar(int id);



}
