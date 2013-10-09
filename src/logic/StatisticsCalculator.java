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
     * Method to return the list of units whose codes match the unit being
	 * searched for
	 *
     * @curUnit is the unit that is being looked for
     * @return a list of the unit objects with the same unit code as curUnit
     */
	 
	 public static List<Unit> findUnits(Unit curUnit){

		List<Unit> unitList = new ArrayList<Unit>();
		
		for (int i = 0; i < CohortData.numUnits; i++) {
		
			if (curUnit.getCode() == CohortData.units.get(i).getCode()) {
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
     * Method to return the list of subassessments whose IDs match the subassessment being
	 * searched for
	 *
     * @curSubAssess is the subassessment that is being looked for
     * @return a list of the subassessment objects with the same subassessmentID as curSubAssess
     */
    
    public static List<SubAssessment> findSubAssessments(SubAssessment curSubAssess){

		List<SubAssessment> subAssessList = new ArrayList<SubAssessment>();
		
		List<Assessment> assessList = findAssessments(curSubAssess.getParentAssessment());
		
		for (int i = 0; i < assessList.size(); i++) {
			Assessment curAssess = assessList.get(i);
			
			for (int j = 0; j <curAssess.getSubAssessments().size(); j++){
				
				if (curSubAssess.getSubAssessmentID() ==
							curAssess.getSubAssessments().get(j).getSubAssessmentID()) {
					subAssessList.add(curAssess.getSubAssessments().get(j));
				}
			}
			
		}
		return subAssessList;
	}

    /**
     * Method to find the Cohort's average over a subassessment
     * 
     * @curSubAssess is the subassessment for which the average is being found
     * @return the subassessment's average, as a string to 2 decimal places
     */
    
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
	
	/**
     * Method to find the Cohort's range over a subassessment
     * 
     * @curSubAssess is the subassessment for which the range is being found
     * @return the subassessment's range, as a string to 2 decimal places in the form "min - max"
     */
    
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
