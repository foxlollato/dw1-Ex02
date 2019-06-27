package dw1s5.controle.helpers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dw1s5.modelo.daos.UsuarioDao;
import dw1s5.modelo.entidades.Usuario;
import dw1s5.utils.BuscadorDataSource;

public class LoginHelper implements Helper {

	String pagina = "/WEB-INF/erro.html";
		
	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		UsuarioDao usuarioDao = new UsuarioDao(BuscadorDataSource.getInstance().getDataSource());
		Optional<Usuario> opt = usuarioDao.getUsuarioPeloEmailESenha(email, senha);
		
		
		opt.ifPresent(
			usuario -> 
			{
				HttpSession session = request.getSession();
				session.setMaxInactiveInterval(60);
				session.setAttribute("usuario", usuario);
				
				pagina="/WEB-INF/professor.jsp";
				
				if(usuario.getPerfil().contentEquals("administrador")) {
					pagina = "/WEB-INF/administrador.jsp";
				}
				if(usuario.getPerfil().contentEquals("aluno")) {
					pagina = "/WEB-INF/aluno.jsp";
				}
				
			}
				
		);
		
		
		
		return pagina;
	}
	
}
