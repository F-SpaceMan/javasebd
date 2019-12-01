package model.dao;

import java.sql.*;
import java.sql.Connection;
import connection.ConnectionFactory;
import java.util.ArrayList;
import java.util.List;
import model.bean.Categoria;

public class CategoriaDAO {

    private Connection con = null;

    public CategoriaDAO() {
        this.con = ConnectionFactory.getConnection();
    }

    public boolean save(Categoria categoria) {

        String sql = "INSERT INTO categoria (descricao) VALUES (?)";
        PreparedStatement stmt = null;

        try {
            stmt = this.con.prepareStatement(sql);
            stmt.setString(1, categoria.getDescricao());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<Categoria> findAll() {

        String sql = "SELECT * FROM categoria";

        PreparedStatement stmt = null;
        ResultSet reset = null;

        List<Categoria> categorias = new ArrayList<>();

        try {

            stmt = this.con.prepareStatement(sql);
            reset = stmt.executeQuery();//retorna linhas

            while (reset.next()) {

                Categoria categoria = new Categoria();
                categoria.setDescricao(reset.getString("descricao"));
                //Categoria categoria = new Categoria(reset.getString("descricao"));

                categorias.add(categoria);
            }
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, reset);
        }
        return categorias;
    }

    public boolean update(Categoria categoria) {
        String sql = "UPDATE categoria SET descricao = ? WHERE id = ?";

        PreparedStatement stmt = null;

        try {

            stmt = this.con.prepareStatement(sql);
            stmt.setString(1, categoria.getDescricao());
            stmt.setInt(2, categoria.getId());
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {

            System.err.println("Erro: " + e);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public boolean delete(Categoria categoria) {
        String sql = "DELETE FROM categoria WHERE id = ?";

        PreparedStatement stmt = null;

        try {

            stmt = this.con.prepareStatement(sql);
            stmt.setInt(1, categoria.getId());
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {

            System.err.println("Erro: " + e);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
