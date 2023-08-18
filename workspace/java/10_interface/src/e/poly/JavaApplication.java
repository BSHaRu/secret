package e.poly;

public class JavaApplication {

	public static void dbConnection(Connector conn) {
		conn.connect();
	}
	
	public static void main(String[] args) {
		dbConnection(new OracleDB());
		dbConnection(new MongoDB());
	}

}
