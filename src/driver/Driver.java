package driver;

import model.*;
import util.*;
import adapter.*;

public class Driver {
	public void test1() {
        Automobile am = FileIO.readFile("FordZTW.txt");
        am.print();
        String fileName = FileIO.serialize(am);
        Automobile newAm = FileIO.deserialize(fileName);
        newAm.print();
        System.out.println(am.toString().contentEquals(newAm.toString()));
	}
	
	public void test2() {
		String modelName = "FordZTW";
		CreateAuto ca = new BuildAuto();
		ca.buildAuto("FordZTW.txt");
		ca.printAuto(modelName);
		UpdateAuto ua = new BuildAuto();
		ua.updateOptionSetName(modelName, "OptionSet_2", "OptionSet_two");
		ua.updateOptionPrice(modelName, "OptionSet_two", "Color", 500);
		ca.printAuto(modelName);
	}
}
