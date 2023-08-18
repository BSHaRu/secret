package a.object.clone;

public class CloneEx {

	public static void main(String[] args) throws CloneNotSupportedException {
		Member member = new Member("홍길동",20,
								new int[] {100,95,90},
								new Car("카이엔"));
		System.out.println(member);
		Member cloned = (Member) member.clone();
		System.out.println(cloned);
		// 그냥 clone해버리면 toString위치값이 동일하지만, 
		// toString을 Object로 받으면 주소값이 달라진다.
		cloned.scores[0] = 50;
		System.out.println(member);
		System.out.println(cloned);
	}

}
