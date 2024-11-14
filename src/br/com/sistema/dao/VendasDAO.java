/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.dao;

import br.com.sitema.jdbc.ConexaoBanco;
import br.com.sitema.model.Vendas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author vinic
 */
public class VendasDAO {
    private Connection conn;
   
   public VendasDAO(){
       this.conn = new ConexaoBanco().pegarConexao();
}
   public void salvar(Vendas obj){
       try {
           String sql = "insert into tb_vendas (cliente_id, data_venda, total_venda,observacoes)values(?,?,?,?)";
           
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setInt(1, obj.getClientes().getId());
           stmt.setString(2, obj.getData_venda());
           stmt.setDouble(3, obj.getTotal_venda());
           stmt.setString(4, obj.getObservacoes());
           stmt.execute();
           stmt.close();
           JOptionPane.showMessageDialog(null, "Venda realizada com sucesso!");
       } catch (Exception e) {
           throw new RuntimeException("Erro ao realizar a venda"+e);
       }
 
   }
   public int retornaUltimoIdVenda(){
       try {
           int ultimoId = 0;
           String sql = "select max(id) id from tb_vendas";
           PreparedStatement stmt = conn.prepareStatement(sql);
           ResultSet rs = stmt.executeQuery();
           while (rs.next()) {
               Vendas v = new Vendas();
               v.setId(rs.getInt("id"));
               ultimoId = v.getId();
               
           }
           return ultimoId;
       } catch (Exception e) {
           throw new RuntimeException("Erro ao retornar o último id da venda!"+e);
       }
   }
}
