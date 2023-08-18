package explain;

import java.util.*;

/** - 문서 주석
 * 해당 국가의 수도 맞추기 게임을 프로그램으로 만들고자 한다. 
    나라 이름(country)과 수도(capital)로 구성된 Nation 클래스를 작성하고 ArrayList<Nation> 컬렉션을 이용하여 
    프로그램을 작성시오.(ArrayList에 8개의 국가에 대한 기본적인 정보 입력)
 */

class Nation{
	
	private String country;	// 나라 이름
	private String capital;	// 수도 이름
	
	public Nation(String country, String capital) {
		this.country = country;
		this.capital = capital;
	}

	public String getCountry() {
		return country;
	}

	public String getCapital() {
		return capital;
	}

	@Override
	public boolean equals(Object obj) {	// 국가 이름이 똑같으면 동일한 객체로 취급하게 재정의함
		if(obj instanceof Nation) {
			Nation n = (Nation)obj;
			if(this.country.equals(n.getCountry())) {
				return true;
			} // if > if 종료
		} // if 종료
		return false;
	} // equals 재정의 종료
}

public class Test02_Explain02 {
	// 전역변수 -> Test02_Explain02 생성될 때 해당 구문 생성되고 초기화 됨
	private ArrayList<Nation> store = new ArrayList<>();
	private Scanner sc = new Scanner(System.in);
	
	public Test02_Explain02() {
		store.add(new Nation("한국","서울"));
		store.add(new Nation("프랑스","파리"));
		store.add(new Nation("그리스","아테네"));
		store.add(new Nation("스페인","마드리드"));
		store.add(new Nation("중국","베이징"));
		store.add(new Nation("영국","런던"));
		store.add(new Nation("미국","워싱턴"));
		store.add(new Nation("독일","베를린"));
	}
	
	// program start
	public void run() {
		System.out.println("**** 수도 맞추기 게임을 시작합니다. ****");
		while(true) {
			System.out.println("입력 : 1 | 퀴즈 : 2 | 종료 : 3 >>");
			int menu = sc.nextInt();
			switch(menu) {
				case 1 :
					//국가별 수도 정보 추가
					input(); // switch문에 해당 내용 다 넣으면 가독성 떨어지니깐 그냥 따로 빼는거임
					break;
				case 2 :
					//국가 이름으로 수도 정보 맞추기
					quize();
					break;
				case 3 :
					System.out.println("게임을 종료합니다.");
					return;
				default :
					System.out.println("번호를 다시 입력 해 주세요.");
			} // switch 종료
		} // while문 종료
	} // run 종료
	
	private void input() {
		int n = store.size(); // 등록된 국가의 크기
		System.out.println("현재 "+n+"개의 나라와 수도가 입력되어 있습니다.");
		
		a : while(true) {
			System.out.printf("국가 입력(현재 : %d번째 국가 등록 / q는 종료)>> \n",store.size()+1); // 여기에 그냥 n증가를 안함
			String country = sc.next(); // 국가 정보						//-> n은 while 밖에 있기때문에 store.size()증감 여부를 모름
			if(country.equals("q") || country.equals("ㅂ")) {
				System.out.println("입력을 종료합니다.");
				break;
			} // if문 종료
			
			System.out.println("수도 입력 >>");
			String capital = sc.next();
			Nation nation = new Nation(country,capital);
			
			if(store.contains(nation)) { 	// contains : o==null ? e==null : o.equals(e) 일경우 true 반환 
											// -> equals를 재정의를 했기 때문에 이렇게 쓸 수 가 있는거임. 
											// => store배열 중에 country와 nation의 country값이 동일하면 true 반환
				System.out.println(country + "은(는) 이미 존재합니다.");
				continue; 					// 여기서는 continue를 해도 바로 while문으로 가서 continue a; 안해준거임
			}
			
			/* 이렇게 하나 위에 방법으로 하나 상관없음
			for(int i=0; i<store.size(); i++) s{ 	// 국가 이름이 동일한게 있는지 확인
				Nation na = store.get(i);
//				if(na.getCountry().equals(nation.getCountry())) {	// equals 재정의 안했다면 이렇게 쓰는게 맞을껄?
				if(nation.equals(na)){				// 위에 equals를 재정의해놔서 이렇게 써도됨
					System.out.println(country + "은(는) 이미 존재합니다.");
					continue a; // 동일한 이름이 존재하면 while문으로 돌아가서 해당내용 계속 반복하게 함
				} // for > if문 종료
			} // for문 종료
			*/
			store.add(nation); // if문으로 나라 중복있는지 확인했으니 중복된 나라가 없으면 해당 store에 해당 나라를 추가해줘라
		}// while문 종료
	}// input 종료
	
	private void quize() {
		if(store.isEmpty()) {
			System.out.println("문제가 다 끝났습니다.");
			System.out.println("나라 정보를 추가하거나 종료하세요");
			return;
		}
		
		/* 이 random을 써도 되지만 아래 shuffle을 써도됨
		int index = (int) (Math.random()*store.size()); // store.size()-1 만큼 랜덤한 값을 불러옴
		Nation nation = store.get(index);				//  -> 인덱스를 구하는거니깐 store.size()가 8이면
		*/												// (Math.random()*store.size()); = index[0~7]
		Collections.shuffle(store);  					// => 즉, 8개의 값이 나오니깐 store.size()의 -1만큼을 구하는거임
		Nation nation = store.get(0); 
		
		String question = nation.getCountry();
		String answer = nation.getCapital();
		System.out.println(question+"의 수도는? ");
		
		String capitalFromUser = sc.next();
		if(answer.equals(capitalFromUser)) {
			System.err.println("정답입니다.");
			// 정답일 경우 해당 정보 삭제 (동일한 문제 안나오게 하기 위해서)
			store.remove(nation); // store.remove(0); 여기선 둘다 동일하게 쓸 수 있음 
		}else {
			System.out.printf("아닙니다! %s의 수도는 %s입니다. \n",question,answer);
		}
	} // quize 종료
	
	public static void main(String[] args) {
		new Test02_Explain02().run(); // Test02_Explain02 e = new Test02_Explain02() | e.run(); 한거랑 동일한 의미
									// -> 하지만 이렇게 선언하면 e라는 변수가 하나 더 추가되서 메모리 낭비가 됨
	} // main 종료
}
