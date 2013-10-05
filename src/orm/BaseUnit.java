package orm;

import java.util.List;

public class BaseUnit {
    private String unitCode;
    private String name;
    private int points;
    private double mark;
    private List<Assessment> assessments;
    
    public BaseUnit(String unitCode) {
        // Get DB connection.
    }
    
    public String getUnitCode() {
        return unitCode;
    }
    
    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getPoints() {
        return points;
    }
    
    public void setPoints(int points) {
        this.points = points;
    }
    
    public double getMark() {
        return mark;
    }
    
    public void setMark(double mark) {
        this.mark = mark;
    }
    
    public List<Assessment> getAssessments() {
        return assessments;
    }
    
    public void setAssessments(List<Assessment> assessments) {
        this.assessments = assessments;
    }
}
