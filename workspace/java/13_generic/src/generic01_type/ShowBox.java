package generic01_type;

public class ShowBox <T> {		// <> : generic은 보통 1개의 글자로 표현함 
								// T : Type
	private T t;				// E : Element
								// M : model
								// K : key
	public T getT() {			// V : value
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}
}							
							
