package e.poly;

public class MongoDB implements Connector {

	@Override
	public void connect() {
		System.out.println("몽고 디비와 연결합니다.");
	}
}

class OracleDB implements Connector{

	@Override
	public void connect() {
		System.out.println("오라클 디비와 연결합니다.");
	}
}



