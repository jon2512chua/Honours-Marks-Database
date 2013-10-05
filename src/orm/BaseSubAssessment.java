package orm;

import java.util.*;

public class BaseSubAssessment {
    private String subAssessmentID;
    private String name;
    private Assessment parentAssessment;
    private int maxMark;
    private List<Mark> marks;
    
    public BaseSubAssessment() {
        // Get DB connection.
    }
    
    public String getSubAssessmentID() {
        return subAssessmentID;
    }
    
    public void setSubAssessmentID(String subAssessmentID) {
        this.subAssessmentID = subAssessmentID;
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
    
    public List<Mark> getMarks() {
        return marks;
    }
    
    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }
}
