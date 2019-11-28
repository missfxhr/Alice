package driver;

import model.*;
import scale.EditOption;
import server.BuildCarModelOptions;
import server.DefaultServerSocket;
import util.*;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import adapter.*;
import client.DefaultSocketClient;
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
	
	// test for assignment 4 - multi-thread editing model
	// As we can see from the printed results
	// Each thread takes turn to access the given model, no crossover
	// Printed information also reflected the content which got edited by that thread
	public void test4() {
		testStart(4);
		String modelName = "Focus Wagon ZTW";
		CreateAuto ca = new BuildAuto();
		ca.buildAuto(this.getFileName(modelName));

		EditOption eo1 = new EditOption(modelName, "thread-1");
		eo1.updateOptionPrice("Transmission", "automatic", 100.0f);
		EditOption eo2 = new EditOption(modelName, "thread-2");
		eo2.updateOptionChoice("Transmission", "automatic");
		EditOption eo3 = new EditOption(modelName, "thread-3");
		eo3.updateOptionChoice("Color", "Grabber Green Clearcoat Metallic");

		eo1.start();
		eo2.start();
		eo3.start();
		testEnd(4);
	}

	// test for assignment 5 - client/server structure
	public void test5() {
		// pre-load one model into "database" before server starts
		// using our old approach in assignment 2
		CreateAuto ca = new BuildAuto();
		String model1 = "Focus Wagon ZTW";
		ca.buildAuto(this.getFileName(model1));

		BuildCarModelOptions bcmo = new BuildCarModelOptions();
		bcmo.serve(6666);
		
//		InetAddress host;
//		try {
//			// start client
//			host = InetAddress.getLocalHost();
//			DefaultSocketClient client = new DefaultSocketClient(host.getHostName(), 6666);
//			client.start();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
		// To play around, you have accord.prop and prius.prop to upload
		// Together with the pre-loaded model, you will have maximum 3 models to select
	}
}
