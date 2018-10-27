package net.allstartech.www.jobsonthemapemployee;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * Created by AST on 10/27/2017.
 */

public class Employee_detail_sec {

    public String employee_About;
    public String employee_Address;
    public String employee_Languages;
    public ArrayList employee_Links;
    public String employee_WorkExperience;



    public Employee_detail_sec(String employee_About, String employee_Address, String employee_Languages, ArrayList employee_Links, String employee_WorkExperience) {
        this.employee_About = employee_About;
        this.employee_Address = employee_Address;
        this.employee_Languages = employee_Languages;
        this.employee_Links = employee_Links;
        this.employee_WorkExperience = employee_WorkExperience;
    }

    public String getEmployee_About() {
        return employee_About;
    }

    public String getEmployee_Address() {
        return employee_Address;
    }

    public String getEmployee_Languages() {
        return employee_Languages;
    }

    public ArrayList getEmployee_Links() {
        return employee_Links;
    }

    public String getEmployee_WorkExperience() {
        return employee_WorkExperience;
    }
}
