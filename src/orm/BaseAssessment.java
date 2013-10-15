package orm;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

import sessionControl.Session;

public class BaseAssessment {
	private int assessmentID;
    public StringBuffer name = new StringBuffer (30);
    private Unit parentUnit;
    public StringBuffer mark = new StringBuffer (6);
    public StringBuffer unitPercent = new StringBuffer (6);
    private List<SubAssessment> subAssessments;
    
    public BaseAssessment(int assessmentID) {
    	try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet assessmentRS = s.executeQuery("SELECT * FROM Assessment WHERE AssessmentID=" + assessmentID)) {
            
            // There will only be one assessment returned as assessmentID is unique
    		// Called with this constructor, no data about marks will exist
    		while (assessmentRS.next()) {
	    		
	            setAssessmentID(assessmentID);
	            
	            setName(assessmentRS.getString("AssessmentName"));
	            setUnitPercent(assessmentRS.getInt("UnitPercent"));
	            //this.parentUnit = new Unit(assessmentRS.getString("UnitCode"));
    		}
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
    	this.subAssessments = new ArrayList<>();
    	
    	try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet assessmentRS = s.executeQuery("SELECT * FROM Assessment WHERE AssessmentID=" + assessmentID)) {
            
            // There will only be one assessment returned as assessmentID is unique
    		// Called with this constructor, no data about marks will exist
    		while (assessmentRS.next()) {
            
	    		setAssessmentID(assessmentID);
	            
	            setName(assessmentRS.getString("AssessmentName"));
	            setUnitPercent(assessmentRS.getInt("UnitPercent"));
	            this.parentUnit = unit;
    		}
        } catch (SQLException ex) {
            Logger.getLogger(BaseAssessment.class.getName()).log(Level.SEVERE, null, ex);
        }
    	
    	try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet assessmarkRS = s.executeQuery("SELECT * FROM AssessmentMark WHERE AssessmentID=" + assessmentID + " AND StudentID=" + studentID)) {
            
            // Next, gets the unique AssessmentMark entry for this student-unit combination
    		while (assessmarkRS.next()) {
            
    			setMark(assessmarkRS.getDouble("Mark"));
    		}
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
    
    public BaseAssessment(int assessmentID, String name, Unit parentUnit, int unitPercent) throws SQLException {
        try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            s.execute("INSERT INTO Assessment(AssessmentName, UnitCode, UnitPercent) VALUES ('" + name + "', '" + parentUnit.getUnitCode() + "', " + unitPercent + ")");
            
            // TODO: Wait if we have autogenerated AssessmentID then how do we initialise this?
            
            setAssessmentID(assessmentID);
            setName(name);
            setParentUnit(parentUnit);
            setUnitPercent(unitPercent);
	        
        } catch (SQLException ex) {
            Logger.getLogger(BaseStudent.class.getName()).log(Level.SEVERE, null, ex);
            throw new SQLException(ex.getMessage());
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
    	this.name.replace(0, this.name.capacity(), name);
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
    	this.mark.replace(0, this.mark.capacity(),  Double.toString(mark));
    }
    
    public double getUnitPercent() {
    	return Integer.parseInt(unitPercent+"");
    }
    
    public void setUnitPercent(int unitPercent) {
    	this.unitPercent.replace(0, this.unitPercent.capacity(),  Integer.toString(unitPercent));
    }
    
    public List<SubAssessment> getSubAssessments() {
        return subAssessments;
    }
    
    public void setSubAssessments(List<SubAssessment> subAssessments) {
        this.subAssessments = subAssessments;
    }
}
