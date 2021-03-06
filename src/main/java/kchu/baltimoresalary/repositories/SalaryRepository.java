package kchu.baltimoresalary.repositories;

import kchu.baltimoresalary.models.Salary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SalaryRepository extends CrudRepository<Salary,Long> {

    @Query(value = "SELECT MAX(annual_salary) FROM Salary", nativeQuery = true)
    double findMaxSalary();

    @Query(value = "SELECT MIN(annual_salary) FROM Salary", nativeQuery = true)
    double findMinSalary();

    Iterable<Salary> findAllByAnnualSalaryBetween(double min, double max);

    @Query(value = "SELECT distinct Job FROM Salary order by Job asc", nativeQuery = true)
    Iterable<String> findDistinctJobs();

    @Query(value = "SELECT distinct Agency_Name FROM Salary order by Agency_Name asc", nativeQuery = true)
    Iterable<String> findDistinctAgencyName();

    @Query(value = "SELECT job, AVG(Annual_Salary) as jobavg " +
            "FROM salary " +
            "group by job " +
            "order by jobavg desc limit 10",
            nativeQuery = true)
    Iterable<Object[]> findTop10HighestJobs();

    @Query(value = "SELECT job, AVG(annual_Salary) as jobavg " +
            "FROM salary " +
            "where UPPER(job) like UPPER( :job ) " +
            "group by job " +
            "HAVING count(job) >= :count and jobavg <= :average " +
            "order by jobavg desc limit 10",
            nativeQuery = true)
    Iterable<Object[]> findTop10Filtered(@Param("job") String job,
                                         @Param("count") int count,
                                         @Param("average") int average);
}
