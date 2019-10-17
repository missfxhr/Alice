package exception;

public class AutoException extends Exception {
	private static final String[][] message = new String[10][];
	private static final int MAX_CODE_NUM = 100;
	private static final int MODEL_PACKAGE = 0;
	static {
		message[MODEL_PACKAGE] = new String[MAX_CODE_NUM];

		message[MODEL_PACKAGE][0] = "Missing price for Automobile in text file.";
		message[MODEL_PACKAGE][1] = "Missing OptionSet data (or part of it).";
		message[MODEL_PACKAGE][2] = "Missing Option data.";
		message[MODEL_PACKAGE][3] = "Missing filename or wrong filename.";
		message[MODEL_PACKAGE][4] = "Missing make for Automobile in text file.";
	}

	private static final long serialVersionUID = 4464712018613675439L;
	private int errorNum;

	public AutoException() {}

	public AutoException(int errorNum) {
		this.errorNum = errorNum;
	}

	public int getPackageNum() {
		return this.errorNum / MAX_CODE_NUM;
	}

	public int getCodeNum() {
		return this.errorNum % MAX_CODE_NUM;
	}

	public String getErrorMessage() {
		int packageNum = this.getPackageNum();
		int codeNum = this.getCodeNum();
		return message[packageNum][codeNum];
	}

	public void fix() {
		if (this.getPackageNum() == MODEL_PACKAGE)
			FixModelHelper.fix(this.getCodeNum());
	}
}
