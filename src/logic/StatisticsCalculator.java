package logic;

import java.util.*;

import orm.*;

/**
 * Class that handles the automatic calculation of the statistics of CohortData marks, such as CohortData
 * averages and ranges over subassessments, assessments, units, and overall student marks, 
 */
public class StatisticsCalculator
{
	/**
	 * Method to calculate the average of a subassessment over a cohort
	 *
	 * @subAssessID the ID of the subassessment that is being looked for
	 * @return the average for the cohort for the subassessment
	 */
	public static double subAssessAve(int subAssessID){

		SubAssessment current = null;

		for (SubAssessment s : CohortData.subassessments) {
			if (s.getSubAssessmentID() == subAssessID){
				current = s;
				break;
			}
			
		}
		if (current == null){
			return 0;
		}
		
		double ave = 0;
		int counted = 0;
		for (Mark m : current.getMarks()){
			ave = ave + m.getValue();
			counted++;
		}
		
		ave = ave/counted;
		return ave;
	}
	
	
	//old things, not sure how they're useful
	
	/**
	 * Method to return the list of units whose codes match the unit being
	 * searched for
	 *
	 * @curUnit is the unit that is being looked for
	 * @return a list of the unit objects with the same unit code as curUnit
	 */

	public static List<Unit> findUnits(Unit curUnit){

		List<Unit> unitList = new ArrayList<Unit>();

		for (int i = 0; i < CohortData.numUnits; i++) {

			if (curUnit.getUnitCode() == CohortData.units.get(i).getUnitCode()) {
				unitList.add(CohortData.units.get(i));
			}

		}

		return unitList;
	}

	/**
	 * Method to return the list of assessments whose IDs match the assessment being
	 * searched for
	 *
	 * @curAssess is the assessment that is being looked for
	 * @return a list of the assessment objects with the same assessmentID as curAssess
	 */

	public static List<Assessment> findAssessments(Assessment curAssess){

		List<Assessment> assessList = new ArrayList<Assessment>();

		for (int i = 0; i < CohortData.numAssessments; i++) {

			if (curAssess.getAssessmentID() == CohortData.assessments.get(i).getAssessmentID()) {
				assessList.add(CohortData.assessments.get(i));
			}

		}

		return assessList;
	}

	
	/**
	 * Method to find the Cohort's average over a subassessment
	 * 
	 * @curSubAssess is the subassessment for which the average is being found
	 * @return the subassessment's average, as a string to 2 decimal places
	 */
/*
	public static String subAssessAve(SubAssessment curSubAssess){

		List<SubAssessment> subAssessList = findSubAssessments(curSubAssess);
		double average = 0;
		int markNum = 0;

		for (int i = 0; i < subAssessList.size(); i++){
			average = average + ( MarkCalculator.subAssessMarkCalc( subAssessList.get(i) ) );
			markNum++;
		}

		average = average/markNum;
		String result = Double.toString(average);
		return result;

	}
*/
	/**
	 * Method to find the Cohort's range over a subassessment
	 * 
	 * @curSubAssess is the subassessment for which the range is being found
	 * @return the subassessment's range, as a string to 2 decimal places in the form "min - max"
	 */
/*
	public static String subAssessRange(SubAssessment curSubAssess){

		List<SubAssessment> subAssessList = findSubAssessments(curSubAssess);
		double min = 100;
		double max = 0;

		for (int i = 0; i < subAssessList.size(); i++){
			double curMark = MarkCalculator.subAssessMarkCalc( subAssessList.get(i) );

			if ( curMark < min ){
				min = curMark;
			}
			if ( curMark > max ) {
				max = curMark;
			}
		}

		String result = Double.toString(min) + "-" + Double.toString(max);
		return result;
	}
*/
	/**
	 * Method to find the Cohort's average over an assessment
	 * 
	 * @curAssess is the assessment for which the average is being found
	 * @return the assessment's average, as a string to 2 decimal places
	 */

	public static String assessAve(Assessment curAssess){

		List<Assessment> assessList = findAssessments(curAssess);
		double average = 0;
		int markNum = 0;

		for (int i = 0; i < assessList.size(); i++){
			average = average + assessList.get(i).getMark();
			markNum++;
		}

		average = average/markNum;
		String result = Double.toString(average);
		return result;

	}

	/**
	 * Method to find the Cohort's range over an assessment
	 * 
	 * @curAssess is the assessment for which the range is being found
	 * @return the assessment's range, as a string to 2 decimal places in the form "min - max"
	 */

	public static String assessRange(Assessment curAssess){

		List<Assessment> assessList = findAssessments(curAssess);
		double min = 100;
		double max = 0;

		for (int i = 0; i < assessList.size(); i++){
			double curMark = assessList.get(i).getMark();

			if ( curMark < min ){
				min = curMark;
			}
			if ( curMark > max ) {
				max = curMark;
			}
		}

		String result = Double.toString(min) + "-" + Double.toString(max);
		return result;

	}

	/**
	 * Method to find the Cohort's average over a unit
	 * 
	 * @curUnit is the unit for which the average is being found
	 * @return the unit's average, as a string to 2 decimal places
	 */
	public static String unitAve(Unit curUnit){

		List<Unit> unitList = findUnits(curUnit);
		double average = 0;
		int markNum = 0;

		for (int i = 0; i < unitList.size(); i++){
			average = average + unitList.get(i).getMark();
			markNum++;
		}

		average = average/markNum;
		String result = Double.toString(average);
		return result;

	}

	/**
	 * Method to find the Cohort's range over a unit
	 * 
	 * @curUnit is the unit for which the average is being found
	 * @return the unit's range, as a string to 2 decimal places in the form "min - max"
	 */

	public static String unitRange(Unit curUnit){

		List<Unit> unitList = findUnits(curUnit);
		double min = 100;
		double max = 0;

		for (int i = 0; i < unitList.size(); i++){
			double curMark = unitList.get(i).getMark();

			if ( curMark < min ){
				min = curMark;
			}
			if ( curMark > max ) {
				max = curMark;
			}
		}

		String result = Double.toString(min) + "-" + Double.toString(max);
		return result;

	}

}
