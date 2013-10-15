package orm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import sessionControl.Session;

public class BaseUnit {
    public StringBuffer unitCode = new StringBuffer (12);
    public StringBuffer name = new StringBuffer (50);
    public StringBuffer points = new StringBuffer (3);
    public StringBuffer mark = new StringBuffer (6);
    private List<Assessment> assessments = new ArrayList<Assessment>();
    
    /**
     * Method to create a java object to represent a unit found in the database
     * 
     * @param unitCode is the unit code of the unit being searched for
     */
    public BaseUnit(String unitCode) {
    	try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet unitRS = s.executeQuery("SELECT * FROM Unit WHERE UnitCode='" + unitCode+"'")) {
            
            // There will only be one unit returned as unitCode is unique
    		// Called with this constructor, no data about marks will exist

    		while (unitRS.next()) {
            
	            setUnitCode(unitCode);
	            
	            setName(unitRS.getString("UnitName"));
	            setPoints(unitRS.getInt("Points"));
    		}
        } catch (SQLException ex) {
            Logger.getLogger(BaseUnit.class.getName()).log(Level.SEVERE, null, ex);
        }
    	
    	try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet assessmentRS = s.executeQuery("SELECT * FROM Assessment WHERE UnitCode='" + unitCode + "'")) {
            
            // Will be a list of assessments returned, as many assessments can belong to a unit
    		// We are adding all of them to the list
            while (assessmentRS.next()) {
            	Assessment nextAssess = new Assessment(assessmentRS.getInt("AssessmentID"), (Unit) this);
	            this.assessments.add(nextAssess);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseUnit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Method to create a java object to represent a unit being taken by a particular student found in the database
     * 
     * @param unitCode the unit code of the unit being searched for
     * @param studentID the ID of the student
     */
    public BaseUnit(String unitCode, int studentID) throws SQLException {
    	this.assessments = new ArrayList<>();
    	
    	try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet unitRS = s.executeQuery("SELECT * FROM Unit WHERE UnitCode='" + unitCode + "'")) {
            
            // There will only be one unit returned as unitCode is unique
    		while (unitRS.next()) {
            
	            setUnitCode(unitCode);
	            
	            setName(unitRS.getString("UnitName"));
	            setPoints(unitRS.getInt("Points"));
    		}
        } catch (SQLException ex) {
            Logger.getLogger(BaseUnit.class.getName()).log(Level.SEVERE, null, ex);
            throw new SQLException(ex.getMessage());
        }
    	
    	try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet unitmarkRS = s.executeQuery("SELECT * FROM UnitMark WHERE UnitCode='" + unitCode + "' AND StudentID=" + studentID)) {
            
            // Next, gets the unique UnitMark entry for this student-unit combination
    		while (unitmarkRS.next()) {
            
    			setMark(unitmarkRS.getDouble("Mark"));
    		}
        } catch (SQLException ex) {
            Logger.getLogger(BaseUnit.class.getName()).log(Level.SEVERE, null, ex);
            throw new SQLException(ex.getMessage());
        }
    	
    	try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet assessmentRS = s.executeQuery("SELECT * FROM Assessment WHERE UnitCode='" + unitCode + "'")) {
            
            // Will be a list of assessments returned, as many assessments can belong to a unit
    		// We are adding all of them to the list
            while (assessmentRS.next()) {
            	Assessment nextAssess = new Assessment(assessmentRS.getInt("AssessmentID"), studentID, (Unit)this);
	            this.assessments.add(nextAssess);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseUnit.class.getName()).log(Level.SEVERE, null, ex);
            throw new SQLException(ex.getMessage());
        }
    }
    
    /**
     * Method to create a java object to represent a unit that doesn't exist yet in 
     * 
     * @param unitCode the unit code of the unit being searched for
     * @param studentID the ID of the student
     */
    public BaseUnit(String unitCode, String name, int points) throws SQLException {
        try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            s.execute("INSERT INTO Unit VALUES ('"
                    + unitCode + "', '"
                    + name + "', "
                    + points + ")");
            
            setUnitCode(unitCode);
            setName(name);
            setPoints(points);
        } catch (SQLException ex) {
            Logger.getLogger(BaseStudent.class.getName()).log(Level.SEVERE, null, ex);
            throw new SQLException(ex.getMessage());
        }
    }
    
    /**
     * Method to get the unit code of the unit
     * @return the unit code of the unit
     */
    public StringBuffer getUnitCode() {
        return unitCode;
    }
 
    /**
     * Method to set the unit code of the unit
     * @param unitCode the unit code of the unit
     */
    public void setUnitCode(String unitCode) {
    	this.unitCode.replace(0, this.unitCode.capacity(), unitCode);
    }
    
    /**
     * Method to get the name of the unit
     * @return the name of the unit
     */
    public StringBuffer getName() {
        return name;
    }
    
    /**
     * Method to set the name of the unit
     * @param name the name of the unit
     */
    public void setName(String name) {
    	this.name.replace(0, this.name.capacity(), name);
    }
    
    /**
     * Method to get the points that the unit is worth
     * @return the points the unit is worth
     */
    public int getPoints() {
    	return Integer.parseInt(points+"");
    }
    
    /**
     * Method to set the points that the unit is worth
     * @param points the points the unit is worth
     */
    public void setPoints(int points) {
    	this.points.replace(0, this.mark.capacity(),  Integer.toString(points));
    }
    
    /**
     * Method to get the mark a student has for a unit
     * @return the mark the student has
     */
    public double getMark() {
        return Double.parseDouble(mark+"");
    }
    
    /**
     * Method to set the mark a student has for a unit
     * @param mark the mark to give the student
     */
    public void setMark(double mark) {
        this.mark.replace(0, this.mark.capacity(),  Double.toString(mark));
        this.mark.setLength(5);
    }
    
    /**
     * Method to get the list of assessments in a unit
     * @return a list containing all of the assessments associated with the unit
     */
    public List<Assessment> getAssessments() {
        return assessments;
    }
    
    /**
     * Method to set the list of assessments in a unit
     * @param assessments a list containing all of the assessments to be associated with the unit
     */
    public void setAssessments(List<Assessment> assessments) {
        this.assessments = assessments;
    }
}
