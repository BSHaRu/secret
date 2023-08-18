package f06_stream_reader_writer;

import java.io.Console;

public class ConsoleEx {

	public static void main(String[] args) {
		Console console = System.console();
		System.out.println("아이디 : ");
		String id = console.readLine();
		System.out.println("비밀번호 : ");
		char[] password = console.readPassword(); // 콘솔에 입력을 하였지만, 보이지는 않는 역할
		System.out.println("================================================");
		String strPassword = new String(password);
		System.out.println(id);			// 여기서 널포인트 오류 뜰꺼임
		System.out.println(strPassword);// 이걸 실행하기 위해서는 해당 폴더의 바이너리(bin)들어가서
										// cmd로 실행시키면 실행됨
							// -> java 패키지이름.클래스명
							// => java f06_stream_reader_writer.ConsoleEx
			/* cmd 명령어
			 	java : 실행명령어
			 	javac 클래스명.java : 해당 클래스파일 컴파일해줌 
			 */
	}

}
