package net.allstartech.www.jobsonthemapemployee;

/**
 * Created by AST on 10/3/2017.
 */

public class Employee_details {
    public String employee_FirstName;
    public String employee_LastName;
    public String employee_Telephone;
    public String employee_Email;
    public String employee_Password;
    public String employee_Profilepicture;
    public String employee_About;
    public String employee_Address;
    public String id;

    public Employee_details() {

    }

    public Employee_details(String employee_FirstName, String employee_LastName, String employee_Telephone, String employee_Email, String employee_Password, String employee_Profilepicture, String employee_About, String employee_Address, String id) {
        this.employee_FirstName = employee_FirstName;
        this.employee_LastName = employee_LastName;
        this.employee_Telephone = employee_Telephone;
        this.employee_Email = employee_Email;
        this.employee_Password = employee_Password;
        this.employee_Profilepicture = employee_Profilepicture;
        this.employee_About = employee_About;
        this.employee_Address = employee_Address;
        this.id = id;
    }

    public String getEmployee_FirstName() {
        return employee_FirstName;
    }

    public String getEmployee_LastName() {
        return employee_LastName;
    }

    public String getEmployee_Telephone() {
        return employee_Telephone;
    }

    public String getEmployee_Email() {
        return employee_Email;
    }

    public String getEmployee_Password() {
        return employee_Password;
    }

    public String getEmployee_Profilepicture() {
        return employee_Profilepicture;
    }

    public String getEmployee_About() {
        return employee_About;
    }

    public String getEmployee_Address() {
        return employee_Address;
    }

    public String getId() {
        return id;
    }
}