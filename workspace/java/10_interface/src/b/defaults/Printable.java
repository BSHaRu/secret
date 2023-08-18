package b.defaults;

public interface Printable {
	
	void print();
	default void colorPrint() {  // default는 해당 기능을 수행할 수 있는 업체만 사용해라
	}							// -> 강제가 아니라 있는 애들만 선택해서 사용해라
								// => default는 불가피 할 때 쓰는거기 때문에 없는 기능이라고 생각하면됨
	
}
