package adapter;

import model.*;
import util.FileIO;

public abstract class ProxyAutomobile {
	private static Automobile am;
	
	public void buildAuto(String fileName) {
		am = FileIO.readFile(fileName);
	}

	public void printAuto(String modelName) {
		am.print();
	}

	public void updateOptionSetName(String modelName, String optionSetName, String newName) {
		am.updateOptionSetName(optionSetName, newName);
	}

	public void updateOptionPrice(String modelName, String optionSetName, String optionName, float newPrice) {
		
	}
}
