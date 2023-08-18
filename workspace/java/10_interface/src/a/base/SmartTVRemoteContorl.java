package a.base;

public class SmartTVRemoteContorl implements RemoteControl, Searchalbe{

	int voluem = 1;
	
	@Override
	public void serch(String word) {
		System.out.println(word + "로 검색합니다.");
	}

	@Override
	public void turnOff() {
		System.out.println("스마트 TV를 끕니다.");
	}

	@Override
	public void turnOn() {
		System.out.println("스마트 TV를 켭니다.");
	}

	@Override
	public void setValue(int value) {
		this.voluem = value;
		System.out.println("스마트 TV의 볼륨을 조절합니다. : " + voluem);
	}

	
}
