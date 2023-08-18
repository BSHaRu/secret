package c.string.split_tokenizer;

public class SplitEx {

	public static void main(String[] args) {
		String text = "김유신&홍길동,홍두깨,심청이-황진희";
		String[] name = text.split("&|,|-");
		
		for(String s : name) {
			System.out.print(s+" ");
		}
	}

}
