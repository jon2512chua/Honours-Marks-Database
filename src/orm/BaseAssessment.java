package orm;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import sessionControl.Session;

public class BaseAssessment {
	private int assessmentID;
    public StringBuffer name;
    private Unit parentUnit;
    public StringBuffer mark;
    public StringBuffer unitPercent;
    private List<SubAssessment> subAssessments;
    
    public BaseAssessment(int assessmentID) {
    	try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet assessmentRS = s.executeQuery("SELECT * FROM Assessment WHERE AssessmentID=" + assessmentID)) {
            
            // There will only be one assessment returned as assessmentID is unique
    		// Called with this constructor, no data about marks will exist
    		assessmentRS.first();
    		
            setAssessmentID(assessmentID);
            
            setName(assessmentRS.getString("AssessmentName"));
            setUnitPercent(assessmentRS.getInt("UnitPercent"));
            //this.parentUnit = new Unit(assessmentRS.getString("UnitCode"));
        } catch (SQLException ex) {
            Logger.getLogger(BaseAssessment.class.getName()).log(Level.SEVERE, null, ex);
        }
    	
    	try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet subassessmentRS = s.executeQuery("SELECT * FROM SubAssessment WHERE AssessmentID=" + assessmentID)) {
            
            // Will be a list of subassessments returned, as many subassessments can belong to an assessment
    		// We are adding all of them to the list
            while (subassessmentRS.next()) {
            	SubAssessment nextSubAssess = new SubAssessment(subassessmentRS.getInt("SubAssessmentID"));
	            this.subAssessments.add(nextSubAssess);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseAssessment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public BaseAssessment(int assessmentID, int studentID, Unit unit) {
    	try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet assessmentRS = s.executeQuery("SELECT * FROM Assessment WHERE AssessmentID=" + assessmentID)) {
            
            // There will only be one assessment returned as assessmentID is unique
    		// Called with this constructor, no data about marks will exist
    		assessmentRS.first();
            
    		setAssessmentID(assessmentID);
            
            setName(assessmentRS.getString("AssessmentName"));
            setUnitPercent(assessmentRS.getInt("UnitPercent"));
            this.parentUnit = unit;
        } catch (SQLException ex) {
            Logger.getLogger(BaseAssessment.class.getName()).log(Level.SEVERE, null, ex);
        }
    	
    	try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet assessmarkRS = s.executeQuery("SELECT * FROM AssessmentMark WHERE AssessmentID=" + assessmentID + " && StudentID=" + studentID)) {
            
            // Next, gets the unique AssessmentMark entry for this student-unit combination
    		assessmarkRS.first();
            
            setMark(assessmarkRS.getDouble("Mark"));
        } catch (SQLException ex) {
            Logger.getLogger(BaseAssessment.class.getName()).log(Level.SEVERE, null, ex);
        }
    	
    	try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet subassessmentRS = s.executeQuery("SELECT * FROM SubAssessment WHERE AssessmentID=" + assessmentID)) {
            
            // Will be a list of subassessments returned, as many subassessments can belong to an assessment
    		// We are adding all of them to the list
            while (subassessmentRS.next()) {
            	SubAssessment nextSubAssess = new SubAssessment(subassessmentRS.getInt("SubAssessmentID"), studentID, (Assessment)this);
	            this.subAssessments.add(nextSubAssess);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseAssessment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public BaseAssessment(int assessmentID, String name, Unit parentUnit, int unitPercent) {
        try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            s.execute("INSERT INTO Assessment VALUES ('" + assessmentID + "', '" + name + "', '" + parentUnit.getUnitCode() + "', " + unitPercent + ")");
            
            setAssessmentID(assessmentID);
            setName(name);
            setParentUnit(parentUnit);
            setUnitPercent(unitPercent);
        } catch (SQLException ex) {
            Logger.getLogger(BaseStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getAssessmentID() {
        return assessmentID;
    }
    
    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }
    
    public StringBuffer getName() {
        return name;
    }
    
    public void setName(String name) {
    	this.name.replace(0, this.name.length(), name);
    }
    
    public Unit getParentUnit() {
        return parentUnit;
    }
    
    public void setParentUnit(Unit parentUnit) {
        this.parentUnit = parentUnit;
    }
    
    public double getMark() {
    	return Double.parseDouble(mark+"");
    }
    
    public void setMark(double mark) {
    	this.mark.replace(0, this.mark.length(),  Double.toString(mark));
    }
    
    public double getUnitPercent() {
    	return Integer.parseInt(unitPercent+"");
    }
    
    public void setUnitPercent(int unitPercent) {
    	this.unitPercent.replace(0, this.unitPercent.length(),  Integer.toString(unitPercent));
    }
    
    public List<SubAssessment> getSubAssessments() {
        return subAssessments;
    }
    
    public void setSubAssessments(List<SubAssessment> subAssessments) {
        this.subAssessments = subAssessments;
    }
}
