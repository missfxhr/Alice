package adapter;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

import exception.AutoException;
import model.*;
import util.FileIO;

public abstract class ProxyAutomobile {
	private static final String DEFAULT_MAKE = "Ford";
	private static final float DEFAULT_BASE_PRICE = 18445;

	private static Automobile am;
	private static LinkedHashMap<String, Automobile> automobiles = new LinkedHashMap<String, Automobile>();

	// utils

	public Automobile findModel(String modelName) {
		Set<Entry<String, Automobile>> entrySet = automobiles.entrySet();
		Iterator<Entry<String, Automobile>> it = entrySet.iterator();
		while (it.hasNext()) {
			Entry<String, Automobile> entry = it.next();
			if (!entry.getKey().contentEquals(modelName))
				continue;

			return entry.getValue();
		}
		return null;
	}

	// CreateAuto

	public void buildAuto(String fileName) {
		try {
			am = FileIO.readFile(fileName);
			if (am == null)
				return;

			automobiles.put(am.getModel(), am);
		} catch (AutoException e) {
			e.printErrorMessage();
			e.dumpToErrorLogFile();
			e.fix();
		}
	}

	public void printAuto(String modelName) {
		Automobile automobile = findModel(modelName);
		if (automobile != null)
			automobile.print();
	}

	// UpdateAuto

	public void updateOptionSetName(String modelName, String optionSetName, String newName) {
		am = this.findModel(modelName);
		am.updateOptionSetName(optionSetName, newName);
	}

	public void updateOptionPrice(String modelName, String optionSetName, String optionName, float newPrice) {
		am = this.findModel(modelName);
		am.updateOptionPrice(optionSetName, optionName, newPrice);
	}

	public void updateOptionChoice(String modelName, String optionSetName, String optionName) {
		am = this.findModel(modelName);
		am.setOptionChoice(optionSetName, optionName);
	}

	// FixAuto

	public void fixMissingPrice() {
		am.setBasePrice(DEFAULT_BASE_PRICE);
	}

	public void fixMissingOptionSetData() {
	}

	public void fixMissingOptionData() {
	}

	public void fixMissingFileName() {
		am = null;
	}

	public void fixMissingMake() {
		am.setMake(DEFAULT_MAKE);
	}
}
