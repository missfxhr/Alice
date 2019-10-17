package driver;

import model.*;
import util.*;

import java.util.LinkedHashMap;

import adapter.*;
import exception.AutoException;

public class Driver {
	// test for assignment 1 - FileIO and serialize
	public void test1() {
		Automobile am = null;
		try {
			am = FileIO.readFile("Focus Wagon ZTW.txt");
		} catch (AutoException e) {
			System.out.println(e.getErrorMessage());
			e.fix();
		}
		if (am == null)
			return;

        am.print();
        String fileName = FileIO.serialize(am);
        Automobile newAm = FileIO.deserialize(fileName);
        newAm.print();
        System.out.println(am.toString().contentEquals(newAm.toString()));
	}

	// test for assignment 2 - interface and custom exception
	public void test2() {
		String modelName = "Focus Wagon ZTW";
		CreateAuto ca = new BuildAuto();
		ca.buildAuto("Focus Wagon ZTW.txt");
		ca.printAuto(modelName);
		UpdateAuto ua = new BuildAuto();
		ua.updateOptionSetName(modelName, "Color", "Colors");
		ua.updateOptionPrice(modelName, "Colors", "Infra-Red Clearcoat", 200);
		ca.printAuto(modelName);
	}

	// test for assignment 3 - 
	public void test3() {
		LinkedHashMap<String, Automobile> lhm = new LinkedHashMap<String, Automobile>();
		
	}
}
