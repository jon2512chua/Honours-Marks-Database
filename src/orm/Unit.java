package orm;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

import logic.CohortData;
import sessionControl.Session;

public class Unit extends BaseUnit {
    public Unit(String unitCode) {
        super(unitCode);
    }

    public Unit(String unitCode, int studentID) throws SQLException {
        super(unitCode, studentID);
    }
    
    public Unit(String unitCode, String unitName, int unitPoints) throws SQLException {
        super(unitCode, unitName, unitPoints);
    }
    
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
     * Delete this student //TODO test
     * @throws SQLException
     */
    public void deleteRow() throws SQLException {
    	CohortData.units.remove(this);
    	String sql = "DELETE from Unit WHERE UnitCode = " + this.getUnitCode().toString();
    	Statement stmt = Session.dbConn.getConnection().createStatement();
    	stmt.execute(sql);
    	stmt.close();
    }
    /**
     * Get a unit by unit code
     * @param unitCode
     * @return the unit, or null if not found
     */
    public static Unit getUnitByUnitCode(String unitCode) {
    	for(Unit u : CohortData.units) {
    		if(u.getUnitCode().toString().equals(unitCode)) {
    			return u;
    		}
    	}
    	return null;
    }
}
