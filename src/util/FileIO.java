package util;

import java.io.*;

import exception.AutoException;
import model.Automobile;

public class FileIO {
	public static String BASE_PATH = "./resource/";
	public static String ATTRIBUTE_SPLITER = "\\|\\|\\|";
	public static String SER_FILE_TYPE = ".ser";

	public static Automobile readFile(String fileName) throws AutoException {
		Automobile am = null;
		try {
	        StringBuilder sb = new StringBuilder(BASE_PATH);
	        sb.append(fileName);
	        FileReader file = new FileReader(sb.toString());
	        BufferedReader buff = new BufferedReader(file);

	        String line = buff.readLine();
	        String[] attrArray = line.split(ATTRIBUTE_SPLITER);
	        am = new Automobile(attrArray[0], attrArray[1], Float.valueOf(attrArray[2]));
	        String optionSetName = null;
	        while (true) {
	        	line = buff.readLine();
	        	if (line == null)
	        		break;
	        	
	        	String[] optAttrArray = line.trim().split(ATTRIBUTE_SPLITER);
	        	if (optAttrArray.length == 1) {
	        		optionSetName = optAttrArray[0];
	        		am.createOptionSet(optionSetName);
	        	} else if (optAttrArray.length == 2) {
	        		am.createOption(optionSetName, optAttrArray[0], Float.valueOf(optAttrArray[1]));
	        	}
	        }
	        buff.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new AutoException(3);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
        	throw new AutoException(0);
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
