package kchu.baltimoresalary.models;


import org.springframework.boot.autoconfigure.web.ResourceProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Salary{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private String lastName;
    private String firstInitial;
    private String job;
    private String agencyName;
    private double annualSalary;
    private double grossSalary;

    public Salary() {
    }

    public Salary(String lastName, String firstInitial, String job, String agencyName, double annualSalary, double grossSalary) {
        this.lastName = lastName;
        this.firstInitial = firstInitial;
        this.job = job;
        this.agencyName = agencyName;
        this.annualSalary = annualSalary;
        this.grossSalary = grossSalary;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstInitial() {
        return firstInitial;
    }

    public void setFirstInitial(String firstInitial) {
        this.firstInitial = firstInitial;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public double getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(double annualSalary) {
        this.annualSalary = annualSalary;
    }

    public double getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(double grossSalary) {
        this.grossSalary = grossSalary;
    }
}
