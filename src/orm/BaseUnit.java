package orm;

import java.util.List;

public class BaseUnit {
    private String code;
    private String name;
    private int points;
    private int mark;
    private List<Assessment> assessments;
    
    public BaseUnit() {
        // Get DB connection.
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
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
    
    public int getMark() {
        return mark;
    }
    
    public void setMark(int mark) {
        this.mark = mark;
    }
    
    public List<Assessment> getAssessments() {
        return assessments;
    }
    
    public void setAssessments(List<Assessment> assessments) {
        this.assessments = assessments;
    }
}
