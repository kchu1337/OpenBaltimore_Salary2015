package kchu.datatest.controllers;

import kchu.datatest.models.*;
import kchu.datatest.repositories.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.math.BigInteger;


/**
 * Created by student on 7/10/17.
 */
@Controller
public class HomeController {
    @Autowired
    SalaryRepository salaryRepository;

    @RequestMapping("/")
    //returns the homepage at the root
    public String home(){
        return "index";
    }

    @GetMapping("/add")
    //adds a new salary tuple
    public String add(Model model){
        model.addAttribute("salary", new Salary());

        //returns a list of jobs/employers that the users can choose from
        Iterable<String> jobTitleList = salaryRepository.findDistinctJobs();
        model.addAttribute("jobList", jobTitleList);
        Iterable<String> employerList = salaryRepository.findDistinctAgencyName();
        model.addAttribute("employerList", employerList);

        return "update";
    }

    @GetMapping("/update/{id}")
    //Updates an existing salary tuple
    public String update(@PathVariable("id") long id, Model model){

        //Finds the salary associated with the ID and adds as an attribute in the view
        Salary salary = salaryRepository.findOne(id);
        model.addAttribute("salary", salary);

        //returns a list of jobs/employers that the users can choose from
        Iterable<String> jobTitleList = salaryRepository.findDistinctJobs();
        model.addAttribute("jobList", jobTitleList);
        Iterable<String> employerList = salaryRepository.findDistinctAgencyName();
        model.addAttribute("employerList", employerList);

        return "update";
    }
    //Saves the new/updated tuple 
    @PostMapping("/update/{id}")
    public String updatePost(@ModelAttribute Salary salary, Model model){
        salaryRepository.save(salary);
    return "updatesubmit";
    }
}
