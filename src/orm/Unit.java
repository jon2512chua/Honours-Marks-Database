package orm;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
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
            ResultSet unitsRS = s.executeQuery("SELECT UnitCode FROM Student");
            
            while (unitsRS.next()) {
                allUnits.add(new Unit(unitsRS.getString("UnitCode")));
            }
        } catch (java.lang.NullPointerException | SQLException ex) {
            Logger.getLogger(BaseStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return allUnits;
    }
}
