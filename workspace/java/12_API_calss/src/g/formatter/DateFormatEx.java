package g.formatter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatEx {

	public static void main(String[] args) {
		Date date = new Date();	// util로 import 해줘야됨
		System.out.println(date);
		
		long time = System.currentTimeMillis();
		date = new Date(time);
		System.out.println(date);
		
		date = new Date(0);
		System.out.println(date);
		
		SimpleDateFormat sdf = new SimpleDateFormat();
		String now = sdf.format(new Date());
		System.out.println(now);
		
		sdf = new SimpleDateFormat("yyyy-MM-dd E요일 HH:mm:ss");
		now = sdf.format(new Date());
		System.out.println(now);
		
		date = new Date(Long.MAX_VALUE);
		sdf = new SimpleDateFormat("yyyy년MM월dd일 HH시MM분ss초");
		now = sdf.format(date);
		System.out.println(now);
	}

}
