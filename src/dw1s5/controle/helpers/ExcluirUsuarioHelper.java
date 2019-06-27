package dw1s5.controle.helpers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dw1s5.modelo.daos.UsuarioDao;
import dw1s5.modelo.entidades.Usuario;
import dw1s5.utils.BuscadorDataSource;

public class ExcluirUsuarioHelper implements Helper {

	String pagina = "listaInativo";
	
	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String usuario = request.getParameter("usuario");
		System.out.println(usuario);
		UsuarioDao usuarioDao = new UsuarioDao(BuscadorDataSource.getInstance().getDataSource());
		usuarioDao.ExcluirUsuario(usuario);
		return pagina;
	}
	
}
