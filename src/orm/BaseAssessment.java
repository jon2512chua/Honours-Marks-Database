package orm;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

import logic.StatisticsCalculator;
import sessionControl.Session;

/**
 * Base class representing an Assessment.
 * 
 * @author Jonathan Chua
 * @author Samuel Widenbar
 * @version 16/10/2013
 */
public class BaseAssessment {
    /**
     * The ID to identify the assessment.
     */
    private int assessmentID;
    
    /**
     * The name of the assessment, exists as a String in the database.
     */
    public StringBuffer name = new StringBuffer (30);
    
    /**
     * The unit this assessment belongs to.
     */
    private Unit parentUnit;
    
    /**
     * The amount a student scored for this assessment, exists as a Double.
     */
    public StringBuffer mark = new StringBuffer (6);
    
    /**
     * The percentage this assessment takes up in the unit, exists as an Integer in the database.
     */
    public StringBuffer unitPercent = new StringBuffer (6);
    
    /**
     * All the sub assessments belonging to this assessment.
     */
    private List<SubAssessment> subAssessments = new ArrayList<SubAssessment>();
    
    /**
     * Constructor to create a java object to represent an assessment found in the database.
     * 
     * @param assessmentID to identify the assessment
     */
    public BaseAssessment(int assessmentID) {
    	try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet assessmentRS = s.executeQuery("SELECT * FROM Assessment WHERE AssessmentID=" + assessmentID)) {
            
            // There will only be one assessment returned as assessmentID is unique
    		// Called with this constructor, no data about marks will exist
    		while (assessmentRS.next()) {
	    		setAssessmentID(assessmentID);
	            setName(assessmentRS.getString("AssessmentName"));
	            setUnitPercent(assessmentRS.getInt("UnitPercent"));
                setParentUnit(new Unit(assessmentRS.getString("UnitCode")));
    		}
        } catch (SQLException ex) {
            Logger.getLogger(BaseAssessment.class.getName()).log(Level.SEVERE, null, ex);
        }
    	
    	try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet subassessmentRS = s.executeQuery("SELECT * FROM SubAssessment WHERE AssessmentID=" + assessmentID)) {
            
            // Will be a list of subassessments returned, as many subassessments can belong to an assessment
    		// We are adding all of them to the list
            while (subassessmentRS.next()) {
            	SubAssessment nextSubAssess = new SubAssessment(subassessmentRS.getInt("SubAssessmentID"), (Assessment)this);
        		this.subAssessments.add(nextSubAssess);
            	
            }
            StatisticsCalculator.assessAve((Assessment)this);	
        } catch (SQLException ex) {
            Logger.getLogger(BaseAssessment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Constructor to create a java object to represent an assessment found in the database, belonging to the specified unit.
     * 
     * @param assessmentID to identify the assessment
     * @param unit the unit that this assessment belongs to
     */
    public BaseAssessment(int assessmentID, Unit unit) {
    	try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet assessmentRS = s.executeQuery("SELECT * FROM Assessment WHERE AssessmentID=" + assessmentID)) {
            
            // There will only be one assessment returned as assessmentID is unique
    		// Called with this constructor, no data about marks will exist
    		while (assessmentRS.next()) {
	    		
	            setAssessmentID(assessmentID);
	            
	            setName(assessmentRS.getString("AssessmentName"));
	            setUnitPercent(assessmentRS.getInt("UnitPercent"));
	            setParentUnit(unit);
    		}
        } catch (SQLException ex) {
            Logger.getLogger(BaseAssessment.class.getName()).log(Level.SEVERE, null, ex);
        }
    	
    	try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet subassessmentRS = s.executeQuery("SELECT * FROM SubAssessment WHERE AssessmentID=" + assessmentID)) {
            
            // Will be a list of subassessments returned, as many subassessments can belong to an assessment
    		// We are adding all of them to the list
            while (subassessmentRS.next()) {
            	SubAssessment nextSubAssess = new SubAssessment(subassessmentRS.getInt("SubAssessmentID"), (Assessment)this);
        		this.subAssessments.add(nextSubAssess);
            	
            }
            
            StatisticsCalculator.assessAve((Assessment)this);
        } catch (SQLException ex) {
            Logger.getLogger(BaseAssessment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Constructor to create a java object to represent an assessment found in the database, belonging to the specified unit and student.
     * 
     * @param assessmentID to identify the assessment
     * @param studentID to identify the student
     * @param unit the unit that the assessment belongs to
     */
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
	            setParentUnit(unit);
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
    
    /**
     * Constructor to create a java object to represent an assessment that doesn't exist yet in the database.
     * 
     * @param assessmentID to identify this assessment
     * @param name the name of this assessment
     * @param parentUnit the unit this assessment belongs to
     * @param unitPercent the percentage this assessment takes up in the unit
     * @throws SQLException when there is an error with the SQL statement
     */
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
    
    /**
     * Method to get the ID of this assessment.
     * 
     * @return the ID of this assessment
     */
    public int getAssessmentID() {
        return assessmentID;
    }
    
    /**
     * Method to set the ID of this assessment.
     * 
     * @param assessmentID the ID of this assessment
     */
    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }
    
    /**
     * Method to get the name of this assessment.
     * 
     * @return name of the assessment
     */
    public StringBuffer getName() {
        return name;
    }
    
    /**
     * Method to set the name of this assessment.
     * 
     * @param name the name of the assessment
     */
    public void setName(String name) {
    	this.name.replace(0, this.name.capacity(), name);
    }
    
    /**
     * Method to get the parent unit.
     * 
     * @return Unit object that this assessment belongs to
     */
    public Unit getParentUnit() {
        return parentUnit;
    }
    
    /**
     * Method to set the parent unit.
     * 
     * @param parentUnit Unit object that this assessment belongs to
     */
    public void setParentUnit(Unit parentUnit) {
    	
        this.parentUnit = parentUnit;
    }
    
    /**
     * Method to get the mark, or 0 if it is not a number
     * 
     * @return the mark a student got for this assessment
     */
    public double getMark() {
    	try {
    		return Double.parseDouble(mark+"");
    	}
    	catch (NumberFormatException e) {
    		return 0;
    	}
    }
    
    /**
     * Method to set the mark. If value passed is not a number, sets to empty string.
     * @param mark the mark a student got for this assessment
     */
    public void setMark(double mark) {
    	if (!Double.isNaN(mark)){
    		this.mark.replace(0, this.mark.capacity(),  Double.toString(mark));
	    	this.mark.setLength(5);
    	} else {
    		this.mark.replace(0, this.mark.capacity(),  " ");
	    	this.mark.setLength(1);
    	}
    }
    
    /**
     * Method to get the unit percent.
     * 
     * @return the percentage of the parent unit that this assessment takes up
     */
    public double getUnitPercent() {
    	return Integer.parseInt(unitPercent+"");
    }
    
    /**
     * Method to set the unit percent.
     * 
     * @param unitPercent the percentage of the parent unit that this assessment takes up
     */
    public void setUnitPercent(int unitPercent) {
    	this.unitPercent.replace(0, this.unitPercent.capacity(),  Integer.toString(unitPercent));
    }
    
    /**
     * Method to get sub assessments.
     * 
     * @return a list of all the sub assessments belonging to this assessment
     */
    public List<SubAssessment> getSubAssessments() {
        return subAssessments;
    }
    
    /**
     * Method to set the sub assessments.
     * 
     * @param subAssessments a list of all the sub assessments belonging to this assessment
     */
    public void setSubAssessments(List<SubAssessment> subAssessments) {
        this.subAssessments = subAssessments;
    }
}
