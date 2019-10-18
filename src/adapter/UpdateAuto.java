package adapter;

public interface UpdateAuto {
	public void updateOptionSetName(String modelName, String optionSetName, String newName);
	public void updateOptionPrice(String modelName, String optionSetName, String optionName, float newPrice);
	public void updateOptionChoice(String modelName, String optionSetName, String optionName);
}
