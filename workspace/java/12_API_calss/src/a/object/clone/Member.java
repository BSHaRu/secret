package a.object.clone;

import java.util.Arrays;

//Cloneable : clone이 가능하다는걸 알려주는거지 아무런 기능도 없음
public class Member implements Cloneable { 
	public String name;
	public int age;
	public int[] scores;
	public Car car;
	
	public Member(String name, int age, int[] scores, Car car) {
		super();
		this.name = name;
		this.age = age;
		this.scores = scores;
		this.car = car;
	}
	
	@Override
	public String toString() {
//		return super.toString();
		return "Member [name=" + name + ", age=" + age + ", scores=" + Arrays.toString(scores) + ", car=" + car + "]";
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		Member cloned = (Member)super.clone();
		int[] score = new int[this.scores.length];
		for(int i=0; i<this.scores.length; i++) {
			score[i] = this.scores[i];
		}
		cloned.scores = score;
		
		Car carr = new Car(this.car.model);
		cloned.car = carr;
//		return super.clone();
		return cloned;
	}
	
	
}
