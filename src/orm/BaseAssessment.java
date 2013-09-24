package orm;

import java.util.List;

public class BaseAssessment {
    private String assessmentId;
    private String name;
    private Unit parentUnit;
    private double mark;
    private double unitPercent;
    private double coursePercent;
    private List<SubAssessment> subAssessments;
    
    public BaseAssessment() {
        // Get DB connection.
    }
    
    public String getAssessmentId() {
        return assessmentId;
    }
    
    public void setAssessmentId(String assessmentId) {
        this.assessmentId = assessmentId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Unit getParentUnit() {
        return parentUnit;
    }
    
    public void setParentUnit(Unit parentUnit) {
        this.parentUnit = parentUnit;
    }
    
    public double getMark() {
        return mark;
    }
    
    public void setMark(double mark) {
        this.mark = mark;
    }
    
    public double getUnitPercent() {
        return unitPercent;
    }
    
    public void setUnitPercent(double unitPercent) {
        this.unitPercent = unitPercent;
    }
    
    public double getCoursePercent() {
        return coursePercent;
    }
    
    public void setCoursePercent(double coursePercent) {
        this.coursePercent = coursePercent;
    }
    
    public List<SubAssessment> getSubAssessments() {
        return subAssessments;
    }
    
    public void setSubAssessments(List<SubAssessment> subAssessments) {
        this.subAssessments = subAssessments;
    }
}
