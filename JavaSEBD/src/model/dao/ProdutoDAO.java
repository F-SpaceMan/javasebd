package model.dao;

import connection.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.bean.Categoria;
import model.bean.Produto;

public class ProdutoDAO {

    private Connection con = null;

    public ProdutoDAO() {
        this.con = ConnectionFactory.getConnection();
    }

    public boolean save(Produto produto) {

        String sql = "INSERT INTO produto (descricao, qtd, valor, categoria_id) VALUES (?,?,?,?)";
        PreparedStatement stmt = null;

        try {
            
            stmt = this.con.prepareStatement(sql);
            stmt.setString(1, produto.getDescricao());
            stmt.setInt(2, produto.getQtd());
            stmt.setDouble(3, produto.getValor());
            stmt.setInt(4, produto.getCategoria().getId());
            stmt.executeUpdate();
            return true;
            
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<Produto> findAll() {

        String sql = "SELECT p.id as id_prod, p.descricao as desc_prod,"
                + " qtd, valor, categoria_id, c.id as id_cat, c.descricao as desc_cat"
                + " FROM produto p join categoria c on c.id = p.categoria_id";

        PreparedStatement stmt = null;
        ResultSet reset = null;

        List<Produto> produtos = new ArrayList<>();

        try {

            stmt = this.con.prepareStatement(sql);
            reset = stmt.executeQuery();

            while (reset.next()) {
                
                Produto produto = new Produto();
                produto.setId(reset.getInt("id_prod"));
                produto.setDescricao(reset.getString("desc_prod"));
                produto.setQtd(reset.getInt("qtd"));
                produto.setValor(reset.getDouble("valor"));
                
                Categoria categoria = new Categoria();
                categoria.setId(reset.getInt("id_cat"));
                categoria.setDescricao(reset.getString("desc_cat"));
                
                produto.setCategoria(categoria);
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, reset);
        }
        return produtos;
    }
}
