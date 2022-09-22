import java.util.ArrayList;
import java.util.HashSet;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;

public class ScoreTrakker {

	public static ArrayList<Student> studentArray = new ArrayList<>();

	public static void loadDataFile(String fileName) {

		try {
			File dataFile = new File(fileName);
			Scanner dataReader = new Scanner(dataFile);

			while (dataReader.hasNext()) {
				Student currentStudent = new Student();

				currentStudent.name = dataReader.nextLine();
				currentStudent.score = dataReader.nextInt();

				if (dataReader.hasNext()) {
					dataReader.nextLine();
				}

				studentArray.add(currentStudent);
			}

			dataReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error opening file.");
			e.printStackTrace();
		}

//		System.out.println(studentArray);

		for (int i = 0; i < studentArray.size(); i++) {
			Student currentStudent2 = new Student();
			currentStudent2 = studentArray.get(i);
			System.out.println(currentStudent2.name);
			System.out.println(currentStudent2.score);
		}
	}

	public ArrayList<Student> printInOrder(ArrayList<Student> studentArray) {
		Set<Student> sort = new HashSet<>(studentArray);
		studentArray.clear();
		studentArray.addAll(sort);

		return studentArray;
	}

	public static void main(String[] args) {
		loadDataFile("test.txt");
	}
}
