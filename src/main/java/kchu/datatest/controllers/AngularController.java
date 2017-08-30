package kchu.datatest.controllers;

import kchu.datatest.models.Salary;
import kchu.datatest.repositories.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
}
