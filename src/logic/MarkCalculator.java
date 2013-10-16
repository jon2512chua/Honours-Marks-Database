package logic;

import orm.*;

/**
 * Class that handles the automatic calculation of subassessment, assessment, and unit marks, from the
 * supplied subassessment marks from individual markers.
 */
public class MarkCalculator
{
    /**
     * Method to find the average of a subassessment's marks, including outliers.
     * 
     * @curSubAssess is the subassessment for which the average is being found
     * @return the subassessment's average, including outliers
     */
    
    public static double subAssessAverage(SubAssessment curSubAssess) {

        int size = curSubAssess.getMarks().size();
         
        double subAssessAve = 0;
        for (int i = 0; i < size; i++){

            subAssessAve += curSubAssess.getMarks().get(i).getValue();
  
        }
        
        subAssessAve = subAssessAve/size;
        return subAssessAve;
    }
    
    /**
     * Method to find the standard deviation of a subassessment's marks.
     * 
     * @curSubAssess is the subassessment for which the standard deviation is being found
     * @return the subassessment's standard deviation
     */
    public static double standardDeviationCalc(SubAssessment curSubAssess) {
        int size = curSubAssess.getMarks().size();
        
        double min = 100;//curSubAssess.getMarks().get(0).getValue();
        double max = 0;//min;
        
        double subAssessAve = subAssessAverage(curSubAssess);
        
        double standDev = 0;
        for (int i = 0; i < size; i++){
            
        	double curValue = curSubAssess.getMarks().get(i).getValue();
            double diff = (curValue - subAssessAve);
            standDev += (diff*diff);
            
            if (min > curValue){
            	min = curValue;
            }
            if (max < curValue){
            	max = curValue;
            }
            
        }
        standDev = standDev / size;
        standDev = Math.sqrt(standDev);
        curSubAssess.setStandDev(standDev);
        curSubAssess.setRange(min, max);
        
        return standDev;
    }  
  
    /**
     * Method to calculate which (if any) subassessment marks lie outside a range of two
     * standard deviations from the average of the marks, and if it is outside the range, sets the 
     * flag on that mark to indicate so.
     * 
     * @curSubAssess is the subassessment for which the outliers are being found and marked
     */
    public static void outlierMarks(SubAssessment curSubAssess){
        
        int size = curSubAssess.getMarks().size();
        double average = subAssessAverage(curSubAssess);
        double standDev = standardDeviationCalc(curSubAssess);

        for (int i = 0; i < size; i++){
  
                if ( (curSubAssess.getMarks().get(i).getValue()) > (average + 2*standDev) ||
                        (curSubAssess.getMarks().get(i).getValue()) < (average - 2*standDev) ) {
                    curSubAssess.getMarks().get(i).setInsideRange(false);
                } else {
                    curSubAssess.getMarks().get(i).setInsideRange(true);
                }

        }
        return;
    }
    
    
    /**
     * Method to find the average of a subassessment's marks, excluding outliers, and set it as
     * that subassessment's mark.
     * 
     * @curSubAssess is the subassessment for which the mark is being found
     * @return the subassessment's average, excluding outliers, which is the final subassessment mark
     */
    public static double subAssessMarkCalc(SubAssessment curSubAssess){
        
        int size = curSubAssess.getMarks().size();
         
        double subAssessAve = 0;
        int inRange = 0;
        for (int i = 0; i < size; i++){
            if (curSubAssess.getMarks().get(i).getInsideRange()){
                subAssessAve += curSubAssess.getMarks().get(i).getValue();
                inRange++;
            }
  
        }
        
        subAssessAve = subAssessAve/inRange;
        
        subAssessAve = (subAssessAve / curSubAssess.getMaxMark()) * 100 ;
        
        curSubAssess.setAveMark(subAssessAve);
        return subAssessAve;
    }
  
    
    
