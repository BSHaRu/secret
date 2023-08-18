package generic.ex3;

import java.util.Arrays;

public class ArrayTest<E> {

	private int capacity;		// 저장 가능한 배열의 크기
	private int length;			// 배열에 저장된 실제 값의 크기
	private E[] array; 			// generic으로 지정된 Type의 배열
	
	public ArrayTest() {
		this(10);				// 기본 생성자로 생성하면 10개의 값을 저장할 수 있는 배열 생성(capacity의 값)
	}
	
	@SuppressWarnings("unchecked")		// 노란줄 보기 싫으면 그냥 이거 생성해주면됨
	public ArrayTest(int capacity) {
		this.capacity = capacity;
		array = (E[])new Object[capacity];
	}
	
	public void add(E e) {
		if(this.length >= this.capacity) {		// 2. 더이상 추가가 안되니깐 그걸 해결하기 위해서 이걸 만듬
			E[] newArray = (E[])new Object[capacity+1];
			this.capacity = newArray.length;
			for(int i=0; i < this.array.length; i++) {
				newArray[i] = this.array[i];	// 3. 새로 배열을 추가하였으니, 그 새로운 배열안에 기존값 넣는거임
			}
			// 새로 생성된 배열 마지막 인덱스값 추가 -> 4. 기존의 값만 추가되면 마지막 인덱스 값은 null이니깐 해당값에 e를 넣는거임
			newArray[capacity-1] = e;
			this.array = newArray;
		}else {
			for(int i=0; i<capacity; i++) {		// 이 for문은 원래 기존에 있던거 였는데 else에 넣은거임
				if(this.array[i] == null) {		//-> 즉, 1,2번이 하기 생성하기전에는 이 for문만 있었음
					this.array[i] = e;
					break;
				}
			}// for문 종료
		} 	// else문 종료
		length++;	// capacity값이 추가 되었으니 배열의 길이도 증가시키는거임
	}	// add문 종료
	
	public void remove(E e) {
		for(int i=0; i < capacity; i++) {
			if(array[i] != null && array[i].equals(e)) {
				E[] newArray = (E[])new Object[capacity-1];
				this.capacity = newArray.length;
				for(int j=0; j < capacity; j++) {
					if(j < i) {			// i값보다 j가작으면 그냥 그대로 해당값을 복사해라
						newArray[j] = this.array[j];
					}else {				// i값이랑 j값이 같거나 j가 클경우 그다음 인덱스번호의 값을 넣어라.
						newArray[j] = this.array[j+1];
					}
				} // i-for > if > j-for문 종료
				this.array = newArray;
				length--;
				break;
			} // i-for문 > if문 종료
		} // i-for문 종료
	} // remove 종료
	
	
	@Override					// toString 재정의하는게 맞다고 알려줌
	public String toString() {
		return Arrays.toString(this.array);
	}
	
	public int size() {
		return this.length;
	}
	
	public static void main(String[] args) {
		ArrayTest<String> array = new ArrayTest<>();
		System.out.println(array);
		System.out.println(array.size());	// 실제의 값이 안들어가있기때문에 길이가 0이 나오는거임.
											//-> 실제의 값 만큼 길이가 정해짐
		
		System.out.println("==============================================");
		
		array = new ArrayTest<>(3);
		array.add("홍길동");
		System.out.println(array);
		System.out.println(array.size());
		
		array.add("김유신");
		System.out.println(array);
		System.out.println(array.size());
		
		array.add("유관순");
		System.out.println(array);
		System.out.println(array.size());
		
		array.add("이순신");
		System.out.println(array);
		System.out.println(array.size());	// 1. 애초에 배열크기를 3으로 잡아놨기때문에 더이상 추가가 안된다.
		
		
		array.remove("유관순");
		System.out.println(array);
		System.out.println(array.size());
	}

}
