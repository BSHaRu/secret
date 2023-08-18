package a.base;

public class RemoteControlEx {

	public static void main(String[] args) {
//		RemoteControl tvRemote = new RemoteControl();	// 인터페이스는 추상메소드라서 이렇게는 안됨
		RemoteControl tvRemote;
		tvRemote = new TVRemoteControl();				// 인터페이스는 타입이 가능하다.
		tvRemote.turnOn();
		tvRemote.setValue(11);
		tvRemote.turnOff();
		
		System.out.println();
		tvRemote = new AirRemoteControl();
		tvRemote.turnOn();
		tvRemote.setValue(27);
		tvRemote.turnOff();
		
		System.out.println(RemoteControl.MAX_VALUE);
		System.out.println(RemoteControl.MIN_VALUE);
//		RemoteControl.MIN_VALUE = 10;	// 상수기 때문에 당연히 값을 변경 할 수 없다.
		
		RemoteControl smartTVRemote = new SmartTVRemoteContorl();
		
		smartTVRemote.turnOn();
		smartTVRemote.setValue(8);
//		smartTVRemote.search("안됨"); // RemoteControl타입에는 search가 없음
		System.out.println();		// -> SmartTVRemoteContorl타입이거나, Searchalbe받아야 search 사용가능
		
		Searchalbe searchRemote = (Searchalbe)smartTVRemote;
		searchRemote.serch("브로커 빨리 보고 싶다");
		if(searchRemote instanceof SmartTVRemoteContorl) {
			SmartTVRemoteContorl stc = (SmartTVRemoteContorl)searchRemote; // 이건 아래에 stc를 대입할 경우 왜 오류가 생기는지 보여주기용이라
																		// 해당 문장없어도 마지막 출력값에 영향 1도 없음
//			System.out.println(stc.MAX_VALUE); // 이건 안됨 
						// -> SmartTVRemoteContorl에는 RemoteControl, Searchalbe 두개의 인터페이스를 가지고 있기때문에
						// 어떤 MAX_VALUE를 가져오라는지 알 수가 없음
			System.out.println(Searchalbe.MAX_VALUE);	// 그래서 그냥 해당 인터페이스를 넣어서 사용하면 됨.
		}
		
	}	

}
