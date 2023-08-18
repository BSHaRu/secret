package f02_output;

import java.io.*;

public class WriteEx {

	public static void main(String[] args) {
		String path = "C:\\Temp\\file2.txt";
		
		// 해당되는 파일이 없을 수도 있기때문에 예외처리 해줘야됨
		try {		// OutputStream : 파일이 없으면 파일은 생성은 해주지만, 디렉토리는 생성안해줌
			OutputStream os = new FileOutputStream(path,true);  // -> 파일은 생성해줄 필요없지만, 디렉토리는 반드시 생성해줘야된다.
			String s = "EFG";							// FileOutputStream(String name, boolean append)
														//-> boolean은 생략가능(기본값 false)
														// true : 이어쓰기 가능 | false는 덮어쓰기
			byte[] bytes = s.getBytes(); 	// getBytes() : 문자열을 byte단위로 변환 해주는 친구.
			
			/* 첫번째) byte를 하나씩 전달하는 방식
			for(int i=0; i<bytes.length; i++) {
				os.write(bytes[i]); // 해당 파일이 위치가 변경되거나 사용중일 수도 있으니 IOException로 예외처리 해줘야됨
			}						*/
			
//			os.write(bytes); // 두번째) byte배열로 출력??
			os.write(bytes,1,2); // 세번째) byte배열의 인덱스번호로 출력
			
			os.flush(); 	// 남아 있는 버퍼를 처리 해줘야 됨
			os.close();		// 사용이 끝났으니 닫아줘야됨 
							// -> OutputStream을 사용하면 "무조건" flush와 close를 마지막에 달아줘야됨
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

}
