package adapter;

// This is an interface including set of APIs
// which are used to edit the Automobile object
// with support of multi-thread access

public interface EditAuto {
	public boolean waitUntilModelIsAvailable(String modelName, String threadID);
	public void setCurrentAm(String modelName);
	public void editOptionPrice(String optionSetName, String optionName, float newPrice);
	public void editChoice(String optionSetName, String optionName);
	public void printEditedModel(String threadID);
	public void closeEditing();
}
