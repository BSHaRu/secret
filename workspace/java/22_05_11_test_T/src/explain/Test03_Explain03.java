package explain;

import java.util.*;

class Location{
	
	// 경도(longitude) | 위도(latitude)
	private double longitude;
	private double latitude;
	
	public Location(double longitude, double latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	@Override
	public String toString() {
		return "Location [longitude=" + longitude + ", latitude=" + latitude + "]";
	}
	
}

public class Test03_Explain03 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Map<String, Location> manage = new HashMap<>();
		System.out.println("도시, 경도, 위도를 한번에 입력해주세요.(완료되면 q를 입력해주세요)");
		
		while(true) {
			System.out.print(">> ");
			String strs = sc.nextLine();
			if(strs.trim().equals("q") || strs.trim().equals("ㅂ")) break;
			
			String[] st = strs.split(",");
			if(st.length != 3) {
				System.out.println("잘못된 입력입니다.");
				continue;
			}
			
			String city = st[0].trim();
			double longitude = Double.parseDouble(st[1].trim());
			double latitude = Double.parseDouble(st[2].trim());
			
			Location location = new Location(longitude,latitude);
			manage.put(city, location); 	// Map은 put으로 추가 | List는 add로 추가
		} // while문 종료 - (사용자)입력 완료
		System.out.println("--------------------------------------");
		
		// manage map 객체가 저장하고 있는 key 묶음
		Set<String> key = manage.keySet();
		for(String k : key) {
			Location l = manage.get(k);
			System.out.printf("%s %.1f %.1f \n",k,l.getLongitude(),l.getLatitude());
		} // 출력 완료
		System.out.println("--------------------------------------");
		
		// 도시 이름이 일치하는 정보를 찾아 출력
		while(true) {
			System.out.println("도시 이름(종료 하실라면 q를 눌러주세요) >>");
			String city = sc.next();
			if(city.trim().equals("q") || city.trim().equals("ㅂ")) break;
			
			if(manage.containsKey(city)) { // true면 동일한 도시이름 존재한다는 의미
				Location l = manage.get(city);
				System.out.println(city+" "+l);
			}else {
				System.out.println(city+"는 없습니다.");
			} // if문 종료
		}// while문 종료
		System.out.println("시스템 종료");
	} //main 종료
}
