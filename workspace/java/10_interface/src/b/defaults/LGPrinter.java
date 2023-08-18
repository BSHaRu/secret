package b.defaults;

public class LGPrinter implements Printable {

	@Override
	public void print() {
		System.out.println("LG 프린터 출력!");
	}

	@Override
	public void colorPrint() {
		System.out.println("LG의 color 출력!");
	}

}
