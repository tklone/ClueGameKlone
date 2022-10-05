import java.util.Comparator;

public class Objects {
	public int weight;
	public int value;
	public int index;
	public Double ratio;
	
	
	public static Comparator<Objects> CompareByRatio = new Comparator<Objects>() {
		public int compare (Objects one, Objects other) {
			return other.ratio.compareTo(one.ratio);
		}
	};
	
	

}
