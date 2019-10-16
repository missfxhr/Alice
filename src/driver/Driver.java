package driver;

import model.Automobile;
import util.FileIO;

public class Driver {
	public void test1() {
		try {
	        Automobile am = FileIO.readFile("FordZTW.txt");
	        am.print();
	        String fileName = FileIO.serialize(am);
	        Automobile newAm = FileIO.deserialize(fileName);
	        newAm.print();
	        System.out.println(am.toString().contentEquals(newAm.toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
