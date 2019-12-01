package main;

import model.bean.Categoria;
import model.bean.Produto;
import model.dao.CategoriaDAO;
import model.dao.ProdutoDAO;

public class Main {

    public static void main(String args[]) {
        //ALTER TABLE tabela AUTO_INCREMENT = 1; zerar o increment

        /*
        Categoria cat = new Categoria("Roupas");
        CategoriaDAO dao = new CategoriaDAO();

        if (dao.save(cat)) {
            System.out.println("Salvo com sucesso!");
        } else {
            System.out.println("Erro ao salvar!");
        }
         */
 /*
        CategoriaDAO dao = new CategoriaDAO();
        //esse for é um foreach, em que um elemento (c), percorre uma matriz/array/lista(dao.findAll)
        for(Categoria c: dao.findAll()){
            System.out.println(c.getDescricao());
        }
         */
 /*
        Categoria cat = new Categoria("Roupa");
        cat.setId(1);
        CategoriaDAO dao = new CategoriaDAO();
        
        if(dao.update(cat)){
            System.out.println("Atualizado com sucesso!");
        } else {
            System.err.println("Erro ao atualizar!");
        }
         */

 /*
        Categoria cat = new Categoria();
        cat.setId(1);
        Produto prod = new Produto("Camiseta", 5, 200, cat);
        
        ProdutoDAO dao = new ProdutoDAO();
        if(dao.save(prod)){
            System.out.println("Salvo com sucesso!");
        }else{
            System.err.println("Deu erro ae!");
        }
         */
        ProdutoDAO dao = new ProdutoDAO();

        for (Produto p : dao.findAll()) {
            System.out.println(p.getId() + " Descrição Produto: " + p.getDescricao() + " - Descrição Categoria: " + p.getCategoria().getDescricao());
        }

    }
}
