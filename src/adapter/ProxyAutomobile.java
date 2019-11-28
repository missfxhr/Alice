package adapter;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import client.DefaultSocketClient;

import java.util.Properties;
import java.util.Set;

import exception.AutoException;
import model.*;
import server.DefaultServerSocket;
import util.FileIO;

public abstract class ProxyAutomobile {
	private static final String DEFAULT_MAKE = "Ford";
	private static final float DEFAULT_BASE_PRICE = 18445;
	
	// This LinkedHashMap stores different model instances
	// key: model instance with a unique model name
	// value: name of the Thread ID which is currently occupying this model instance, null if vacant
	private static final LinkedHashMap<Automobile, String> automobiles = new LinkedHashMap<Automobile, String>();

	// This refers to the model instance that current thread is modifying
	private Automobile am = null;

	// utils

	public Automobile findModel(String modelName) {
		Set<Entry<Automobile, String>> entrySet = automobiles.entrySet();
		Iterator<Entry<Automobile, String>> it = entrySet.iterator();
		while (it.hasNext()) {
			Entry<Automobile, String> entry = it.next();
			if (!entry.getKey().getModel().contentEquals(modelName))
				continue;

			return entry.getKey();
		}
		return null;
	}

	public void printAuto(String modelName) {
		Automobile automobile = findModel(modelName);
		if (automobile != null)
			automobile.print();
	}

	public String getAllModels() {
		StringBuilder sb = new StringBuilder();
		int i = 1;
		for (Automobile am : automobiles.keySet()) {  
			sb.append(i);
			sb.append(".");
			sb.append("\r\n");
			sb.append(am.toString());
			sb.append("\r\n");
			i ++;
        }
		return sb.toString();
	}

	public Automobile getAutomobileByIndex(int idx) {
		int i = 1;
		for (Automobile am : automobiles.keySet()) {  
			if (idx == i)
				return am;
			
			i ++;
        }
		return null;
	}
	
	// CreateAuto

	public void buildAuto(String fileName) {
		try {
			Automobile automobile = FileIO.readFile(fileName);
			if (automobile == null)
				return;

			automobiles.put(automobile, null);
		} catch (AutoException e) {
			e.printErrorMessage();
			e.dumpToErrorLogFile();
			e.fix();
		}
	}
	
	public void buildAuto(Properties props) {
		try {
			String carMake = props.getProperty("CarMake");
			String carModel = props.getProperty("CarModel");
			float carBasePrice = Float.valueOf(props.getProperty("CarBasePrice"));
			Automobile automobile = new Automobile(carMake, carModel, carBasePrice);
			int i = 1;
			while(props.containsKey("Option" + i)) {
				String optionSetName = props.getProperty("Option" + i);
				automobile.createOptionSet(optionSetName);
				int j = 1;
				while (props.containsKey(("OptionValue" + i) + ((char) (j - 1 + 'a')))) {
					String optionName = props.getProperty(("OptionValue" + i) + ((char) (j - 1 + 'a')));
					float optionPrice = Float.valueOf(props.getProperty(("OptionPrice" + i) + ((char) (j - 1 + 'a'))));
					automobile.createOption(optionSetName, optionName, optionPrice);
					j ++;
				}
				i ++;
			}			
			automobiles.put(automobile, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// UpdateAuto

	public void updateOptionSetName(String modelName, String optionSetName, String newName) {
		Automobile automobile = this.findModel(modelName);
		if (automobile == null)
			return;

		automobile.updateOptionSetName(optionSetName, newName);
	}

	public void updateOptionPrice(String modelName, String optionSetName, String optionName, float newPrice) {
		Automobile automobile = this.findModel(modelName);
		if (automobile == null)
			return;

		automobile.updateOptionPrice(optionSetName, optionName, newPrice);
	}

	public void updateOptionChoice(String modelName, String optionSetName, String optionName) {
		Automobile automobile = this.findModel(modelName);
		if (automobile == null)
			return;

		automobile.setOptionChoice(optionSetName, optionName);
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

	// EditAuto (Supports MultiThread)

	// This is the place where the key logic locates
	// We allow one thread to access a model instance only if there is no other thread visiting it
	// If there is, current thread need to wait until notified
	public synchronized boolean waitUntilModelIsAvailable(String modelName, String threadID) {
		Set<Entry<Automobile, String>> entrySet = automobiles.entrySet();
		Iterator<Entry<Automobile, String>> it = entrySet.iterator();
		while (it.hasNext()) {
			Entry<Automobile, String> entry = it.next();
			if (!entry.getKey().getModel().contentEquals(modelName))
				continue;

			while (entry.getValue() != null) {
				try {
					wait();
				} catch (InterruptedException e)  {
	                Thread.currentThread().interrupt(); 
	                System.out.println("Thread interrupted."); 
	            }
			}
			this.am = entry.getKey();
			entry.setValue(threadID);
			return true;
		}
		return false;
	}

	public synchronized void editOptionPrice(String optionSetName, String optionName, float newPrice) {
		if (am == null)
			return;

		am.updateOptionPrice(optionSetName, optionName, newPrice);		
	}

	public synchronized void editChoice(String optionSetName, String optionName) {
		if (am == null)
			return;

		am.setOptionChoice(optionSetName, optionName);		
	}

	public synchronized void printEditedModel(String threadID) {
		if (am == null)
			return;

		am.print();
	}

	// One thread releases the lock when this method is called
	// In another word, one thread can finish as much edit as possible before it wants to release the lock
	public synchronized void closeEditing() {
		automobiles.put(am, null);
		am = null;
		notifyAll();
	}

	public void serve(int port) {
		// start server
		DefaultServerSocket server = new DefaultServerSocket(port);
		server.start();
	}
}
