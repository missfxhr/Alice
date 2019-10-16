package util;

import java.io.*;

import model.Automobile;

public class FileIO {
	public static String BASE_PATH = "./resource/";
	public static String ATTRIBUTE_SPLITER = "\\|\\|\\|";
	public static String SER_FILE_TYPE = ".ser";

	public static Automobile readFile(String fileName) {
		Automobile am = null;
		try {
	        StringBuilder sb = new StringBuilder(BASE_PATH);
	        sb.append(fileName);
	        FileReader file = new FileReader(sb.toString());
	        BufferedReader buff = new BufferedReader(file);
	
	        String line = buff.readLine();
	        String[] attrArray = line.split(ATTRIBUTE_SPLITER);
	        am = new Automobile(attrArray[0], Float.valueOf(attrArray[1]), Integer.valueOf(attrArray[2]), Integer.valueOf(attrArray[3]));
	    	int optionSetIndex = 0;
	        while (true) {
	        	line = buff.readLine();
	        	if (line == null)
	        		break;
	
	            am.createOptionSet(optionSetIndex, line);
	        	for (int optionIndex = 0; optionIndex < am.getOptionNum(); optionIndex ++) {
	        		line = buff.readLine();
	        		String[] optAttrArray = line.split(ATTRIBUTE_SPLITER);
	        		am.createOption(optionSetIndex, optionIndex, optAttrArray[0], optAttrArray[1], Float.valueOf(optAttrArray[2]));
	        	}
	        	optionSetIndex ++;
	        }
	        buff.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return am;
    }

	public static String serialize(Automobile am) {
		String fileName = null;
		try {
			StringBuilder sb = new StringBuilder(BASE_PATH);
			sb.append(am.getName());
			sb.append(SER_FILE_TYPE);
			fileName = sb.toString();
			FileOutputStream fileOutputStream = new FileOutputStream(fileName);
	        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(am);
	        objectOutputStream.flush();
	        objectOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return fileName;
	}

	public static Automobile deserialize(String fileName) {
		Automobile newAm = null;
		try {
			FileInputStream fileInputStream = new FileInputStream(fileName);
	        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
	        newAm = (Automobile) objectInputStream.readObject();
	        objectInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return newAm;
	}
}
