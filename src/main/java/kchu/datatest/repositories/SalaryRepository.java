package kchu.datatest.repositories;

import kchu.datatest.models.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SalaryRepository extends CrudRepository<Salary,Long> {

    @Query(value = "SELECT distinct Job FROM Salary order by Job asc", nativeQuery = true)
    Iterable<String> findDistinctJobs();

    @Query(value = "SELECT distinct Agency_Name FROM Salary order by Agency_Name asc", nativeQuery = true)
    Iterable<String> findDistinctAgencyName();

    @Query(value = "SELECT job, COUNT(job) as jobcount FROM salary group by job order by jobcount desc limit 10",
    nativeQuery = true)
    Iterable<Object[]> findTop10CommonJobs();
}
