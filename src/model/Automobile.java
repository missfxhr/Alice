package model;

import java.io.Serializable;

public class Automobile implements Serializable { //This class will represent the Model.
	private static final long serialVersionUID = -1459660698127308486L;
	public static int MAX_SET_NUM = 168;

	private String name;
	private float basePrice;
    private OptionSet opset[];
    private int optionSetNum;
    private String[][] optionDetails;

    public Automobile() {}

    public void setName(String name) {
        this.name = name;
    }

    public void setBasePrice(float basePrice) {
        this.basePrice = basePrice;
    }

	public void setOpset(OptionSet[] opset) {
		this.opset = opset;
	}

    public void setOptionDetails(String[][] optionDetails) {
        this.optionDetails = optionDetails;
    }

    public void setOptionSet(int index, String[] optionArray) {
        opset[index] = new OptionSet(optionArray[0], optionNames.length);
        optionSetNum ++;
    }

    public void setOptions(int index, String[] optionArray) {
        opset[index].setOptions(optionNames, optionArray);
    }

    public String getName() {
		return name;
	}

	public float getBaseprice() {
		return baseprice;
	}

	public String[] getOptionNames() {
		return optionNames;
	}

    public OptionSet[] getOpset() {
		return opset;
	}
	
    public int getOptionSetNum() {
		return optionSetNum;
	}

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Model Name: ");
        sb.append(this.name);
        sb.append("\r\n");
        sb.append("Base Price: ");
        sb.append(this.baseprice);
        sb.append("\r\n");
        sb.append("Number of Option Sets: ");
        sb.append(this.optionSetNum);
        sb.append("\r\n");
        sb.append("\r\n");
        sb.append("=== Option Set Details ===");
        sb.append("\r\n");
        for (int i = 0; i < optionSetNum; i ++) {
            sb.append("\r\n");
            sb.append(opset[i].toString());
            sb.append("Option Set Total Price: $");
            sb.append(this.baseprice + opset[i].getOptionSetPrice());
            sb.append("\r\n");
        }
        return sb.toString();
    }

    public void print() {
        System.out.println(this.toString());
    }
}
