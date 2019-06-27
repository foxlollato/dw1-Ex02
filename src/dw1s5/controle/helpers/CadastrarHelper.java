package dw1s5.controle.helpers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dw1s5.modelo.daos.UsuarioDao;
import dw1s5.modelo.entidades.Usuario;
import dw1s5.utils.BuscadorDataSource;

public class CadastrarHelper implements Helper {

	String pagina = "/WEB-INF/erro.html";
		
	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String perfil = request.getParameter("perfil");
		
		Usuario usuario = new Usuario(nome, email, senha, perfil);
		UsuarioDao usuarioDao = new UsuarioDao(BuscadorDataSource.getInstance().getDataSource());		
		
		usuarioDao.cadastrarUsuario(usuario);		
		pagina="/";
		
		return pagina;
	}
	
}
