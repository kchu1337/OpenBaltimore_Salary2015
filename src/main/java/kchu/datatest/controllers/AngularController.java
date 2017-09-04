package kchu.datatest.controllers;

import kchu.datatest.models.*;
import kchu.datatest.repositories.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@RestController
public class AngularController {

    @Autowired
    SalaryRepository salaryRepository;

    @RequestMapping("/getdata")
    public Iterable<Salary> getdata(){return salaryRepository.findAll();}

    @RequestMapping("/delete/{id}")
    public Iterable<Salary> delete(@PathVariable("id") long id){
        salaryRepository.delete(id);
        return salaryRepository.findAll();
    }
    @RequestMapping("/maxsalary")
    public int maxSalary(){
        double maxSalary= salaryRepository.findMaxSalary();
        return (int)Math.round(maxSalary/1000)*1000;
    }
    @RequestMapping("/minsalary")
    public int minSalary(){
        double minSalary= salaryRepository.findMinSalary();
        return (int)Math.round(minSalary/1000)*1000;
    }

    @RequestMapping("/top10bysalary")
    public List<DataSet> top10BySalary(){
        return(createDataSetList(salaryRepository.findTop10HighestJobs()));
    }

    @RequestMapping("/top10filtered")
    public List<DataSet> top10Filtered(
            @RequestParam(value="job", defaultValue="") String job,
            @RequestParam(value="count", defaultValue = "1") int count,
            @RequestParam(value="salary", defaultValue = "-1") int salary){
        if (salary<0){
            salary = (int)Math.round(salaryRepository.findMaxSalary());
        }
        //Adds wildcards to the job search string
        job = '%'+job+'%';
        return(createDataSetList(salaryRepository.findTop10Filtered(job, count, salary)));
    }

//*****************************************************************************************************
// Helper functions

    //Returns a json formatted list of the chart data
    private List<DataSet> createDataSetList(Iterable<Object[]> dbResult){
        ArrayList<DataSet> dataSetList = new ArrayList<DataSet>();
        for(Object[] data: dbResult){
            //For each tuple, creates a new DataSet object with the tuple
            //values and adds it to the list
            DataSet dataSet = new DataSet((String)data[0],(int)(Math.round((double)data[1])));
            dataSetList.add(dataSet);
        }
        return dataSetList;
    }
}
