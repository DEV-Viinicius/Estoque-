/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.dao;

import br.com.sitema.jdbc.ConexaoBanco;
import br.com.sitema.model.Clientes;
import br.com.sitema.model.Fornecedores;
import br.com.sitema.model.Funcionarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author vinic
 */
public class FornecedoresDAO {
   private Connection conn;
   
   public FornecedoresDAO(){
       this.conn = new ConexaoBanco().pegarConexao();
       }
   
   public void Salvar(Fornecedores obj){
       try {
           //1º Criar o SQL
           String sql = "insert into tb_fornecedores(nome, cnpj, email,telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado)"
                   + "values(?,?,?,?,?,?,?,?,?,?,?,?)";
           //2º preparar conexao SQL para se conectar com o banco
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setString(1, obj.getNome());
           stmt.setString(2, obj.getCnpj());
           stmt.setString(3, obj.getEmail());
           stmt.setString(4, obj.getTelefone());
           stmt.setString(5, obj.getCelular());
           stmt.setString(6, obj.getCep());
           stmt.setString(7, obj.getEndereco());
           stmt.setInt(8, obj.getNumero());
           stmt.setString(9, obj.getComplemento());
           stmt.setString(10, obj.getBairro());
           stmt.setString(11, obj.getCidade());
           stmt.setString(12, obj.getEstado());
           // 3º executar sql
           stmt.execute();
           //4º Fechar a conexão
           stmt.close();
           JOptionPane.showMessageDialog(null, "Fornecedor salvo com sucesso");
       } catch (SQLException erro) {
           JOptionPane.showMessageDialog(null,"Erro ao salvar o fornecedor "+ erro);
       }
    }
   public void Editar(Fornecedores obj){
       try {
           //1º Criar o SQL
           String sql = "update tb_fornecedores set nome=?,cnpj=?,email=?,telefone=?,celular=?,cep=?,endereco=?, numero=?, complemento=?, bairro=?, cidade=?, estado=? where id=?";
           //2º preparar conexao SQL para se conectar com o banco
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setString(1, obj.getNome());
           stmt.setString(2, obj.getCnpj());
           stmt.setString(3, obj.getEmail());
           stmt.setString(4, obj.getTelefone());
           stmt.setString(5, obj.getCelular());
           stmt.setString(6, obj.getCep());
           stmt.setString(7, obj.getEndereco());
           stmt.setInt(8, obj.getNumero());
           stmt.setString(9, obj.getComplemento());
           stmt.setString(10, obj.getBairro());
           stmt.setString(11, obj.getCidade());
           stmt.setString(12, obj.getEstado());
           stmt.setInt(13, obj.getId());
           // 3º executar sql
           stmt.execute();
           //4º Fechar a conexão
           stmt.close();
           JOptionPane.showMessageDialog(null, "fornecedor editado com sucesso");
       } catch (SQLException erro) {
           JOptionPane.showMessageDialog(null,"Erro ao editar o fornecodor"+ erro);
       }
    }
   
   public void Excluir(Fornecedores obj){
       try{
           String sql = "delete from tb_Fornecedores where id=?";
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setInt(1,obj.getId());
           stmt.execute();
           stmt.close();
           JOptionPane.showMessageDialog(null, "Fornecedor excluido com sucesso");
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"erro ao excluir o Fornecedor "+  e);
        }

   }
   
   public Fornecedores BuscarFornecedor(String nome){
   try {
       String sql = "select * from tb_fornecedores where nome = ?  ";
       PreparedStatement stmt = conn.prepareStatement(sql);
       stmt.setString(1,nome);
       ResultSet rs = stmt.executeQuery();
       Fornecedores obj = new Fornecedores();
       if(rs.next()){
           obj.setId(rs.getInt("id"));
           obj.setNome(rs.getString("nome"));
           obj.setCnpj(rs.getString("cnpj"));
           obj.setEmail(rs.getString("email"));
           obj.setTelefone(rs.getString("telefone"));
           obj.setCelular(rs.getString("celular"));
           obj.setCep(rs.getString("cep"));
           obj.setEndereco(rs.getString("endereco"));
           obj.setNumero(rs.getInt("numero"));
           obj.setComplemento(rs.getString("complemento"));
           obj.setBairro(rs.getString("bairro"));
           obj.setCidade(rs.getString("cidade"));
           obj.setEstado(rs.getString("estado"));
       
       }
       return obj;
       
   }catch(SQLException erro){
       JOptionPane.showMessageDialog(null,"erro ao buscar forncedor "+erro);
   }
   return null;
   }
   
public List<Fornecedores>Listar(){
List<Fornecedores>lista = new ArrayList<>();
    try{
        String sql = "select * from tb_fornecedores";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        
        while(rs.next()){
           Fornecedores obj = new Fornecedores();
           obj.setId(rs.getInt("id"));
           obj.setNome(rs.getString("nome"));
           obj.setCnpj(rs.getString("cnpj"));
           obj.setEmail(rs.getString("email"));
           obj.setTelefone(rs.getString("telefone"));
           obj.setCelular(rs.getString("celular"));
           obj.setCep(rs.getString("cep"));
           obj.setEndereco(rs.getString("endereco"));
           obj.setNumero(rs.getInt("numero"));
           obj.setComplemento(rs.getString("complemento"));
           obj.setBairro(rs.getString("bairro"));
           obj.setCidade(rs.getString("cidade"));
           obj.setEstado(rs.getString("estado"));
           lista.add(obj);  
            
        }
        return lista;
        
    }catch(SQLException e){ 
      JOptionPane.showMessageDialog(null,"erro  criar a lista :"+e);
    }  
    return null;
  }
public List<Funcionarios>Filtrar(String nome){
List<Funcionarios>lista = new ArrayList<>();
    try{
        String sql = "select * from tb_funcionarios where nome like ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, nome);
        ResultSet rs = stmt.executeQuery();
        
        
        while(rs.next()){
            Funcionarios obj = new Funcionarios();
           obj.setId(rs.getInt("id"));
           obj.setNome(rs.getString("nome"));
           obj.setRg(rs.getString("rg"));
           obj.setCpf(rs.getString("cpf"));
           obj.setEmail(rs.getString("email"));
           obj.setSenha(rs.getString("senha"));
           obj.setCargo(rs.getString("cargo"));
           obj.setNivel_acesso(rs.getString("email"));
           obj.setTelefone(rs.getString("telefone"));
           obj.setCelular(rs.getString("celular"));
           obj.setCep(rs.getString("cep"));
           obj.setEndereco(rs.getString("endereco"));
           obj.setNumero(rs.getInt("numero"));
           obj.setComplemento(rs.getString("complemento"));
           obj.setBairro(rs.getString("bairro"));
           obj.setCidade(rs.getString("cidade"));
           obj.setEstado(rs.getString("estado"));
           lista.add(obj);
            
        }
        return lista;
        
    }catch(SQLException e){ 
      JOptionPane.showMessageDialog(null,"erro ao criar a lista :"+e);
    }  
    return null;
  }
  }
   