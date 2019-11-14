package model;

import java.io.Serializable;
import java.util.ArrayList;

import model.OptionSet.Option;

public class Automobile implements Serializable { //This class will represent the Model.
	private static final long serialVersionUID = -1459660698127308486L;

	private String make;
	private String model;
	private float basePrice;
    private ArrayList<OptionSet> optionSets;
    private ArrayList<Option> choice;

	public Automobile() {}

    public Automobile(String make, String model, float basePrice) {
    	this.make = make;
    	this.model = model;
    	this.basePrice = basePrice;
    	this.optionSets = new ArrayList<OptionSet>();
    	this.choice = new ArrayList<Option>();
    }

    // setter

	public synchronized void setMake(String make) {
		this.make = make;
	}

	public synchronized void setModel(String model) {
		this.model = model;
	}

    public synchronized void setBasePrice(float basePrice) {
        this.basePrice = basePrice;
    }
    
	public synchronized void setOptionSets(ArrayList<OptionSet> optionSets) {
		this.optionSets = optionSets;
	}

	public synchronized void setChoice(ArrayList<Option> choice) {
		this.choice = choice;
	}
	
	// getter

	public synchronized String getMake() {
		return make;
	}

	public synchronized String getModel() {
		return model;
	}

	public synchronized float getBasePrice() {
		return this.basePrice;
	}

    public synchronized ArrayList<OptionSet> getOptionSets() {
		return this.optionSets;
	}

    public synchronized ArrayList<Option> getChoice() {
		return this.choice;
	}

    // Create

    public synchronized void createOptionSet(String name) {
    	this.optionSets.add(new OptionSet(name));
    }

    public synchronized void createOption(String optionSetName, String optionName, float price) {
    	OptionSet optionSet = this.getOptionSet(optionSetName);
    	optionSet.createOption(optionName, price);
    }

    // Read

    public synchronized OptionSet getOptionSet(String name) {
    	for (OptionSet optionSet : this.getOptionSets()) {
    		if (optionSet.getName().contentEquals(name))
    			return optionSet;
    	}
    	return null;
    }

    public synchronized Option getOption(String optionSetName, String optionName) {
    	OptionSet optionSet = this.getOptionSet(optionSetName);
    	if (optionSet == null)
    		return null;

    	return optionSet.getOption(optionName);
    }

    public synchronized String getOptionChoice(String optionSetName) {
    	OptionSet optionSet = this.getOptionSet(optionSetName);
    	if (optionSet == null)
    		return null;

    	return optionSet.getOptionChoice().getName();
    }

    public synchronized float getOptionChoicePrice(String optionSetName) {
    	OptionSet optionSet = this.getOptionSet(optionSetName);
    	return optionSet.getOptionChoice().getPrice();
    }

    // update

    public synchronized void updateOptionSetName(String optionSetName, String newName) {
    	OptionSet optionSet = this.getOptionSet(optionSetName);
    	if (optionSet == null)
    		return;

    	optionSet.setName(newName);
    }

    public synchronized void updateOptionName(String optionSetName, String optionName, String newName) {
    	Option option = this.getOption(optionSetName, optionName);
    	if (option == null)
    		return;

    	option.setName(newName);
    }

    public synchronized void updateOptionPrice(String optionSetName, String optionName, float newPrice) {
    	Option option = this.getOption(optionSetName, optionName);
    	if (option == null)
    		return;

    	option.setPrice(newPrice);
    }

    public synchronized void setOptionChoice(String optionSetName, String optionName) {
    	OptionSet optionSet = this.getOptionSet(optionSetName);
    	if (optionSet == null)
    		return;

    	optionSet.setOptionChoice(optionName);
    	Option option = optionSet.getOptionChoice();
    	if (!this.choice.contains(option))
    		choice.add(option);
    }

    // delete

    public synchronized void deleteOptionSet(String optionSetName) {
    	OptionSet optionSet = this.getOptionSet(optionSetName);
    	if (optionSet == null)
    		return;

    	this.getOptionSets().remove(optionSet);
    }

    public synchronized void deleteOption(String optionSetName, String optionName) {
    	OptionSet optionSet = this.getOptionSet(optionSetName);
    	if (optionSet == null)
    		return;

    	optionSet.deleteOption(optionName);
    }

    // utils

    public synchronized String getName() {
    	StringBuilder sb = new StringBuilder();
    	sb.append(this.getMake());
        sb.append(" ");
        sb.append(this.getModel());
        return sb.toString();
    }

    public synchronized float getChoicePrice() {
    	float totalPrice = 0;
    	for (Option option : this.getChoice())
    		totalPrice += option.getPrice();
    	return totalPrice;
    }

    public synchronized float getTotalPrice() {
    	return this.getBasePrice() + this.getChoicePrice();
    }

    public synchronized String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("====== Model Details ======");
        sb.append("\r\n");
        sb.append("Make: ");
        sb.append(this.getMake());
        sb.append("\r\n");
        sb.append("Model: ");
        sb.append(this.getModel());
        sb.append("\r\n");
        sb.append("Base Price: $");
        sb.append(this.getBasePrice());
        sb.append("\r\n");
        sb.append("Number of Option Sets: ");
        sb.append(this.getOptionSets().size());
        sb.append("\r\n");
        sb.append("\r\n");
        sb.append("====== Option Set Details ======");
        sb.append("\r\n");
        for (OptionSet optionSet : this.getOptionSets()) {
            sb.append(optionSet.toString());
            sb.append("\r\n");
        }
        sb.append("====== User Selection Details ======");
        sb.append("\r\n");
        for (Option option : this.getChoice()) {
        	sb.append(option.toString());
        }
        sb.append("Choice Price: $");
        sb.append(this.getChoicePrice());
        sb.append("\r\n");
        sb.append("Total Price: $");
        sb.append(this.getTotalPrice());
        sb.append("\r\n");
        return sb.toString();
    }

    public synchronized void print() {
        System.out.println(this.toString());
    }
}
