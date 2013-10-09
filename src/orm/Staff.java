package orm;

public class Staff extends BaseStaff {
    public Staff(int staffID) {
        super(staffID);
    }
    
    public String getFullName() {
        return this.getFistName() + " " + this.getLastName();
    }
    
    public int getNumMarks() {
        return getMarks().size();
    }
}
