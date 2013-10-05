package logic;

import java.util.*;
import orm.*;

/**
 * Class that handles the automatic calculation of subassessment, assessment, and unit marks, from the
 * supplied subassessment marks from individual markers.
 */
public class testing
{
    /**
     * Declaring objects for testing purposes
     */
    public Unit testUnit1;
    public Unit testUnit2;
    public Assessment testAssess1;
    public Assessment testAssess2;
    public Assessment testAssess3;
    public SubAssessment testSub1;
    public SubAssessment testSub2;
    public SubAssessment testSub3;
    
    public Mark mark1;
    public Mark mark2;
    public Mark mark3;
    public Mark mark4;
    public Mark mark5;
    public Mark mark6;
    
    public List<Mark> markSet1;
    public List<Mark> markSet2;
    public List<SubAssessment> subList1;
    public List<SubAssessment> subList2;
    public List<Assessment> assessList1;
    public List<Assessment> assessList2;
    public List<Unit> testDisc1;
    
    public Student testStud1;
    
    /**
     * Initialising objects for testing purposes
     */
    public void initObjects(){
        
        testUnit1 = new Unit();
        testUnit2 = new Unit();
        testAssess1 = new Assessment();
        testAssess2 = new Assessment();
        testAssess3 = new Assessment();
        testSub1 = new SubAssessment();
        testSub2 = new SubAssessment();
        testSub3 = new SubAssessment();
        markSet1 = new ArrayList<Mark>();
        markSet2 = new ArrayList<Mark>();
        subList1 = new ArrayList<SubAssessment>();
        subList2 = new ArrayList<SubAssessment>();
        assessList1 = new ArrayList<Assessment>();
        assessList2 = new ArrayList<Assessment>();
        testStud1 = new Student();
        testDisc1 = new ArrayList<Unit>();
        
        mark1 = new Mark();
        mark2 = new Mark();
        mark3 = new Mark();
        mark4 = new Mark();
        mark5 = new Mark();
        mark6 = new Mark();
        mark1.setValue(39);
        mark2.setValue(40);
        mark3.setValue(41);
        mark4.setValue(50);
        mark5.setValue(40);
        mark6.setValue(41);
        
        markSet1.add(mark1);
        markSet2.add(mark1);
        markSet2.add(mark2);
        markSet2.add(mark3);
        markSet2.add(mark4);
        markSet2.add(mark5);
        markSet2.add(mark6);
        
        testSub1.setMarks(markSet1);
        testSub2.setMarks(markSet2);
        testSub3.setMarks(markSet1);
        testSub1.setMaxMark(100);
        testSub2.setMaxMark(50);
        testSub3.setMaxMark(50);
        
        subList1.add(testSub1);
        subList2.add(testSub2);
        subList2.add(testSub3);
        
        testAssess1.setSubAssessments(subList1);
        testAssess2.setSubAssessments(subList2);
        testAssess3.setSubAssessments(subList1);
        testAssess1.setUnitPercent(100);
        testAssess2.setUnitPercent(50);
        testAssess3.setUnitPercent(50);
        
        assessList1.add(testAssess1);
        assessList2.add(testAssess2);
        assessList2.add(testAssess3);
        
        testUnit1.setAssessments(assessList1);
        testUnit2.setAssessments(assessList2);
        
        testDisc1.add(testUnit1);
        testDisc1.add(testUnit2);
        
        testStud1.setDiscipline(testDisc1);
    }
    
    /**
        THIS METHOD WORKS ON ASSESSMENTS, NOW CHANGED, KEEPING THIS IN CASE
        I WANT TO USE IT AS A REFERENCE, WHEN ITERATING THROUGH ASSESSMENTS I GUESS????
    
    public double[] subAssessAverage(int x) {
        List<SubAssessment> curSubAssessmentsList;
        if (x==1){        
            curSubAssessmentsList = testAss1.getSubAssessments();
        } else{
            curSubAssessmentsList = testAss2.getSubAssessments();
        }
        
        if (curSubAssessmentsList == null) {
            return null;
         }
        int subAssessNum = curSubAssessmentsList.size();
        
        int[] size = new int[subAssessNum];
        
        for (int i = 0; i < subAssessNum; i++){
             size[i] = curSubAssessmentsList.get(i).getMarks().size();
        }
        
        double[] subAssessAves = new double[subAssessNum];
        for (int i = 0; i < subAssessNum; i++){
            for (int j = 0; j < size[i]; j++) {
                subAssessAves[i] += curSubAssessmentsList.get(i).getMarks().get(j).getValue();
            }
            subAssessAves[i] = subAssessAves[i]/size[i];
        }
        
        return subAssessAves;
    }
    **/
    
}