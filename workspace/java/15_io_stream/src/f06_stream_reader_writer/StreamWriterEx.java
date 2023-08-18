package f06_stream_reader_writer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class StreamWriterEx {

	public static void main(String[] args) {
		
		try {
			FileOutputStream fos = new FileOutputStream("C:\\temp\\fos.txt");
			Writer writer = new OutputStreamWriter(fos);
			String data = "바이트 출력 스트림을 문자기반 출력 스트림으로 변환~~~!!"; // OutputStreamWriter이 이 해당문구의 뜻이래
			writer.write(data);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
