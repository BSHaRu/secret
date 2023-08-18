package f08_print;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class PrintEx {

	public static void main(String[] args) throws Exception {
		File file = new File("data.txt");
		FileOutputStream fos = new FileOutputStream(file,true);
		PrintStream ps = new PrintStream(fos,true);	// true일경우 autoflush 해줌
		ps.println();
		ps.println("[프린트 보조 스트림]");
		ps.print(1);
		ps.print("마치 ");
		ps.print("콘솔에 출력하는 것 처럼~~~");
		ps.println(" 데이터를 출력 합니다.");
		ps.printf("A의 값은 %d입니다.",100);
		// print는 기본적으로 오버로딩 되어있기때문에 모든타입 다 가능
		ps.println();
		ps.close(); 
		
	} // main 종료

}
