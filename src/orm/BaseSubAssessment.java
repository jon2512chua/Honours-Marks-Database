package orm;

import java.util.PriorityQueue;

public class BaseSubAssessment {
    private String subAssessmentId;
    private String name;
    private Assessment parentAssessment;
    private int maxMark;
    private PriorityQueue<Mark> marks;
    
    public BaseSubAssessment() {
        // Get DB connection.
    }
    
    public String getSubAssessmentId() {
        return subAssessmentId;
    }
    
    public void setSubAssessmentId(String subAssessmentId) {
        this.subAssessmentId = subAssessmentId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Assessment getParentAssessment() {
        return parentAssessment;
    }
    
    public void setParentAssessment(Assessment parentAssessment) {
        this.parentAssessment = parentAssessment;
    }
    
    public int getMaxMark() {
        return maxMark;
    }
    
    public void setMaxMark(int maxMark) {
        this.maxMark = maxMark;
    }
    
    public PriorityQueue<Mark> getMarks() {
        return marks;
    }
    
    public void setMarks(PriorityQueue<Mark> marks) {
        this.marks = marks;
    }
}
