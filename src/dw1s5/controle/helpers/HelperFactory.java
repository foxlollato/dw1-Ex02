package dw1s5.controle.helpers;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class HelperFactory {

	public Helper getHelper(HttpServletRequest request) {
		String acao = request.getPathInfo();
		ServletContext context = request.getServletContext();
		String nomeDaClasse = context.getInitParameter(acao);
		try {
			Class<?> clazz = Class.forName(nomeDaClasse);
			Helper helper = (Helper)clazz.getDeclaredConstructor().newInstance();
			return helper;
		}
		catch(Exception erro) {
			throw new RuntimeException(erro);
		}
	}
}
