
public class Eeyore {
	public void feedback(int score) {
		if (score == 100)
			System.out.println("Meh");
		else if (score > 90)
			System.out.println("Uhhh");
		else if (score > 60)
			System.out.println("Ehhh");
		else
			System.out.println("Rrrmm");
	}
}
