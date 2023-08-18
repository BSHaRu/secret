package f05_buffered;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class BufferedEx {

	public static void main(String[] args) throws Exception {
		String originalPath = "C:\\Temp\\cat3.jpg";
		File originalFile = new File(originalPath);
		FileInputStream fis = new FileInputStream(originalFile);
		BufferedInputStream bis = new BufferedInputStream(fis);
		
		String copyPath = "C:\\Temp\\dir\\copy.jpg";
		File copyFile = new File(copyPath);
		FileOutputStream fos = new FileOutputStream(copyFile);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		
		long startTime, endTime;
		startTime = endTime = 0;
		int data;
		
		startTime = System.nanoTime();
		while((data = fis.read()) != -1) {
			fos.write(data);
		}
		fos.flush();
		endTime = System.nanoTime();
		System.out.printf("file : %d ns %n",(endTime-startTime));
		
		
		startTime = System.nanoTime();
		while((data = bis.read()) != -1) {
			bos.write(data);
		}
		bos.flush();
		endTime = System.nanoTime();
		System.out.printf("buffered : %d ns %n",(endTime-startTime));
		
		bos.close();
		bis.close();
		
		fos.close();
		fis.close();
		System.out.println("copy 완료");
	}

}
