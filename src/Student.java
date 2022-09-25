import java.util.Comparator;

public class Student {

	public String name;
	public Integer score;
	
	public static Comparator<Student> CompareByScore = new Comparator<Student>() {
		public int compare(Student one, Student other) {
			return one.score.compareTo(other.score);
		}
	};
}
