package dw1s5.utils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BuscadorDataSource {
	private static BuscadorDataSource instance = new BuscadorDataSource();
	private DataSource dataSource;
	
	private BuscadorDataSource() {
		try {
			Context context = new InitialContext();
			
//			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/OracleBD");			
			//Ou
			
			context = (Context)context.lookup("java:comp/env");
			dataSource = (DataSource)context.lookup("jdbc/OracleDB");
			
		}
		catch(NamingException erro) {
			throw new RuntimeException(erro);
		}
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}
	
	public static BuscadorDataSource getInstance() {
		return instance;
	}
}
