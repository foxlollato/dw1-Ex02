package dw1s5.utils.filtros;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class FiltroValida
 */
@WebFilter("/*")
public class FiltroValida implements Filter {

    /**
     * Default constructor. 
     */
    public FiltroValida() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		String uri = httpRequest.getRequestURI();
		HttpSession session = httpRequest.getSession(false);
		boolean uriPermitida = 	uri.endsWith("/") || 
								uri.endsWith("login") ||
								uri.endsWith("cadastro") ||
								uri.endsWith("cadastrar");
		
		boolean usuarioLogado = session != null && session.getAttribute("usuario") != null;
		
		if(uriPermitida || usuarioLogado) {			
			chain.doFilter(request, response);			
		}
		else {			
			httpResponse.sendRedirect(httpRequest.getContextPath()+"/");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
