package dw1s5.controle.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dw1s5.modelo.daos.UsuarioDao;
import dw1s5.modelo.entidades.Usuario;
import dw1s5.utils.BuscadorDataSource;

public class ListarUsuariosHelper implements Helper {

	String pagina="/WEB-INF/listaInativos.jsp";
		
	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub		
		
		UsuarioDao usuarioDao = new UsuarioDao(BuscadorDataSource.getInstance().getDataSource());
		List<Optional<Usuario>> lista = usuarioDao.listaInativo();
		
		
		if(!lista.isEmpty()) {
			List<Usuario> listaInativos = new ArrayList<Usuario>();
			lista.forEach(opt -> {
				opt.ifPresent(usuario -> {
					listaInativos.add(usuario);
				});
			});
			request.setAttribute("listaInativos", listaInativos);			
			
		}
		
		System.out.println(pagina);
		
		return pagina;
	}
	
}
