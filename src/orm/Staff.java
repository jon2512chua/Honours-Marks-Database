package orm;

public class Staff extends BaseStaff {
    public Staff(int staffID) {
        super(staffID);
    }
    
    public int getNumMarks() {
        return getMarks().size();
    }
}
