package scale;

import adapter.BuildAuto;
import adapter.EditAuto;

public class EditOption extends Thread {

	public static final int UPDATE_OPTION_PRICE = 0;
	public static final int UPDATE_OPTION_CHOICE = 1;
	private static EditAuto editAuto = new BuildAuto();

	private String modelName;
	private String threadID;

	private boolean forceSync;
	private int pattern;
	private String optionSetName;
	private String optionName;
	private float newPrice;

	public EditOption() {}

	public EditOption(String modelName, String threadID, boolean forceSync) {
		super(threadID);
		this.modelName = modelName;
		this.threadID = threadID;
		this.forceSync = forceSync;
	}

	public void updateOptionPrice(String optionSetName, String optionName, float newPrice) {
		this.optionSetName = optionSetName;
		this.optionName = optionName;
		this.newPrice = newPrice;
		this.pattern = UPDATE_OPTION_PRICE;
	}

	public void updateOptionChoice(String optionSetName, String optionName) {
		this.optionSetName = optionSetName;
		this.optionName = optionName;
		this.pattern = UPDATE_OPTION_CHOICE;
	}

	@Override
	public void run() {
		if (forceSync) {
			if (!editAuto.waitUntilModelIsAvailable(this.modelName, this.threadID))
				return;
		} else {
			editAuto.setCurrentAm(modelName);
		}

		System.out.println("[" + this.threadID + "] starts working");
		if (this.pattern == UPDATE_OPTION_CHOICE) {
			editAuto.editChoice(optionSetName, optionName);
		} else if (this.pattern == UPDATE_OPTION_PRICE) {
			editAuto.editOptionPrice(optionSetName, optionName, newPrice);
		}
		System.out.println("[" + this.threadID + "] finished updating");
		editAuto.printEditedModel(this.threadID);
		System.out.println("[" + this.threadID + "] finished printing");
		editAuto.closeEditing();
	}
}
