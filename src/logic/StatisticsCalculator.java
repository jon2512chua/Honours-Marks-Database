package logic;


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
	
	/**
	 * Method for finding the average of an assessment containing cohort data
	 * 
	 * @param assess Assessment being calculated for
	 */
	public static void assessAve(Assessment assess){

		double ave = 0;
		for (SubAssessment s : assess.getSubAssessments()) {
			ave = ave + (s.getAveMark()  / s.getMaxMark() *  s.getAssessmentPercent());
		}
		
		assess.setMark(ave);
		
		return;
	}
	
	public static void unitAve(Unit unit){
		
		double ave = 0;
		for (Assessment a : unit.getAssessments()) {
			ave = ave + (a.getMark() * a.getUnitPercent());
		}
		
		unit.setMark(ave/100);
		
		return;
	}
}