package adapter;

import exception.AutoException;
import model.*;
import util.FileIO;

public abstract class ProxyAutomobile {
	private static final String DEFAULT_MAKE = "Ford";
	private static final float DEFAULT_BASE_PRICE = 18445;

	private static Automobile am;

	public void buildAuto(String fileName) {
		try {
			am = FileIO.readFile(fileName);
		} catch (AutoException e) {
			System.out.println(e.getErrorMessage());
			e.fix();
		}
	}

	public void printAuto(String modelName) {
		am.print();
	}

	public void updateOptionSetName(String modelName, String optionSetName, String newName) {
		am.updateOptionSetName(optionSetName, newName);
	}

	public void updateOptionPrice(String modelName, String optionSetName, String optionName, float newPrice) {
		am.updateOptionPrice(optionSetName, optionName, newPrice);
	}

	public void fix(int codeNum) {
		switch(codeNum) {
			// Missing price for Automobile in text file
			case 0:
				am.setBasePrice(DEFAULT_BASE_PRICE);
				break;
			// Missing OptionSet data (or part of it)
			case 1:
				break;
			// Missing Option data
			case 2:
				break;
			// Missing filename or wrong filename
			case 3:
				am = null;
				break;
			// Missing make for Automobile in text file
			case 4:
				am.setMake(DEFAULT_MAKE);
			default:
				break;
		}
	}
}
