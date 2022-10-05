import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Greedy {

	public static void ReadToObjects () {
		try {
			File dataFile = new File("n10.txt");
			Scanner dataReader = new Scanner(dataFile);

			ArrayList <Objects> allItems = new ArrayList<>();
			Objects singleObject = new Objects();
			
			int maxCapacity = dataReader.nextInt();
			int n = dataReader.nextInt();
			
			while (dataReader.hasNext()) {
				singleObject.weight = dataReader.nextInt();
				singleObject.value = dataReader.nextInt();
				allItems.add(singleObject);
			}
		
			for (Objects O : allItems) {
				System.out.println(O.weight);
			}

			dataReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error opening file.");
			e.printStackTrace();
		}
	}
		
}
