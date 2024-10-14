package edu.cs;

/*
  @author   AlexAT
  @project   lab3-company-service
  @class  Company
  @version  1.0.0
  @since 28.09.2024 - 17.44
*/

public class Company {
    private Company parent;
    private long employeeCount;

    public Company(Company parent, long employeeCount) {
        this.parent = parent;
        this.employeeCount = employeeCount;
    }

    // Getters and Setters
    public Company getParent() {
        return parent;
    }

    public void setParent(Company parent) {
        this.parent = parent;
    }

    public long getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(long employeeCount) {
        this.employeeCount = employeeCount;
    }
}
