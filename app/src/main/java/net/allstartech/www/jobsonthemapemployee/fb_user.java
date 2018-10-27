package net.allstartech.www.jobsonthemapemployee;

/**
 * Created by AST on 10/19/2017.
 */

public class fb_user {

    public String employee_FirstName;
    public String employee_LastName;
    public String employee_Email;
    public String employee_Profilepicture;


    public  fb_user(){}

    public fb_user(String employee_FirstName, String employee_LastName, String employee_Email, String employee_Profilepicture) {
        this.employee_FirstName = employee_FirstName;
        this.employee_LastName = employee_LastName;
        this.employee_Email = employee_Email;
        this.employee_Profilepicture = employee_Profilepicture;
    }

    public String getEmployee_FirstName() {
        return employee_FirstName;
    }

    public String getEmployee_LastName() {
        return employee_LastName;
    }

    public String getEmployee_Email() {
        return employee_Email;
    }

    public String getEmployee_Profilepicture() {
        return employee_Profilepicture;
    }
}
