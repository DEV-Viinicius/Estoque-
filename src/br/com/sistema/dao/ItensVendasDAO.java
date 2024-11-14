/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.dao;

import br.com.sitema.jdbc.ConexaoBanco;
import br.com.sitema.model.ItensVendas;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author vinic
 */
public class ItensVendasDAO {
    private Connection conn;
   
   public ItensVendasDAO(){
       this.conn = new ConexaoBanco().pegarConexao();

   }
   public void salvar(ItensVendas obj){
       try {
           String sql = "insert into tb_itensvendas (venda_id, produto_id, qtd, subtotal) values(?,?,?,?) ";
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setInt(1, obj.getVendas().getId());
           stmt.setInt(2, obj.getProdutos().getId());
           stmt.setInt(3, obj.getQtd());
           stmt.setDouble(4, obj.getSubtotal());
           stmt.execute();
           stmt.close();
       } catch (Exception e) {
           throw new RuntimeException("Erro ao salvar os itens da minha venda!");
       }
   }
   
   
   
   
   
   
   
}
