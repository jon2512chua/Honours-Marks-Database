package orm;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

import logic.CohortData;
import sessionControl.Session;

/**
 * Class representing a Unit.
 * 
 * @author Jonathan Chua
 * @author Samuel Widenbar
 * @author Nicholas Abbey
 * @version 16/10/2013
 */
public class Unit extends BaseUnit {
    /**
     * Constructor to create a java object to represent a unit found in the database, independently of a student, primarily for
     * use in displaying/editing unit information.
     * 
     * @param unitCode is the unit code of the unit being searched for
     */
    public Unit(String unitCode) {
        super(unitCode);
    }

    /**
     * Constructor to create a java object to represent a unit being taken by a particular student found in the database.
     * 
     * @param unitCode the unit code of the unit being searched for
     * @param studentID the ID of the student
     */
    public Unit(String unitCode, int studentID) throws SQLException {
        super(unitCode, studentID);
    }
    
    /**
     * Constructor to create a java object to represent a unit that doesn't exist yet in the database.
     * 
     * @param unitCode the unit code of the unit being searched for
     * @param studentID the ID of the student
     * @param points the amount of credit points for the unit
     */
    public Unit(String unitCode, String unitName, int unitPoints) throws SQLException {
        super(unitCode, unitName, unitPoints);
    }
    
    /**
     * Method to get all the units in the database.
     * @return a List of Unit objects
     */
    public static List<Unit> getAllUnits() {
        List<Unit> allUnits = new ArrayList<>();
        
        try {
            Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet unitsRS = s.executeQuery("SELECT UnitCode FROM Unit");
            
            while (unitsRS.next()) {
                allUnits.add(new Unit(unitsRS.getString("UnitCode")));
            }
        } catch (java.lang.NullPointerException | SQLException ex) {
            Logger.getLogger(BaseStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return allUnits;
    }
    
    /**
     * return Unit detail of a specific unit code
     * @param unitCode the specific unit code
     * @return the Unit class of the specific unit
     */
    public static Unit getUnitByCode (String unitCode) {
    	for (Unit u : Unit.getAllUnits()) {
    		try {
			if (unitCode.equals(u.getUnitCode()+"")) return u;
    		} catch (java.lang.NumberFormatException e) {}
		}
    	return null;
    }
    
    /**
     * Update a single row of the Unit table 
     * 	- called when the save changes button is hit.
     *  - unit code is omitted so that it can never be changed. 
     * @throws SQLException 
     */
    public void updateRow() throws SQLException {
    	String sql = "UPDATE Unit SET UnitName = '"+this.getName().toString()+"', Points = " + this.getPoints() + " WHERE UnitCode = " + this.getUnitCode().toString();
    	Statement stmt = Session.dbConn.getConnection().createStatement();
    	stmt.execute(sql);
    	stmt.close();
    }
    
    /**
     * Delete this unit
     * @throws SQLException
     */
    public void deleteRow() throws SQLException {
    	CohortData.units.remove(this);
    	String sql = "DELETE from Unit WHERE UnitCode = '" + this.getUnitCode().toString() + "'";
    	Statement stmt = Session.dbConn.getConnection().createStatement();
    	stmt.execute(sql);
    	stmt.close();
    }
}
