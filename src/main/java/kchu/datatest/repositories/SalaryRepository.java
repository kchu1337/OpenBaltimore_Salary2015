package kchu.datatest.repositories;

import kchu.datatest.models.*;
import org.springframework.data.repository.CrudRepository;

public interface SalaryRepository extends CrudRepository<Salary,Long> {

    /*public Iterable<String> findDistinctByJob();

    public Iterable<String> findDistinctByAgencyName();*/
}
