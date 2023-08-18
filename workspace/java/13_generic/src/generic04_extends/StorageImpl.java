package generic04_extends;

public class StorageImpl<E> implements Storage<E> {		// ~Impl이라고 되어있으면 인터페이스를 품고있는거라 재정의해줘야됨

	private E[] array;
	
	@SuppressWarnings("unchecked")			// 해당 노란줄은 의미 없으니깐 노란줄로 표시하지마라 -> 없어도 되는거긴함
	public StorageImpl(int capacity){
		Object[] o = new Object[capacity];	// 2. 그래서 모든지 들어가는 Object를 생성함
//		this.array = new E[capacity];		// 1. generic 타입으로는 정의 할 수 없음
		this.array =(E[])o;					// 3. ???????
	}
	
	@Override
	public void add(E item, int index) {
		array[index] = item;
	}

	@Override
	public E get(int index) {
		return array[index];
	}		
	

}
