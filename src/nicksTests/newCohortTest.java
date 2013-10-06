package nicksTests;

import newCohort.CohortCreator;
/**
 * Nick's testing class for new cohort functionality
 * @author nickos
 *
 */
public class newCohortTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if(CohortCreator.create("20199")) {
			CohortCreator.installData();
		}
		
	}

}
