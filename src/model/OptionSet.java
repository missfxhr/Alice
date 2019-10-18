package model;

import java.io.Serializable;
import java.util.ArrayList;

class OptionSet implements Serializable {
	private static final long serialVersionUID = 3165531069817902853L;
    private ArrayList<Option> options;
    private String name;
    private Option choice = null;

    protected OptionSet() {}

    protected OptionSet(String name) {
        this.name = name;
        this.options = new ArrayList<Option>();
    }

    // setter

    protected void setName(String name) {
        this.name = name;
    }

    protected void setOptions(ArrayList<Option> options) {
        this.options = options;
    }

    protected void setChoice(Option choice) {
        this.choice = choice;
    }

    // getter

    protected String getName() {
        return this.name;
    }

    protected ArrayList<Option> getOptions() {
        return this.options;
    }

    protected Option getChoice() {
        return this.choice;
    }

    // create
    
    protected void createOption(String name, float price) {
    	this.getOptions().add(new Option(name, price));
    }

    // read

    protected Option getOption(String optionName) {
    	for (Option option : this.getOptions()) {
    		if (option.getName().contentEquals(optionName))
    			return option;
    	}
    	return null;
    }

    protected Option getOptionChoice() {
    	return this.getChoice();
    }

    // update
    
    protected void setOption(String optionName, String name, float price) {
        Option option = this.getOption(optionName);
        option.setName(name);
        option.setPrice(price);
    }

    protected void setOptionChoice(String optionName) {
    	Option option = this.getOption(optionName);
    	this.setChoice(option);
    }

    // delete
    
    protected void deleteOption(String optionName) {
    	Option option = this.getOption(optionName);
    	if (option == null)
    		return;

    	this.getOptions().remove(option);
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Option Set Name: ");
        sb.append(this.getName());
        sb.append("\r\n");
        for (Option option : this.getOptions()) {
            sb.append(option.toString());
        }
        return sb.toString();
    }

    class Option implements Serializable {
		private static final long serialVersionUID = -1530653546780033372L;
		private String name;
        private float price;

        protected Option(String name, float price) {
        	this.name = name;
        	this.price = price;
        }

        // setter

        protected void setName(String name) {
            this.name = name;
        }

        protected void setPrice(float price) {
            this.price = price;
        }

        // getter

        protected String getName() {
            return this.name;
        }

        protected float getPrice() {
            return this.price;
        }

        public String toString() {
        	StringBuilder sb = new StringBuilder();
        	sb.append(this.getName());
            sb.append(": $");
            sb.append(this.getPrice());
            sb.append("\r\n");
            return sb.toString();
        }
    }
}
