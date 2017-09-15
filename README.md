# OpenBaltimore_Salary2015
Salary analysis of Baltimore public employees 2015 using OpenBaltimore dataset

# How to install
__Requirements:__

* Java JDK
* Maven

__Instructions:__

1. Clone the repository into a directoryof your choice 

2. Run: `mvn clean`

3. Run: `mvn spring-boot:run`

4. Open the internet browser of your choice and type in ![localhost:8083](localhost:8083)

# How to use
![help](src/main/resources/static/images/help1.gif)

1. Switch between table and graph views.

2. Change how many items are shown per table page.

3. Add new salary data. A window will pop up to add new salary data.

4. Filter each category. Just type in the value and the chart will automatically filter.

5. Sort each category. Click on the button to sort ascending, descending, and back to default.

6. Filter by annual salary. Set the range of the annual salary and click the filter salary button. Click the reset salary filters button
to reset the salary filters. 

7. Edit existing salary data.  A window will pop up to edit existing salary data.

8. Delete the salary data. Deletes the row of data. 

9. Pagination. Change pages. 

![help](src/main/resources/static/images/help2.gif)

1. Filter jobs based on job title

2. Filter jobs to show only the jobs whose salary is less that this amount

3. Filter jobs to show only those higher than this minimum job count.

4. Reloads the graph with the above filters

__Final design may differ slightly from above images__

