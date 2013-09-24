package orm;

public class Staff extends BaseStaff {
    public int getNumMarks() {
        return getMarks().size();
    }
}
