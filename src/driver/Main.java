package driver;

public class Main {

	public static void main(String[] args) {
		Driver driver = new Driver();
		// Each time make sure only run one of the following tests
		// Printed results show that sync updates succeeded but async's will not
		// For sync test, printed results will print 3 times of model detail, with each time only the current edit will be applied
		// For async test, printed results will also print 3 times of model detail, but each time more than one edits could be applied, which is data corruption
		driver.test4Sync();
//		driver.test4Async();
	}
}
