package orm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import sessionControl.Session;

public class BaseUnit {
	public StringBuffer unitCode = new StringBuffer (30);
    public StringBuffer name = new StringBuffer (30);
    public StringBuffer points = new StringBuffer (30);
    public StringBuffer mark = new StringBuffer (30);
    private List<Assessment> assessments;
    
    public BaseUnit(String unitCode) {
    	try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet unitRS = s.executeQuery("SELECT * FROM Unit WHERE UnitCode=" + unitCode)) {
            
            // There will only be one unit returned as unitCode is unique
    		// Called with this constructor, no data about marks will exist
    		unitRS.first();
            
            setUnitCode(unitCode);
            
            setName(unitRS.getString("UnitName"));
            setPoints(unitRS.getInt("Points"));
        } catch (SQLException ex) {
            Logger.getLogger(BaseUnit.class.getName()).log(Level.SEVERE, null, ex);
        }
    	
    	try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet assessmentRS = s.executeQuery("SELECT * FROM Assessment WHERE UnitCode='" + unitCode + "'")) {
            
            // Will be a list of assessments returned, as many assessments can belong to a unit
    		// We are adding all of them to the list
            while (assessmentRS.next()) {
            	Assessment nextAssess = new Assessment(assessmentRS.getInt("AssessmentID"));
	            this.assessments.add(nextAssess);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseUnit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public BaseUnit(String unitCode, int studentID) throws SQLException {
    	this.assessments = new ArrayList<>();
    	
    	try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet unitRS = s.executeQuery("SELECT * FROM Unit WHERE UnitCode='" + unitCode + "'")) {
            
            // There will only be one unit returned as unitCode is unique
            unitRS.first();
            
            setUnitCode(unitCode);
            
            setName(unitRS.getString("UnitName"));
            setPoints(unitRS.getInt("Points"));
        } catch (SQLException ex) {
            Logger.getLogger(BaseUnit.class.getName()).log(Level.SEVERE, null, ex);
            throw new SQLException(ex.getMessage());
        }
    	
    	try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet unitmarkRS = s.executeQuery("SELECT * FROM UnitMark WHERE UnitCode='" + unitCode + "' AND StudentID=" + studentID)) {
            
            // Next, gets the unique UnitMark entry for this student-unit combination
            unitmarkRS.first();
            
            setMark(unitmarkRS.getDouble("Mark"));
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
    
    public StringBuffer getUnitCode() {
        return unitCode;
    }
    
    public void setUnitCode(String unitCode) {
    	this.unitCode.replace(0, this.unitCode.length(), unitCode);
    }
    
    public StringBuffer getName() {
        return name;
    }
    
    public void setName(String name) {
    	this.name.replace(0, this.name.length(), name);
    }
    
    public int getPoints() {
    	return Integer.parseInt(points+"");
    }
    
    public void setPoints(int points) {
    	this.points.replace(0, this.mark.length(),  Integer.toString(points));
    }
    
    public double getMark() {
        return Double.parseDouble(mark+"");
    }
    
    public void setMark(double mark) {
        this.mark.replace(0, this.mark.length(),  Double.toString(mark));
    }
    
    public List<Assessment> getAssessments() {
        return assessments;
    }
    
    public void setAssessments(List<Assessment> assessments) {
        this.assessments = assessments;
    }
}
