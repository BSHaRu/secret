package a.object.equals;

public class Member {

	String id;

	public Member(String id) {
		super();
		this.id = id;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + "]";
	}

	// alt + s + v
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Member) {
			Member member1 = (Member)obj;
			if(this.id.equals(member1.id)) {
				return true;
			}
		}
		return false;
	}
	
	
	
}
