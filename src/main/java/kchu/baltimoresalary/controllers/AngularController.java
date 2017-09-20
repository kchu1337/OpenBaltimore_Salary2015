package kchu.baltimoresalary.controllers;

import kchu.baltimoresalary.models.DataSet;
import kchu.baltimoresalary.models.Salary;
import kchu.baltimoresalary.repositories.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class AngularController {

    @Autowired
    SalaryRepository salaryRepository;

    //Retrieves all of the salary data from the respository
    @RequestMapping("/getdata")
    public Iterable<Salary> getdata(){return salaryRepository.findAll();}

    //Retrieves all salary data that cotains annual salaries between the max and min
    @RequestMapping("/getdatabetween")
    public Iterable<Salary> getdatabetween(@RequestParam(value="min") double min,
                                           @RequestParam(value="max") double max){
        return salaryRepository.findAllByAnnualSalaryBetween(min,max);
    }

    //Deletes the salary tuple having that id
    @RequestMapping("/delete/{id}")
    public Iterable<Salary> delete(@PathVariable("id") long id){
        salaryRepository.delete(id);
        return salaryRepository.findAll();
    }

    //Returns the value of the maximum salary
    @RequestMapping("/maxsalary")
    public int maxSalary(){
        double maxSalary= salaryRepository.findMaxSalary();
        return (int)Math.ceil(maxSalary/1000)*1000;
    }
    //Returns the valueof the minimum salary
    @RequestMapping("/minsalary")
    public int minSalary(){
        double minSalary= salaryRepository.findMinSalary();
        return (int)Math.floor(minSalary/1000)*1000;
    }

    //Returns the default dataset, which is just hte top 10 salaries
    @RequestMapping("/top10bysalary")
    public List<DataSet> top10BySalary(){
        return(createDataSetList(salaryRepository.findTop10HighestJobs()));
    }

    //Returns up to the top 10 salaries filtered by the user input
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
