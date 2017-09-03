package kchu.datatest.controllers;

import kchu.datatest.models.*;
import kchu.datatest.repositories.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@RestController
public class AngularController {

    @Autowired
    SalaryRepository salaryRepository;

    @RequestMapping("/getdata")
    public Iterable<Salary> getdata(){
        return salaryRepository.findAll();
    }

    @RequestMapping("/delete/{id}")
    public Iterable<Salary> delete(@PathVariable("id") long id){
        salaryRepository.delete(id);
        return salaryRepository.findAll();
    }

    @RequestMapping("/top10byjobs")
    public List<DataSet> top10ByJobs(){
        Iterable<Object[]> jobCountList = salaryRepository.findTop10CommonJobs();
        ArrayList<DataSet> returnDataSet = new ArrayList<DataSet>();

        for(Object[] data: jobCountList){
            DataSet dataSet = new DataSet((String)data[0],((BigInteger)data[1]).toString());
            returnDataSet.add(dataSet);
        }
        return returnDataSet;
    }
}
