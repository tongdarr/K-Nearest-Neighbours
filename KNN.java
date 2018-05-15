import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.lang.Math;

public class KNN {

	private static ArrayList<iris> TrainSet = new ArrayList<iris>();
	private static ArrayList<iris> TestSet = new ArrayList<iris>();
	
	private static ArrayList<iris> classifiedTest = new ArrayList<iris>();
	static int k = 3;

	KNN() {
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		// the arguments that must be provided are the training set and test set respectively
		readTrainingData(args[0]);
		readTestData(args[1]);
		
		classifiedTest = computeKNN(k, TrainSet, TestSet);
		
		int count = 0;
		for(int i = 0; i < TestSet.size(); i++) {
			System.out.println("Instance " + (i+1) + " of Test Set = " + classifiedTest.get(i).getType());
			if(TestSet.get(i).getType().equals(classifiedTest.get(i).getType())) {
				count++;
			}
		}
		System.out.println("Correctly classified: " + count + " out of " + classifiedTest.size() + " when k = " + k);
	}

	public static ArrayList<iris> computeKNN(int k, List<iris> training, List<iris> test) {

		ArrayList<iris> classified = new ArrayList<iris>();

		// finding the euclidean distance of the sepalLen feature for the closest
		// for each instance of test, find the 5 nearest neighbours in training in order
		// to classify
		for (int i = 0; i < test.size(); i++) {

			HashMap<Double, iris> map = new HashMap<Double, iris>();
			ArrayList<Double> values = new ArrayList<Double>();

			iris object = test.get(i);
			double sepalLen = object.getSepalLen();
			double sepalWid = object.getSepalWid();
			double petalLen = object.getPetalLen();
			double petalWid = object.getPetalWid();

			for (int j = 0; j < training.size(); j++) {
				double numerator1 = Math.pow(sepalLen - training.get(j).getSepalLen(), 2);
				double denominator1 = Math.pow(getRangeSepalLen(training), 2);
				double euclidDist1 = numerator1 / denominator1;

				double numerator2 = Math.pow(sepalWid - training.get(j).getSepalWid(), 2);
				double denominator2 = Math.pow(getRangeSepalWid(training), 2);
				double euclidDist2 = numerator2 / denominator2;

				double numerator3 = Math.pow(petalLen - training.get(j).getPetalLen(), 2);
				double denominator3 = Math.pow(getRangePetalLen(training), 2);
				double euclidDist3 = numerator3 / denominator3;

				double numerator4 = Math.pow(petalWid - training.get(j).getPetalWid(), 2);
				double denominator4 = Math.pow(getRangePetalWid(training), 2);
				double euclidDist4 = numerator4 / denominator4;

				double result = Math.sqrt(euclidDist1 + euclidDist2 + euclidDist3 + euclidDist4);
				
				// reference the instance of iris to the euclidean distance
				map.put(result, training.get(j));

				// put the euclidean distance into a list for sorting
				values.add(result);
			}

			// sort the distances into descending order
			Collections.sort(values);
			
			List<iris> closest = new ArrayList<iris>();


			// extract k values from list and put the closest values in list
			for (int q = 0; q < k; q++) {
				closest.add(map.get(values.get(q)));
			}
			
			int setosa = 0;
			int versicolor = 0;
			int virginica = 0;

			// count how many of the neighbours are of which class/label
			for (int q = 0; q < k; q++) {
				if (closest.get(q).getType().equals("Iris-setosa")) {
					setosa++;
				} else if (closest.get(q).getType().equals("Iris-versicolor")) {
					versicolor++;
				} else if (closest.get(q).getType().equals("Iris-virginica")) {
					virginica++;
				}
			}

			// find out which class is the closest to test instance
			String label = null;
			if (setosa > versicolor && setosa > virginica) {
				label = "Iris-setosa";
			} else if (versicolor > setosa && versicolor > virginica) {
				label = "Iris-versicolor";
			} else if (virginica > setosa && virginica > versicolor) {
				label = "Iris-virginica";
			}

			classified.add(new iris(sepalLen, sepalWid, petalLen, petalWid, label));
			
		}
		
		return classified;
	}
	
	// gets range of sepal length feature
	public static double getRangeSepalLen(List<iris> training) {
		if (training.isEmpty() || training.size() == 1) {
			System.out.println("Only one or zero values in arraylist");
		}
		double min = training.get(0).getSepalLen();
		double max = training.get(0).getSepalLen();

		for (iris i : training) {
			if (i.getSepalLen() < min) {
				min = i.getSepalLen();
			} else if (i.getSepalLen() > max) {
				max = i.getSepalLen();
			}
		}
		return max - min;
	}
	
	// gets range of sepal width feature
	public static double getRangeSepalWid(List<iris> training) {
		if (training.isEmpty() || training.size() == 1) {
			System.out.println("Only one or zero values in arraylist");
		}
		double min = training.get(0).getSepalWid();
		double max = training.get(0).getSepalWid();

		for (iris i : training) {
			if (i.getSepalWid() < min) {
				min = i.getSepalWid();
			} else if (i.getSepalWid() > max) {
				max = i.getSepalWid();
			}
		}
		return max - min;
	}
	
	// gets range of petal length feature
	public static double getRangePetalLen(List<iris> training) {
		if (training.isEmpty() || training.size() == 1) {
			System.out.println("Only one or zero values in arraylist");
		}
		double min = training.get(0).getPetalLen();
		double max = training.get(0).getPetalLen();

		for (iris i : training) {
			if (i.getPetalLen() < min) {
				min = i.getPetalLen();
			} else if (i.getPetalLen() > max) {
				max = i.getPetalLen();
			}
		}
		return max - min;
	}

	
	// gets range of petal width feature
	public static double getRangePetalWid(List<iris> training) {
		if (training.isEmpty() || training.size() == 1) {
			System.out.println("Only one or zero values in arraylist");
		}
		double min = training.get(0).getPetalWid();
		double max = training.get(0).getPetalWid();

		for (iris i : training) {
			if (i.getPetalWid() < min) {
				min = i.getPetalWid();
			} else if (i.getPetalWid() > max) {
				max = i.getPetalWid();
			}
		}
		return max - min;
	}
	
	public static void readTrainingData(String str) throws FileNotFoundException {
		File f = new File(str);
		try {
			Scanner sc = new Scanner(f);
			// scans each line
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				
				Scanner s = new Scanner(line);
				
				// create an instance of iris if there is a line
				TrainSet.add(new iris(Double.parseDouble(s.next()), Double.parseDouble(s.next()), 
						Double.parseDouble(s.next()), Double.parseDouble(s.next()), s.next()));
				
				s.close();
			}
			
			sc.close();
		}
		
		catch(FileNotFoundException e) {
			throw new FileNotFoundException();
		}
	}
	
	public static void readTestData(String str) throws FileNotFoundException {
		File f = new File(str);
		try {
			Scanner sc = new Scanner(f);
			// scans each line
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				Scanner s = new Scanner(line);
				
				// create an instance of iris if there is a line
				TestSet.add(new iris(Double.parseDouble(s.next()), Double.parseDouble(s.next()), 
						Double.parseDouble(s.next()), Double.parseDouble(s.next()), s.next()));
				
				s.close();
			}
			sc.close();
		}
		
		catch(FileNotFoundException e) {
			throw new FileNotFoundException();
		}
	}
}
	
	
