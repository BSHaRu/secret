package g.object_array;

public class Student {
	int number;		// 학번
	String name; 	// 이름
	int score; 		// 점수
	
	
	// 객체정보를 확인하는 toString() : alt + s + s + s 
	// toString()은 이것보단 더 중요한 친군데 추가적인건 나중에 알려준다고함
	public String toString() {
		return "Student [number=" + this.number + ", name=" + this.name + ", score=" + this.score + "]"; // this 생략가능
	}
	
	
	
}
