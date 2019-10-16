package model;

import java.io.Serializable;

import model.OptionSet.Option;

public class Automobile implements Serializable { //This class will represent the Model.
	private static final long serialVersionUID = -1459660698127308486L;

	private String name;
	private float basePrice;
    private int optionNum;
    private OptionSet optionSets[];

    public Automobile() {}

    public Automobile(String name, float basePrice, int optionNum, int optionSetNum) {
    	this.name = name;
    	this.basePrice = basePrice;
    	this.optionNum = optionNum;
    	this.optionSets = new OptionSet[optionSetNum];
    }

    // setter

    public void setName(String name) {
        this.name = name;
    }

    public void setBasePrice(float basePrice) {
        this.basePrice = basePrice;
    }

    public void setOptionNum(int optionNum) {
    	this.optionNum = optionNum;
    }
    
	public void setOptionSets(OptionSet[] optionSets) {
		this.optionSets = optionSets;
	}

	// getter

    public String getName() {
		return this.name;
	}

	public float getBasePrice() {
		return this.basePrice;
	}

	public int getOptionNum() {
		return this.optionNum;
	}

    public OptionSet[] getOptionSets() {
		return this.optionSets;
	}

    // Create

    public void createOptionSet(int index, String name) {
    	this.optionSets[index] = new OptionSet(name, this.getOptionNum());
    }

    public void createOption(int optionSetIndex, int optionIndex, String name, String value, float price) {
    	OptionSet optionSet = this.getOptionSet(optionSetIndex);
    	optionSet.createOption(optionIndex, name, value, price);
    }

    // Read
    
    public OptionSet getOptionSet(int index) {
    	return this.optionSets[index];
    }

    public OptionSet getOptionSet(String name) {
    	for (int i = 0; i < this.getOptionSets().length; i ++) {
    		if (this.getOptionSets()[i].getName().contentEquals(name))
    			return this.getOptionSets()[i];
    	}
    	return null;
    }
    
    public Option getOption(int optionSetIndex, int optionIndex) {
    	return this.getOptionSet(optionSetIndex).getOption(optionIndex);
    }

    public Option getOption(String optionSetName, String optionName) {
    	OptionSet optionSet = this.getOptionSet(optionSetName);
    	if (optionSet == null)
    		return null;

    	return optionSet.getOption(optionName);
    }


    // update

    public void updateOptionSetName(String optionSetName, String newName) {
    	OptionSet optionSet = this.getOptionSet(optionSetName);
    	if (optionSet == null)
    		return;

    	optionSet.setName(newName);
    }

    public void updateOptionPrice(String optionSetName, String optionName, float newPrice) {
    	Option option = this.getOption(optionSetName, optionName);
    	if (option == null)
    		return;

    	option.setPrice(newPrice);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Model Name: ");
        sb.append(this.getName());
        sb.append("\r\n");
        sb.append("Base Price: ");
        sb.append(this.getBasePrice());
        sb.append("\r\n");
        sb.append("Number of Option Sets: ");
        sb.append(this.getOptionSets().length);
        sb.append("\r\n");
        sb.append("\r\n");
        sb.append("=== Option Set Details ===");
        sb.append("\r\n");
        for (int i = 0; i < this.getOptionSets().length; i ++) {
            sb.append("\r\n");
            sb.append(this.getOptionSets()[i].toString());
            sb.append("Option Set Total Price: $");
            sb.append(this.getBasePrice() + this.getOptionSets()[i].getOptionSetPrice());
            sb.append("\r\n");
        }
        return sb.toString();
    }

    public void print() {
        System.out.println(this.toString());
    }
}
