package orm;

public class Student extends BaseStudent { 
    public String getFullName() {
        String fullName = getFirstName() + getLastName();
        
        return fullName;
    }
}
