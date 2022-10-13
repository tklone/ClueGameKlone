
public class TheMotivator {
	public void feedback(int score) {
		if (score == 100)
			System.out.println("You're not awesome");
		else if (score > 90)
			System.out.println("That's not great");
		else if (score > 60)
			System.out.println("That's not good ");
		else
			System.out.println("Well, what can I say? Bad");
	}
}
