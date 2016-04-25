import java.util.Random;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random r = new Random();
		for (int i = 1; i < 100; i++) {
			System.out.println(r.nextInt(2));
		}
	}

}
