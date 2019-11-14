package scale;

import adapter.BuildAuto;
import adapter.EditAuto;

public class EditOption extends Thread {

	private String modelName;
	private String threadID;
	private int pattern;

	public EditOption() {}

	public EditOption(String modelName, String threadID, int pattern) {
		super(threadID);
		this.modelName = modelName;
		this.threadID = threadID;
		this.pattern = pattern;
	}

	@Override
	public void run() {
		EditAuto ea = new BuildAuto();
		if (!ea.waitUntilModelIsAvailable(this.modelName, this.threadID))
			return;

		if (this.pattern == 1) {
			String optionSetName = "Brakes/Traction Control";
			String optionName = "ABS";
			ea.editChoice(optionSetName, optionName);
		} else if (this.pattern == 2) {
			String optionSetName = "Brakes/Traction Control";
			String optionName = "ABS";
			float newPrice = 600.0f;
			ea.editOptionPrice(optionSetName, optionName, newPrice);
		} else if (this.pattern == 3) {
			String optionSetName = "Power Moonroof";
			String optionName = "selected";
			float newPrice = 1000.0f;
			ea.editOptionPrice(optionSetName, optionName, newPrice);
		} 
		ea.printEditedModel(this.threadID);
		ea.closeEditing();
	}
}
