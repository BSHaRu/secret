package f09_object.serializable;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class ObjectAppendStream extends ObjectOutputStream {

	public ObjectAppendStream(OutputStream out) throws IOException {
		super(out);
	}

	@Override // 이걸 재정의 안하면 이어쓰기 할 경우 이 친구가 헤더에서 obj를 계속 생성하기 때문에 그 기능을 제거하는거임
	protected void writeStreamHeader() throws IOException {
		// 헤더 저장할 때 기능 제거
//		super.writeStreamHeader();  
	}

	
	
}
