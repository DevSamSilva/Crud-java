package service;

import dao.ProdutoDao;
import implement.ProdutoImplements;
import model.Produto;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ProdutoService {

    public ProdutoDao produtoDao = new ProdutoImplements();


    public void produtoService(Scanner sc, Produto p) {
        boolean bool = true;

        while (bool) {
                int opcao = introducao(sc);

                switch (opcao) {
                    case 1:
                        try {
                            adicionarProduto(p, sc);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case 2:
                        listarProdutos();
                        break;
                    case 3:
                        atualizarProduto(p, sc);
                        break;
                    case 4:
                        excluirProduto(sc);
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



    public int introducao(Scanner sc){
        System.out.println("===== Sistema Loja =====");
        System.out.println("Escolha uma opção:");
        System.out.println("(1)Inserir produtos.");
        System.out.println("(2)Listar produtos.");
        System.out.println("(3)Atualizar produtos.");
        System.out.println("(4)Deletar produtos.");
        System.out.println("(5)Sair do sistema.");
        int opcao = sc.nextInt();
        sc.nextLine();
        return opcao;
    }

    public void adicionarProduto(Produto produto, Scanner sc) throws SQLException, SQLException {
            System.out.println("Digite o nome do produto:");
            String nome = sc.nextLine();
            System.out.println("Digite o preço do produto:");
            double preco = sc.nextDouble();
            System.out.println("Digite a quantidade do produto:");
            int quantidade = sc.nextInt();
            produto = new Produto(nome,preco,quantidade);
            produtoDao.inserirProduto(produto);


    }

    public void listarProdutos(){
        List<Produto> produtos = produtoDao.listarProduto();
        for (Produto p : produtos) {
            System.out.println(p.getId() + " - " + p.getNome() +
                    " - R$" + p.getPreco() +
                    " - Qtd: " + p.getQtd());
        }
    }

    public void atualizarProduto(Produto produto, Scanner sc){
        System.out.println("Digite os dados para atualizar o produto");
        System.out.println("Digite o ID do produto: ");
        int idAtualizado = sc.nextInt();
        sc.nextLine();
        System.out.println("Digite o nome do produto");
        String nomeAtualizado = sc.nextLine();
        System.out.println("Digite o preço: ");
        double precoAtualizado = sc.nextDouble();
        System.out.println("Digite a quantidade:");
        int quantidadeAtualizado = sc.nextInt();
        produto = new Produto(nomeAtualizado, precoAtualizado, quantidadeAtualizado);
        produto.setId(idAtualizado);
        produtoDao.atualizar(produto);
    }

    public  void excluirProduto(Scanner sc){
        System.out.println("Digite o ID para exclusão:");
        int idExclusao = sc.nextInt();
        produtoDao.deletar(idExclusao);
    }
}
