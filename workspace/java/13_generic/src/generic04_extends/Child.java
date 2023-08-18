package generic04_extends;

public class Child<T, M, C> extends Parent<T, M> {  // 부모가 generic 타입을 가지고 extends하면 자식도 해당 타입을 불러와야됨
													// 자식도 generic 타입을 사용 하고 싶으면 ,하고 추가해주면됨
	private C company;
	
	// getter | setter | toSting() 정의
	public C getCompany() {
		return company;
	}
	
	public void setCompany(C company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "Child [company=" + company + "]";
	}
	
}