    /**
     * Method to calculate the mark for a student's assessment, based on all of the
     * subassessment marks and their respective weightings.
     * 
     * @curSubAssess is the assessment for which the mark is being found
     * @return the assessment's mark
     */
    public static double assessMarkCalc(Assessment curAssess){
    
        double assessmentMark = 0;
        int subAssessNum = curAssess.getSubAssessments().size();
        for (int i = 0; i<subAssessNum; i++){
        	SubAssessment curSubAssess = curAssess.getSubAssessments().get(i);
            assessmentMark += subAssessMarkCalc(curSubAssess) * curSubAssess.getAssessmentPercent() / 100;
        }
        
        curAssess.setMark(assessmentMark);
        
        return assessmentMark;
    }

    
    /**
     * Method to calculate the mark for an individual student's unit, based on all of the assessment
     * marks and their respective weightings.
     * 
     * @curUnit is the unit that the mark is being found for
     * @return the unit's mark
     */
    public static double unitMarkCalc(Unit curUnit){  
        double unitMark = 0;
        int assessNum = curUnit.getAssessments().size();
        
        for (int i = 0; i<assessNum; i++){
            unitMark += ( (curUnit.getAssessments().get(i).getMark())
                            * (curUnit.getAssessments().get(i).getUnitPercent()) / 100);
        }
        
        curUnit.setMark(unitMark);
        
        //not sure if we want this method to set the mark,
        //or another method to call this one and set the mark
        
        return unitMark;
        
    }
    
    /**
     * Method to iterate through a unit, calculating averages for sub assessments, then marks for assessments,
     * then the mark for the unit itself. Excludes outlier marks from sub assessments.
     * 
     * @curUnit is the unit that the mark is being found for
     * @return the unit's mark
     */
    public static double calculateWholeUnit(Unit curUnit){
        int assessNum = curUnit.getAssessments().size();
        
        int[] subAssessNum = new int[assessNum];
        for (int i = 0; i < assessNum; i++) {
            subAssessNum[i]= curUnit.getAssessments().get(i).getSubAssessments().size();
        }
        
        for (int i = 0; i < assessNum; i++) {
            for (int j = 0; j < subAssessNum[i]; j++) {
                outlierMarks( curUnit.getAssessments().get(i).getSubAssessments().get(j) );
                
            }
            assessMarkCalc( curUnit.getAssessments().get(i) );
        }

        return unitMarkCalc(curUnit);
    }
    
    /**
     * Method to iterate through all units taken by a student, and set the mark for each of them,
     * including all involved assessments and subassessments
     * 
     * @student is the student that is being iterated through
     */
    public static void calculateStudentMarks(Student curStudent){
        int unitNum = curStudent.getDiscipline().size();
        double total = 0;
        int points = 0;
        for (int i = 0; i < unitNum; i++) {
            calculateWholeUnit( curStudent.getDiscipline().get(i) );
            total = total + ( calculateWholeUnit( curStudent.getDiscipline().get(i) ) * curStudent.getDiscipline().get(i).getPoints());
            points = points + curStudent.getDiscipline().get(i).getPoints();
        }
        
        total = total/points;
        curStudent.setCourseMarks(total);
        return;
    }
    
        
    /**
     * Method that calculates the grade for a unit, based on the mark.
     * Currently ommitted due to no grade variable existing in the unit object in java, and I am not
     * sure if we need this method.
     * 
     * @curUnit is the unit that the mark is being found for
     * @return the unit's grade
     *
    public static void calculateUnitGrade(Unit curUnit){
        int unitMark = curUnit.getMark();
        switch (unitMark/10) {
                
            case 5:
                //mark = 50-59
                curUnit.setGrade("P");
            case 6:
                //mark = 60-69
                curUnit.setGrade("CR");
            case 7:
                //mark = 70-79
                curUnit.setGrade("D");
            case 8:
                //mark = 80-89
                curUnit.setGrade("HD");
            case 9:
                //mark = 90-99
                curUnit.setGrade("HD");
            case 10:
                //mark = 100
                curUnit.setGrade("HD");
            case default:
                //mark = 0-49
                if (unitMark > 44) {
                    curUnit.setGrade("N+");
                } else {
                curUnit.setGrade("N");
                }
                
        }
        
    }
    **/
}
