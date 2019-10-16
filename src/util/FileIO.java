package util;

import java.io.*;

import model.Automobile;

public class FileIO {
	public static String BASE_PATH = "./resource/";
	public static String ATTRIBUTE_SPLITER = "\\|\\|\\|";
	public static String AUTOMOBILE_LINE_INDICATOR = "###";
	public static String OPTION_LINE_INDICATOR = "***";
	public static String OPTION_SET_DATA_LINE_INDICATOR = "===";
	public static String SER_FILE_TYPE = ".ser";

	public static Automobile readFile(String fileName) throws IOException {
        Automobile am = new Automobile();
        StringBuilder sb = new StringBuilder(BASE_PATH);
        sb.append(fileName);
        FileReader file = new FileReader(sb.toString());
        BufferedReader buff = new BufferedReader(file);
        int lineNum;
        while (true) {
            String line = buff.readLine();
            if (line == null)
                break;

            if (AUTOMOBILE_LINE_INDICATOR.contentEquals(line)) {
            	line = buff.readLine();
            	String[] attrArray = line.split(ATTRIBUTE_SPLITER);
            	am.setName(attrArray[0]);
            	am.setBaseprice(Float.valueOf(attrArray[1]));
            } else if (OPTION_LINE_INDICATOR.contentEquals(line)) {
            	line = buff.readLine();
            	am.setOp
            	am.setOptionNames(line.split(LINE_SPLITER));
            }
                
            else {
                String[] optionArray = line.split(LINE_SPLITER);
                am.setOptionSet(lineNum - 2, optionArray);
                am.setOptions(lineNum - 2, optionArray);
            }
        }
        buff.close();
        return am;
    }
	
	public static String serialize(Automobile am) throws IOException{
		StringBuilder sb = new StringBuilder(BASE_PATH);
		sb.append(am.getName());
		sb.append(SER_FILE_TYPE);
		String fileName = sb.toString();
		FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(am);
        objectOutputStream.flush();
        objectOutputStream.close();
        return fileName;
	}

	public static Automobile deserialize(String fileName) throws IOException, ClassNotFoundException {
		FileInputStream fileInputStream = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Automobile newAm = (Automobile) objectInputStream.readObject();
        objectInputStream.close();
        return newAm;
	}
}
