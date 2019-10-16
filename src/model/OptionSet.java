package model;

import java.io.Serializable;

class OptionSet implements Serializable {
	private static final long serialVersionUID = 3165531069817902853L;
    private Option opt [];
    private String name;
    protected OptionSet() {}
    protected OptionSet(String n, int size) {
        opt = new Option[size];
        name = n;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setOpt(Option[] opt) {
        this.opt = opt;
    }

    protected String getName() {
        return this.name;
    }

    protected Option[] getOpt() {
        return opt;
    }

    protected float getOptionSetPrice() {
        float result = 0;
        for (int i = 0; i < opt.length; i ++) {
            result += opt[i].getPrice();
        }
        return result;
    }

    protected void setOptions(String[] optionNames, String[] optionArray) {
        for (int i = 0; i < optionNames.length; i ++) {
            opt[i] = new Option();
            opt[i].setName(optionNames[i]);
            opt[i].setValue(Integer.valueOf(optionArray[i + 1]));
            opt[i].setPrice(opt[i].calcAddPrice());
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Option Set Name: ");
        sb.append(this.name);
        sb.append("\r\n");
        for (int i = 0; i < opt.length; i ++) {
            sb.append(opt[i].toString());
        }
        sb.append("Option Set Additional Price: $");
        sb.append(this.getOptionSetPrice());
        sb.append("\r\n");
        return sb.toString();
    }
    
    class Option implements Serializable {
		private static final long serialVersionUID = -1530653546780033372L;
		private String name;
        private float price;
        private int value;
    
        protected void setName(String name) {
            this.name = name;
        }

        protected void setValue(int value) {
            this.value = value;
        }

        protected void setPrice(float price) {
            this.price = price;
        }

        protected String getName() {
            return name;
        }

        protected int getValue() {
            return value;
        }

        protected float getPrice() {
            return this.price;
        }
        
        protected float calcAddPrice() {
            float result = 0;
            if ("Color".equals(name))
                result = 0;
            else if ("Transmission".equals(name))
                result = value == 0 ? 0 : 815;
            else if ("Brakes/Traction Control".equals(name))
                result = value == 0 ? 0 : value == 1 ? 400 : 1625;
            else if ("Side Impact Air Bags".equals(name))
                result = value == 0 ? 0 : 350;
            else if ("Power Moonroof".equals(name))
                result = value == 0 ? 0 : 595;

            return result;
        }

        protected String getOptionLabel(String optName, int value) {
            String result = "";
            if ("Color".equals(optName)) {
                if (value == 0)
                    result = "Fort Knox Gold Clearcoat Metallic";
                else if (value == 1)
                    result = "Liquid Grey Clearcoat Metallic";
                else if (value == 2)
                    result = "Infra-Red Clearcoat";
                else if (value == 3)
                    result = "Grabber Green Clearcoat Metallic";
                else if (value == 4)
                    result = "Sangria Red Clearcoat Metallic";
                else if (value == 5)
                    result = "French Blue Clearcoat Metallic";
                else if (value == 6)
                    result = "Twilight Blue Clearcoat Metallic";
                else if (value == 7)
                    result = "CD Silver Clearcoat Metallic";
                else if (value == 8)
                    result = "Pitch Black Clearcoat";
                else if (value == 9)
                    result = "Cloud 9 White Clearcoat"; 
            } else if ("Transmission".equals(optName))
                result = value == 0 ? "automatic" : "manual";
            else if ("Brakes/Traction Control".equals(optName))
                result = value == 0 ? "Standard" : value == 1 ? "ABS" : "ABS with Advance Tra";
            else if ("Side Impact Air Bags".equals(optName))
                result = value == 0 ? "present" : "not present";
            else if ("Power Moonroof".equals(optName))
                result = value == 0 ? "present" : "not present";

            return result;
        }
        
        public String toString() {
        	StringBuilder sb = new StringBuilder();
        	sb.append(this.getName());
            sb.append(": ");
            sb.append(this.getOptionLabel(this.getName(), this.getValue()));
            sb.append("\r\n");
            return sb.toString();
        }

    }
}
