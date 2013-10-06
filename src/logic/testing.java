import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

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
    
    //
    
    public Unit statUnit1;
    public Unit statUnit2;
    public Unit statUnit3;
    
    public Assessment statAssess1;
    public Assessment statAssess2;
    public Assessment statAssess3;
    
    public SubAssessment statSub1;
    public SubAssessment statSub2;
    public SubAssessment statSub3;
    
    
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
        testUnit1.setPoints(6);
        testUnit2.setPoints(6);

        testDisc1.add(testUnit1);
        testDisc1.add(testUnit2);
        
        testStud1.setDiscipline(testDisc1);
        
        //
        
        
    }
    
    /**
     * Initialising objects for statistic generation testing
     */
    public void initStatObjects(){
        CohortData.units = new ArrayList<Unit>();
        
        CohortData.assessments = new ArrayList<Assessment>();
        
        statUnit1 = new Unit();
        statUnit2 = new Unit();
        statUnit3 = new Unit();
        
        statAssess1 = new Assessment();
        statAssess2 = new Assessment();
        statAssess3 = new Assessment();
        
        statSub1 = new SubAssessment();
        statSub2 = new SubAssessment();
        statSub3 = new SubAssessment();
        
        Mark mark7 = new Mark();
        Mark mark8 = new Mark();
        Mark mark9 = new Mark();
        mark7.setValue(55);
        mark8.setValue(65);
        mark9.setValue(70);
        mark7.setInsideRange(true);
        mark8.setInsideRange(true);
        mark9.setInsideRange(true);
        
        List<Mark> markSet4 = new ArrayList<Mark>();
        List<Mark> markSet5 = new ArrayList<Mark>();
        List<Mark> markSet6 = new ArrayList<Mark>();
        markSet4.add(mark7);
        markSet5.add(mark8);
        markSet6.add(mark9);
        
        statSub1.setMarks(markSet4);
        statSub2.setMarks(markSet5);
        statSub3.setMarks(markSet6);
        statSub1.setMaxMark(100);
        statSub2.setMaxMark(100);
        statSub3.setMaxMark(100);
        statSub1.setSubAssessmentId(1);
        statSub2.setSubAssessmentId(1);
        statSub3.setSubAssessmentId(2);
        
        statSub1.setParentAssessment(statAssess1);
        statSub2.setParentAssessment(statAssess1);
        statSub3.setParentAssessment(statAssess2);
        
        statUnit1.setMark(60);
        statUnit2.setMark(70);
        statUnit3.setMark(80);
        statUnit1.setCode("aaaa");
        statUnit2.setCode("aaaa");
        statUnit3.setCode("bbbb");
        
        statAssess1.setMark(60);
        statAssess2.setMark(70);
        statAssess3.setMark(80);
        statAssess1.setAssessmentId(1);
        statAssess2.setAssessmentId(1);
        statAssess3.setAssessmentId(2);
        
        List<SubAssessment> subListA = new ArrayList<SubAssessment>();
        List<SubAssessment> subListB = new ArrayList<SubAssessment>();
        List<SubAssessment> subListC = new ArrayList<SubAssessment>();
        subListA.add(statSub1);
        subListA.add(statSub2);
        subListA.add(statSub3);
        statAssess1.setSubAssessments(subListA);
        statAssess2.setSubAssessments(subListB);
        statAssess3.setSubAssessments(subListC);
        
        
        CohortData.units.add(statUnit1);
        CohortData.units.add(statUnit2);
        CohortData.units.add(statUnit3);
        
        CohortData.assessments.add(statAssess1);
        CohortData.assessments.add(statAssess2);
        CohortData.assessments.add(statAssess3);
        
        
        CohortData.numUnits = 3;
        CohortData.numAssessments = 3;
        
        
    }
    
}