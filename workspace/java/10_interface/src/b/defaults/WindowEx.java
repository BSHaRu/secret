package b.defaults;

public class WindowEx {

	public void print(Printable printer) {
		printer.print();
	}
	
	public static void main(String[] args) {
		WindowEx windows = new WindowEx();
		Printable lgPrinter = new LGPrinter();
		Printable hpPrinter = new HPPrinter();
		windows.print(hpPrinter);
		windows.print(lgPrinter);
	}

}
