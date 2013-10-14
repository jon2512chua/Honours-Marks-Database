package orm;

import java.sql.SQLException;

public class Unit extends BaseUnit {
    public Unit(String unitCode) {
            super(unitCode);
    }

    public Unit(String unitCode, int studentID) throws SQLException {
            super(unitCode, studentID);
    }
}
