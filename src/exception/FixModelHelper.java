package exception;

import adapter.*;

public class FixModelHelper {
	public static void fix(int codeNum) {
		FixAuto fa = new BuildAuto();
		fa.fix(codeNum);
	}
}
