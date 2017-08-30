package kchu.datatest.controllers;

import kchu.datatest.models.Salary;
import kchu.datatest.repositories.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


/**
 * Created by student on 7/10/17.
 */
@Controller
public class HomeController {
    @Autowired
    SalaryRepository salaryRepository;

    @RequestMapping("/")
    public String home(){
        return "index";

    }
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") long id, Model model){

        //Finds the salary associated with the ID and adds as an attribute in the view
        Salary salary = salaryRepository.findOne(id);
        model.addAttribute("salary", salary);

        /*Iterable<String> jobTitleList = salaryRepository.findDistinctByJob();
        model.addAttribute("jobList", jobTitleList);

        Iterable<String> employerList = salaryRepository.findDistinctByAgencyName();
        model.addAttribute("employerList", employerList);*/

        return "update";
    }
}
