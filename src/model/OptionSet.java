package model;

import java.io.Serializable;

class OptionSet implements Serializable {
	private static final long serialVersionUID = 3165531069817902853L;
    private Option[] options;
    private String name;

    protected OptionSet() {}

    protected OptionSet(String name, int optionNum) {
        this.name = name;
        this.options = new Option[optionNum];
    }

    // setter

    protected void setName(String name) {
        this.name = name;
    }

    protected void setOptions(Option[] options) {
        this.options = options;
    }

    // getter

    protected String getName() {
        return this.name;
    }

    protected Option[] getOptions() {
        return this.options;
    }

    // create
    
    protected void createOption(int optionIndex, String name, String value, float price) {
    	this.getOptions()[optionIndex] = new Option(name, value, price);
    }

    // read
    
    protected Option getOption(int optionIndex) {
    	return this.options[optionIndex];
    }

    protected Option getOption(String optionName) {
    	for (int i = 0; i < this.getOptions().length; i ++) {
    		if (this.getOptions()[i].getName().contentEquals(optionName))
    			return this.getOptions()[i];
    	}
    	return null;
    }

    // update
    
    protected void setOption(int optionIndex, String name, String value, float price) {
        Option option = this.getOptions()[optionIndex];
        option.setName(name);
        option.setValue(value);
        option.setPrice(price);
    }

    // utils

    protected float getOptionSetPrice() {
        float result = 0;
        for (int i = 0; i < this.getOptions().length; i ++) {
            result += this.getOptions()[i].getPrice();
        }
        return result;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Option Set Name: ");
        sb.append(this.getName());
        sb.append("\r\n");
        for (int i = 0; i < this.getOptions().length; i ++) {
            sb.append(this.getOptions()[i].toString());
        }
        sb.append("Option Set Additional Price: $");
        sb.append(this.getOptionSetPrice());
        sb.append("\r\n");
        return sb.toString();
    }

    class Option implements Serializable {
		private static final long serialVersionUID = -1530653546780033372L;
		private String name;
        private String value;
        private float price;

        protected Option(String name, String value, float price) {
        	this.name = name;
        	this.value = value;
        	this.price = price;
        }

        // setter

        protected void setName(String name) {
            this.name = name;
        }

        protected void setValue(String value) {
            this.value = value;
        }

        protected void setPrice(float price) {
            this.price = price;
        }

        // getter

        protected String getName() {
            return this.name;
        }

        protected String getValue() {
            return this.value;
        }

        protected float getPrice() {
            return this.price;
        }

        public String toString() {
        	StringBuilder sb = new StringBuilder();
        	sb.append(this.getName());
            sb.append(": ");
            sb.append(this.getValue());
            sb.append("\r\n");
            return sb.toString();
        }

    }
}
