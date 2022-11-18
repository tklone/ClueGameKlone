
public class Timber {

	int returnVal;
	int[] tree = {6, 8, 5, 6, 9, 7};
	int n = tree.length;
	int l_i;
	int l_j;

	//i is the base of the tree
	//j is the top of the tree
	
	public int Timber(int i, int j) {
		//But passing in the wrong things. I should be passing in i and j, not an array
		//How and where do we change the size of the array?
		
		l_i = tree[i];
		l_j = tree[j - 1];
		
		
		//First base case
		if (i == j) {
			return l_i;
		}
		
		//Second base case
		if (j == (i + 1)) {
			return max(l_i, l_j);
		}
		
		returnVal = max(l_i + min(Timber(i + 2, j), Timber(i + 1, j - 1)), l_j + min(Timber(i + 1, j - 1), Timber(i, j - 2)));
		
		return returnVal;
	}

	public  int min(int x, int y) {
		if (x < y) {
			return x;
		}
		if (y < x) {
			return y;
		}
		if (x == y) {
			return x;
		}
		return 0;
	}
	
	
	public  int max(int x, int y) {
		if (x > y) {
			return x;
		}
		if (y > x) {
			return y;
		}
		if (x == y) {
			return y;
		}
		return 0;
	}
	
	public static void main(String[] args) {
		Timber(6, 7); //Some input, but I don't know what I would be inputting
	}
}
