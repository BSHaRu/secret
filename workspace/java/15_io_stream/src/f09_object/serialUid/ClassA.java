package f09_object.serialUid;

import java.io.Serializable;

public class ClassA implements Serializable { // Serializable : 아무기능없이 해당 class 직렬화 가능하다고만 알려주는 친구임

	private static final long serialVersionUID = -2356547293491809803L;
	int filed1;
	int filed2;
}
