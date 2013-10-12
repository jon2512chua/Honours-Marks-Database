package orm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import sessionControl.Session;

public class BaseSubAssessment {

	private int subAssessmentID;
	public StringBuffer name = new StringBuffer (30);
    private Assessment parentAssessment;
    public StringBuffer maxMark = new StringBuffer (30);
    public StringBuffer assessmentPercent = new StringBuffer (30);
    private List<Mark> marks;
    // Maybe add average mark for this subassessment over all marks
    
    public BaseSubAssessment(int subAssessmentID) {
    	try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet subassessmentRS = s.executeQuery("SELECT * FROM SubAssessment WHERE SubAssessmentID=" + subAssessmentID)) {
            
            // There will only be one subAssessment returned as subAssessmentID is unique
    		// Called with this constructor, no data about marks will exist
    		subassessmentRS.first();
            
            setSubAssessmentID(subAssessmentID);
            
            setName(subassessmentRS.getString("SubAssessmentName"));
            setMaxMark(subassessmentRS.getInt("MaxMarks"));
            setAssessmentPercent(subassessmentRS.getInt("AssessmentPercent"));
            //this.parentAssessment = new Assessment(assessmentRS.getString("AssessmentID"));
            
        } catch (SQLException ex) {
            Logger.getLogger(BaseSubAssessment.class.getName()).log(Level.SEVERE, null, ex);
        }
    	
    }
    
    public BaseSubAssessment(int subAssessmentID, int studentID, Assessment assessment) {
    	try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet subassessmentRS = s.executeQuery("SELECT * FROM SubAssessment WHERE SubAssessmentID=" + subAssessmentID)) {
            
            // There will only be one subAssessment returned as subAssessmentID is unique
    		// Called with this constructor, no data about marks will exist
    		subassessmentRS.first();
            
            setSubAssessmentID(subAssessmentID);
            
            setName(subassessmentRS.getString("SubAssessmentName"));
            setMaxMark(subassessmentRS.getInt("MaxMarks"));
            setAssessmentPercent(subassessmentRS.getInt("AssessmentPercent"));
            this.parentAssessment = assessment;
        } catch (SQLException ex) {
            Logger.getLogger(BaseSubAssessment.class.getName()).log(Level.SEVERE, null, ex);
        }
    	
    	try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet subassessmentMarksRS = s.executeQuery("SELECT * FROM SubAssessmentMark WHERE SubAssessmentID=" + subAssessmentID)) {
            
            // Will be a list of subAssessments marks returned, as many subAssessments marks can belong to a subassessment
    		// We are adding all of them to the list
            while (subassessmentMarksRS.next()) {
            	Mark nextMark = new Mark(subassessmentMarksRS.getInt("SubAssessmentID"), studentID, subassessmentMarksRS.getInt("MarkerID"), (SubAssessment)this);
	            this.marks.add(nextMark);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseAssessment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getSubAssessmentID() {
        return subAssessmentID;
    }
    
    public void setSubAssessmentID(int subAssessmentID) {
        this.subAssessmentID = subAssessmentID;
    }
    
    public StringBuffer getName() {
        return name;
    }
    
    public void setName(String name) {
    	this.name.replace(0, this.name.length(), name);
    }
    
    public Assessment getParentAssessment() {
        return parentAssessment;
    }
    
    public void setParentAssessment(Assessment parentAssessment) {
        this.parentAssessment = parentAssessment;
    }
    
    public int getMaxMark() {
    	return Integer.parseInt(maxMark+"");
    }
    
    public void setMaxMark(int maxMark) {
    	this.maxMark.replace(0, this.maxMark.length(),  Integer.toString(maxMark));
    }
    
    public double getAssessmentPercent() {
    	return Integer.parseInt(assessmentPercent+"");
    }
    
    public void setAssessmentPercent (int assessmentPercent) {
    	this.assessmentPercent.replace(0, this.assessmentPercent.length(),  Integer.toString(assessmentPercent));
    }
    
    public List<Mark> getMarks() {
        return marks;
    }
    
    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }
}
