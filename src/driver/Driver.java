package driver;

import model.*;
import util.*;

import adapter.*;
import exception.AutoException;

public class Driver {
	private static final String MODEL_FILE_TYPE = ".txt";

	private String getFileName(String modelName) {
		StringBuilder sb = new StringBuilder();
		sb.append(modelName);
		sb.append(MODEL_FILE_TYPE);
		return sb.toString();
	}

	private void testStart(int testNum) {
		StringBuilder sb = new StringBuilder("/**************** Beginning of test");
		sb.append(testNum);
		sb.append(" ****************/");
		sb.append("\r\n");
		System.out.println(sb.toString());
	}

	private void testEnd(int testNum) {
		StringBuilder sb = new StringBuilder("/**************** End of test");
		sb.append(testNum);
		sb.append(" ****************/");
		sb.append("\r\n");
		sb.append("\r\n");
		System.out.println(sb.toString());
	}

	// test for assignment 1 - FileIO and serialize
	public void test1() {
		testStart(1);
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
        testEnd(1);
	}

	// test for assignment 2 - interface and custom exception
	public void test2() {
		testStart(2);
		String modelName = "Focus Wagon ZTW";
		CreateAuto ca = new BuildAuto();
		// verify the AutoException is thrown
		ca.buildAuto("wrong file name.txt");
		ca.buildAuto(this.getFileName(modelName));
		ca.printAuto(modelName);
		UpdateAuto ua = new BuildAuto();
		ua.updateOptionSetName(modelName, "Color", "Colors");
		ua.updateOptionPrice(modelName, "Colors", "Infra-Red Clearcoat", 200);
		ca.printAuto(modelName);
		testEnd(2);
	}

	// test for assignment 3 - multi-model supports 
	public void test3() {
		testStart(3);
		CreateAuto ca = new BuildAuto();
		UpdateAuto ua = new BuildAuto();

		String model1 = "Focus Wagon ZTW";
		String model2 = "Focus New Model";

		// build both models
		ca.buildAuto(this.getFileName(model1));
		ca.buildAuto(this.getFileName(model2));

		// set user choices for both models
		ua.updateOptionChoice(model1, "Color", "Cloud 9 White Clearcoat");
		ua.updateOptionChoice(model1, "Transmission", "automatic");
		ua.updateOptionChoice(model1, "Brakes/Traction Control", "ABS");
		ua.updateOptionChoice(model1, "Side Impact Air Bags", "selected");
		ua.updateOptionChoice(model1, "Power Moonroof", "selected");

		ua.updateOptionChoice(model2, "Color", "Color_2");
		ua.updateOptionChoice(model2, "Transmission", "standard");
		ua.updateOptionChoice(model2, "Brakes/Traction Control", "ABS");
		ua.updateOptionChoice(model2, "Side Impact Air Bags", "none");
		ua.updateOptionChoice(model2, "Power Moonroof", "selected");

		// print out details
		ca.printAuto(model1);
		ca.printAuto(model2);

		// update one of the option price for model1 which is selected by user
		ua.updateOptionPrice(model1, "Color", "Cloud 9 White Clearcoat", 100);
		// print out details
		ca.printAuto(model1);
		testEnd(3);
	}
}
