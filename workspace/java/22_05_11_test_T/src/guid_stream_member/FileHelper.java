package guid_stream_member;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class FileHelper {
	
	static File file;
	
	static {	// 이름없이 static 블럭만 있으면 해당 class가 실행하자마자 바로 해당 static 블럭이 실행됨
		// 정적 멤버 실행 블럭 
		// -> class 설계정보가 메모리에 등록된 후 즉시 실행
		try {
			file = new File("member.dat");
			System.out.println(file.getAbsolutePath());
			System.out.println(file.getPath()); // 여기서 getPath는 "member.dat"을 가르킴
			if(!file.exists()) {
				file.createNewFile();
				System.out.println("member.dat 파일 생성 완료");
			}
		} catch (IOException e) {
			System.out.println("파일 생성 실패 : " + e.getMessage());
		}
		
	} // static 종료
	
	{		// 그냥 이름 없는 블럭은 인스턴스가 실행되자마자 바로 해당 블럭 실행됨
		
	}
	
	// 파일에서 회원 목록 정보 가져오기
	@SuppressWarnings("unchecked")
	public static List<Member> getList(){
		if(!file.exists() || file.length() == 0) {
			System.out.println("등록된 회원 정보가 없습니다.");
			return null;
		}
		
		List<Member> memberList = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			memberList = (List<Member>)ois.readObject();
			ois.close();
			fis.close();
		}catch(Exception e){
			
		}
		return memberList;
	}
	
	//회원정보 파일에 저장
	public static void setList(List<Member> list) {
		
		try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(list);
			oos.flush();
			oos.close();
			fos.close();
		} catch (Exception e) {
			
		} // try catch 종료
		
	} // setList 종료
	
}
