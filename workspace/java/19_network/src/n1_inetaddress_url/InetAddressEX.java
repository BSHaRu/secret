package n1_inetaddress_url;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class InetAddressEX {

	public static void main(String[] args) {

		try {
			InetAddress addr = InetAddress.getLocalHost();
			System.out.println(addr);
			// 내컴퓨터 ip 주소
			System.out.println(addr.getHostAddress());
			
			// naver ip 주소 확인
			InetAddress[] iar = InetAddress.getAllByName("www.naver.com"); // getAllByName : 도메인 이름으로 Inet주소 가져옴
			// 대형 포탈 사이트는 서버가 여러개 있을 수도 있기 때문에 배열로 지정함
			for(InetAddress remote : iar) {
				System.out.println("naver.com IP 주소 : " +remote.getHostAddress());
			}
			
			URL url = new URL("https://www.naver.com");
			
			try {
				InputStream is = url.openStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);

				// 콘솔로 열기엔 너무 크다보니깐 해당 url을 입출력으로 받는 파일을 생성
				File file = new File("naver.html");
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
				
				String isLine = ""; // bw때문에 1줄씩 읽어올 수 있음
				while((isLine = br.readLine()) != null) {
					bw.write(isLine);		// 한줄씩 읽어오고,
					bw.newLine();			// 줄바꿈해준다.
				}
				bw.flush();
				bw.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) { // URL 예외 -> 해당 사이트가 없을 수도 있으니깐 예외처리
			e.printStackTrace();
		}
	}

}
