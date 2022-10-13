package scoreTrakker;
import java.util.ArrayList;
import java.util.Collections;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ScoreTrakker {

	public ArrayList<Student> studentArray = new ArrayList<>();
	
	private String[] files = {"scores.txt", "BadScores.txt", "nofile.txt" };

	public void loadDataFile(String fileName) throws FileNotFoundException {
		studentArray = new ArrayList<>();
		try {
			File dataFile = new File(fileName);
			Scanner dataReader = new Scanner(dataFile);

			while (dataReader.hasNext()) {
				Student currentStudent = new Student();

				currentStudent.name = dataReader.nextLine();
				String score = dataReader.next();
				try {
					currentStudent.score = Integer.parseInt(score);
					studentArray.add(currentStudent);
				}
				catch(NumberFormatException a){
					//a.printStackTrace();
					//throw new NumberFormatException("Incorrect format for "+currentStudent.name+" not a valid score: "+score);
					System.out.println("\nIncorrect format for "+currentStudent.name+" not a valid score: "+score+'\n');

				}

				if (dataReader.hasNext()) {
					dataReader.nextLine();
				}

				
			}

			dataReader.close();
		}
		catch (LoadDataFileException e) {
			//e.printStackTrace();
			//throw new LoadDataFileException("Can't open file: "+fileName);
			System.out.println("Can't open file: "+fileName);
		}
	}

	public ArrayList<Student> printInOrder() {
		
		Collections.sort(studentArray, Student.CompareByScore);
		for (Student s : studentArray) {
			System.out.print(s.name);
			System.out.print(s.score);
			System.out.println();
		}

		return studentArray;
	}
	
	public void processFiles() throws FileNotFoundException {
		for(String file: files) {
		try {
		loadDataFile(file);
		printInOrder();
		}
		catch(FileNotFoundException e) {
			//e.printStackTrace();
			//throw new FileNotFoundException("Could not find file: "+file);
			System.out.println("\nCould not find file: "+file);
		}
	
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		ScoreTrakker s = new ScoreTrakker();
		s.processFiles();
	}
}
