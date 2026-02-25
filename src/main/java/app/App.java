package app;

import dao.ProdutoDao;
import db.DB;
import model.Produto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws SQLException {
        Connection conn = DB.conectar();
        System.out.println("Banco conectado");

        Scanner sc = new Scanner(System.in);

        boolean bool = true;

        while (bool){
            System.out.println("===== Sistema Loja =====");
            System.out.println("Escolha uma opção:");
            System.out.println("(1)Inserir produtos.");
            System.out.println("(2)Listar produtos.");
            System.out.println("(3)Atualizar produtos.");
            System.out.println("(4)Deletar produtos.");
            System.out.println("(5)Sair do sistema.");
            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao){
                case 1:
                    System.out.println("Digite o nome do produto:");
                    String nome = sc.nextLine();
                    System.out.println("Digite o preço do produto:");
                    double preco = sc.nextDouble();
                    System.out.println("Digite a quantidade do produto:");
                    int quantidade = sc.nextInt();
                    ProdutoDao.inserirProduto(new Produto(nome,preco,quantidade));
                    break;
                case 2:
                    System.out.println(ProdutoDao.listarProduto()+"\n");
                    break;
                case 3:
                    System.out.println("Digite os dados para atualizar o produto");
                    System.out.println("Digite o ID do produto: ");
                    int idAtualizdo = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Digite o nome do produto");
                    String nomeAtualizado = sc.nextLine();
                    System.out.println("Digite o preço: ");
                    double precoAtualizado = sc.nextDouble();
                    System.out.println("Digite a quantidade:");
                    int quantidadeAtualizado = sc.nextInt();
                    Produto produto = new Produto(nomeAtualizado, precoAtualizado, quantidadeAtualizado);
                    produto.setId(idAtualizdo);
                    ProdutoDao.atualizar(produto);
                    break;
                case 4:
                    System.out.println("Digite o ID para exclusão:");
                    int idExclusao = sc.nextInt();
                    ProdutoDao.deletar(idExclusao);
                    break;
                case 5:
                    System.out.println("Programa finalizado:");
                    bool = false;
                    break;
                default:
                    System.out.println("opção inválida");
                    continue;
            }
        }


    }
}

