package f01_file;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileEx {

	public static void main(String[] args) {
		String dir = "C:/Temp/dir/aaa"; // 원래 정식은 "C:\\Temp\\dir\\aaa";이건데 window에서는 /도 인식을 해줌
										// -> java에서는 특수문자들을 사용할때 \ 쓰기 때문에
		String fileName = "text.java";	//  \\ 이렇게 2개를 쓰는거임
		
		String separator = File.separator; // separator : 해당 운영체제에 맞는 구분자로 바꿔줌 
		System.out.println(separator);
		dir = "C:"+separator+"Temp"+separator+"dir"+separator+"aaa"; // 이렇게 설정하면 어떤 운영체제에서도 알아서 넣어줌.
		System.out.println(dir);
		System.out.println("=========================================================");
		
		File file = new File(dir);
				// exists(): 해당 위치에 폴더나 파일이 존재하면 true | 없으면 false
		if(!file.exists()) { 	// 디렉토리가 존재하지 않을때 해당 if문 실행
			System.out.println("디렉토리가 존재하지 않습니다.");
//			boolean isMake = file.mkdir();		// mkdir() : 경로상 마지막 디렉토리생성 
			boolean isMake = file.mkdirs();		// 경로상에 정의된 모든 디렉토리를 생성함
			System.out.println("디렉토리 생성 여부 : " + isMake);//-> mkdir()로는 dir이 생성안되어 있어서 false가 뜸
		}							// => 한번 실행시키면 mkdirs()로 디렉토리를 생성했기때문에 2번쨰부터는 if문 실행을 안함
		
		file = new File(dir,fileName);
		if(file.exists() == false) {
			System.out.println("파일이 존재하지 않음");
			try {						// createNewFile() : 빈 파일 생성 
				file.createNewFile();	// 여러가지 이유로 오류있을 수 있으니 try catch로 예외처리 실행
				System.out.println("파일 생성 완료");
			} catch (IOException e) {
				System.out.println("파일 생성 실패 : "+e.getMessage());
			} // try catch 종료
		} // if 종료
		
		File file1 = new File("C:\\Temp\\file1.text");
		if(!file1.exists()) {
			// alt + s + w : 통해서 블록처리된걸 try에 감싸게 됨
			try {
				boolean isMake = file1.createNewFile(); // 파일 이 없으면 생성 후 true | 있으면 false 반환
				System.out.println("파일 생성 여부 : " + isMake);
			} catch (IOException e) {
				System.out.println("파일 생성 실패 : "+e.getMessage());
			}// try catch 종료
		} // if 종료
		System.out.println("=========================================================");
		
		String path = file1.getAbsolutePath(); // file에대한 정보를 가지고있기 때문에 파일 경로도 확인가능
		System.out.println("file1의 절대 경로 : " + path);
		
		File temp = new File("C:\\Temp");
		File[] temps = temp.listFiles();
		System.out.println(temp.length());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a hh:mm");
		for(File f : temps) {
			long modified = f.lastModified(); // 폴더나 파일의 마지막 수정 시간을 ms으로 알려줌
			Date date = new Date(modified);
			String lastModified = sdf.format(date);
			System.out.print(lastModified);
			
			if(f.isDirectory()) {	// 디렉토리면 true 아니면 false
				System.out.println("\t<DIR>\t\t\t"+f.getName()); // getName() : 파일 이름
			}else {
				System.out.println("\t<FILE>\t\t\t"+f.getName());// \t : 탭
			}
		}// for문 종료
		System.out.println("=========================================================");
		
		// 디렉토리 파일 삭제 
		File file2 = new File("C:\\Temp\\dir\\aaa\\text.java");
		boolean isDeleted = false;
		isDeleted = file2.delete();
		System.out.println("파일 삭제 여부 : " + isDeleted);  // 디렉토리안에 파일이 존재하면 삭제불가
		
		file2 = new File("C:\\Temp\\dir\\aaa");
		file2.delete();
		System.out.println("디렉토리 삭제 여부 : " + isDeleted);
		
	}// main 종료

}
