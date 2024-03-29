package dw1s5.modelo.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;
import javax.sql.DataSource;

import com.sun.net.httpserver.Authenticator.Result;

import dw1s5.modelo.entidades.Usuario;
import dw1s5.utils.DigestMD5;

public class UsuarioDao {
	private DataSource dataSource;
	
	public UsuarioDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}	
	
	public Optional<Usuario> getUsuarioPeloEmailESenha(String email, String senha) {
		String sql = "select nome, email, senha, perfil_id, ativa from ex2_usuario where email=? and senha=?";
		Optional<Usuario> opt = Optional.empty();
		try(Connection con = dataSource.getConnection();
			PreparedStatement pStat = con.prepareStatement(sql))
		{
			String senhaCriptografada = new DigestMD5(senha).getSenhaCriptografada();
			pStat.setString(1, email);
			pStat.setString(2, senhaCriptografada);			
			
			try(ResultSet rs = pStat.executeQuery()) {
				if(rs.next()) {
					boolean ativo = rs.getLong(5) == 1;
					if(ativo) {
						String nome = rs.getString(1);
						String perfil = lerPerfil(rs.getLong(4), con);
						Usuario usuario = new Usuario(nome, email, senha, perfil);
						opt = Optional.of(usuario);
					}
					
				}
			}
			return opt;
		}
		catch(SQLException erro) {
			throw new RuntimeException("Erro durante a consusuario where email=? and senha=?\";\r\n" + 
					"		Optional<Usuario> opt = Optional.empty();lta", erro);
		}
	}
	
	public void cadastrarUsuario(Usuario usuario)throws SQLException{
		String sql = "insert into ex2_usuario(nome, email, senha, perfil_id) values(?,?,?,?)";
		try(Connection con = dataSource.getConnection()){
			con.setAutoCommit(false);
			try(PreparedStatement pStat = con.prepareStatement(sql)){
				
				long perfil_id = lerPerfilId(usuario.getPerfil(), con);
				String senhaCriptografada = new DigestMD5(usuario.getSenha()).getSenhaCriptografada();
				
				pStat.setString(1, usuario.getNome());
				pStat.setString(2, usuario.getEmail());
				pStat.setString(3, senhaCriptografada);
				pStat.setLong(4, perfil_id);
				
				pStat.executeUpdate();
				
				if(perfil_id == -1) {
					con.rollback();
				}else {
					con.commit();
				}
			}			
		}
	}
	
	public List<String> listaPerfil(){
		String sql = "select perfil from ex2_perfil";
		List<String> lista  = new ArrayList<String>();
		
		try(Connection con = dataSource.getConnection();
			PreparedStatement pStat = con.prepareStatement(sql))
		{
						
			try(ResultSet rs = pStat.executeQuery()) {
				while(rs.next()) {
					lista.add(rs.getString(1));				
					
				}
			}
			return lista;
		}
		catch(SQLException erro) {
			throw new RuntimeException("Erro durante a consulta", erro);
		}
	}
	
	private long lerPerfilId(String perfil, Connection con)throws SQLException{
        String sql = "select perfil_id from ex2_perfil where perfil like ?";
        long id = -1;
        try(PreparedStatement pStat = con.prepareStatement(sql)){
                pStat.setString(1,perfil);
                pStat.executeQuery();
                ResultSet rs = pStat.getResultSet();
                if(rs.next()){
                    id = rs.getInt(1);
                }
        }
        return id;

    }
	private String lerPerfil(long perfil_id, Connection con)throws SQLException{
        String sql = "select perfil from ex2_perfil where perfil_id=?";
        String perfil = null;
        try(PreparedStatement pStat = con.prepareStatement(sql)){
                pStat.setLong(1,perfil_id);
                pStat.executeQuery();
                ResultSet rs = pStat.getResultSet();
                if(rs.next()){
                    perfil = rs.getString(1);
                }
        }
        return perfil;
    }
	
	public List<Optional<Usuario>> listaInativo(){
		String sql = "select nome, email, senha, perfil_id from ex2_usuario where ativa=0";
		List<Optional<Usuario>> lista  = new ArrayList<Optional<Usuario>>();		
		try(Connection con = dataSource.getConnection();
			PreparedStatement pStat = con.prepareStatement(sql))
		{						
			try(ResultSet rs = pStat.executeQuery()) {
				while(rs.next()) {
					Optional<Usuario> opt = Optional.empty();
					String nome = rs.getString(1);
					String email = rs.getString(2);
					String senha = rs.getString(3);
					String perfil = lerPerfil(rs.getLong(4), con);
					Usuario usuario = new Usuario(nome, email, senha, perfil);
					opt = Optional.of(usuario);
					lista.add(opt);
				}
			}
			
			return lista;
		}
		catch(SQLException erro) {
			throw new RuntimeException("Erro durante a consulta", erro);
		}
		
	}
	
	public void ativarUsuario(String usuario)throws SQLException{
		String sql = "update ex2_usuario set ativa=1 where email=?";
		try(Connection con = dataSource.getConnection()){			
			try(PreparedStatement pStat = con.prepareStatement(sql)){				
				pStat.setString(1, usuario);				
				pStat.executeUpdate();			
			
			}
		}			
	}
	
	public void ExcluirUsuario(String usuario)throws SQLException{
		String sql = "delete from ex2_usuario where email=?";
		try(Connection con = dataSource.getConnection()){			
			try(PreparedStatement pStat = con.prepareStatement(sql)){				
				pStat.setString(1, usuario);				
				pStat.executeUpdate();				
			
			}
		}			
	}
}
