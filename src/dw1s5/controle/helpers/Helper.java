package dw1s5.controle.helpers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Helper {
	
	public abstract String executa(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
