package exception;

import adapter.*;

public class FixModelHelper {
	public static void fix(int codeNum) {
		FixAuto fa = new BuildAuto();
		switch (codeNum) {
			// Missing price for Automobile in text file
			case 0:
				fa.fixMissingPrice();
				break;
			// Missing OptionSet data (or part of it)
			case 1:
				fa.fixMissingOptionSetData();
				break;
			// Missing Option data
			case 2:
				fa.fixMissingOptionData();
				break;
			// Missing filename or wrong filename
			case 3:
				fa.fixMissingFileName();
				break;
			// Missing make for Automobile in text file
			case 4:
				fa.fixMissingMake();
				break;
			default:
				break;
		}
	}
}
