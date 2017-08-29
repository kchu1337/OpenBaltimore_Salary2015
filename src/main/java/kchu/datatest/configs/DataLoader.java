package kchu.datatest.configs;


import kchu.datatest.models.Salary;
import kchu.datatest.repositories.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Scanner;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    SalaryRepository salaryRepository;

    @Override
    public void run(String... strings) throws Exception {
        Scanner in = new Scanner(new File("Baltimore_City_Employee_Salaries_FY2015.csv"));
        in.nextLine();
        while(in.hasNext()){
        //for(int i = 0; i<100; i++){
            String line = in.nextLine();
            //0-lastname, 1-firstname, 2-jobtitle, 3-agencyid, 4-agencyname, 5-date, 6-annualsalary, 7-gross salary
            String[] data = line.split(",");
            String lastName = data[0].substring(1);
            String firstInitial = data[1].substring(0,1);
            String jobTitle = data[2];
            int agencyIdIndex = 3;
            String agencyId = data[3];
            String agencyName = data[4];
            double annualSalary = Double.parseDouble(data[6]);
            double grossSalary;
            //some datasets do not have gross salary
            if (data.length>7) {
                grossSalary = Double.parseDouble(data[7]);
            }
            else{
                grossSalary = -1;
            }
            Salary salary = new Salary(lastName,firstInitial,jobTitle,agencyId,agencyName,annualSalary,grossSalary);
            salaryRepository.save(salary);
        }
    }
}
