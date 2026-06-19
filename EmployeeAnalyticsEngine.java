import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class EmployeeAnalyticsEngine {

    // Employee Model
    static class Employee {
        private int id;
        private String name;
        private String department;
        private double salary;
        private int experience;
        private double rating;

        public Employee(int id, String name, String department,
                        double salary, int experience, double rating) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.salary = salary;
            this.experience = experience;
            this.rating = rating;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDepartment() {
            return department;
        }

        public double getSalary() {
            return salary;
        }

        public int getExperience() {
            return experience;
        }

        public double getRating() {
            return rating;
        }

        @Override
        public String toString() {
            return String.format(
                "%-5d %-15s %-12s %-10.2f %-10d %-5.2f",
                id, name, department, salary, experience, rating
            );
        }
    }


    // Functional Interface
    interface EmployeeOperation {
        void execute(List<Employee> employees);
    }


    static List<Employee> employees = new ArrayList<>();


    // Load 50+ employees
    static void loadData() {

        employees = Stream.of(
            new Employee(1,"John","IT",75000,5,4.5),
            new Employee(2,"Alice","HR",60000,6,4.2),
            new Employee(3,"Robert","Finance",82000,8,4.8),
            new Employee(4,"David","IT",90000,10,4.9),
            new Employee(5,"Emma","Sales",55000,3,3.9),
            new Employee(6,"James","IT",70000,4,4.1),
            new Employee(7,"Sophia","HR",65000,7,4.6),
            new Employee(8,"Daniel","Finance",88000,9,4.7),
            new Employee(9,"Olivia","Sales",58000,2,3.8),
            new Employee(10,"Michael","IT",95000,12,4.9),

            new Employee(11,"William","Finance",76000,6,4.3),
            new Employee(12,"Emily","HR",62000,5,4.0),
            new Employee(13,"Henry","IT",84000,8,4.6),
            new Employee(14,"Grace","Sales",59000,4,4.1),
            new Employee(15,"Lucas","Finance",91000,11,4.8),
            new Employee(16,"Mia","HR",68000,6,4.4),
            new Employee(17,"Alexander","IT",87000,9,4.7),
            new Employee(18,"Charlotte","Sales",61000,5,4.0),
            new Employee(19,"Benjamin","Finance",83000,7,4.5),
            new Employee(20,"Amelia","IT",98000,13,5.0),

            new Employee(21,"Ethan","HR",57000,2,3.7),
            new Employee(22,"Ava","Finance",89000,10,4.8),
            new Employee(23,"Ryan","IT",73000,5,4.2),
            new Employee(24,"Ella","Sales",64000,6,4.3),
            new Employee(25,"Noah","Finance",92000,12,4.9),
            new Employee(26,"Lily","HR",70000,8,4.6),
            new Employee(27,"Jack","IT",78000,7,4.4),
            new Employee(28,"Zoe","Sales",56000,3,3.9),
            new Employee(29,"Adam","Finance",86000,9,4.7),
            new Employee(30,"Hannah","IT",93000,11,4.8),

            new Employee(31,"Leo","HR",59000,4,4.0),
            new Employee(32,"Nora","Finance",81000,6,4.5),
            new Employee(33,"Oscar","IT",96000,12,4.9),
            new Employee(34,"Ruby","Sales",63000,5,4.1),
            new Employee(35,"Sam","HR",66000,7,4.3),
            new Employee(36,"Eva","Finance",87000,8,4.6),
            new Employee(37,"Tom","IT",74000,4,4.2),
            new Employee(38,"Ivy","Sales",60000,3,3.9),
            new Employee(39,"Max","Finance",90000,10,4.8),
            new Employee(40,"Anna","IT",99000,14,5.0),

            new Employee(41,"Chris","HR",61000,5,4.1),
            new Employee(42,"Laura","Finance",85000,9,4.7),
            new Employee(43,"Peter","IT",88000,8,4.6),
            new Employee(44,"Kate","Sales",65000,6,4.2),
            new Employee(45,"Mark","Finance",94000,12,4.9),
            new Employee(46,"Rose","HR",67000,7,4.4),
            new Employee(47,"Victor","IT",79000,6,4.3),
            new Employee(48,"Bella","Sales",62000,4,4.0),
            new Employee(49,"George","Finance",91000,11,4.8),
            new Employee(50,"Nancy","IT",97000,13,5.0),
            new Employee(51,"Kevin","HR",69000,8,4.5)

        ).collect(Collectors.toList());
    }


    // Display all employees
    static void showEmployees(List<Employee> list) {

        list.stream()
            .forEach(System.out::println);
    }



    // Highest salary employee using Optional
    static void highestSalary(List<Employee> list) {

        Optional<Employee> emp =
            list.stream()
            .max(Comparator.comparing(Employee::getSalary));


        emp.ifPresent(e ->
            System.out.println("\nHighest Salary Employee\n" + e)
        );
    }



    // Department report
    static void departmentReport(List<Employee> list) {

        list.stream()
        .collect(Collectors.groupingBy(
            Employee::getDepartment,
            Collectors.counting()
        ))
        .forEach((dept,count) ->
            System.out.println(dept+" : "+count+" employees")
        );
    }




    // Average salary
    static void averageSalary(List<Employee> list){

        double avg =
        list.stream()
        .mapToDouble(Employee::getSalary)
        .average()
        .orElse(0);

        System.out.println(
            "Average Salary : "+avg
        );
    }




    // Filter using lambda
    static void filterEmployees(List<Employee> list){

        Predicate<Employee> highRated =
            e -> e.getRating() >= 4.5;


        list.stream()
        .filter(highRated)
        .forEach(System.out::println);
    }



    // Menu
    static void menu(){

        Scanner sc = new Scanner(System.in);

        EmployeeOperation operation;


        while(true){

            System.out.println("\n===== EMPLOYEE ANALYTICS DASHBOARD =====");
            System.out.println("1. Display Employees");
            System.out.println("2. Highest Salary Employee");
            System.out.println("3. Department Report");
            System.out.println("4. Average Salary");
            System.out.println("5. High Rated Employees");
            System.out.println("6. Exit");

            System.out.print("Choice : ");

            int choice=sc.nextInt();


            switch(choice){

                case 1:
                    operation =
                    EmployeeAnalyticsEngine::showEmployees;
                    break;

                case 2:
                    operation =
                    EmployeeAnalyticsEngine::highestSalary;
                    break;

                case 3:
                    operation =
                    EmployeeAnalyticsEngine::departmentReport;
                    break;

                case 4:
                    operation =
                    EmployeeAnalyticsEngine::averageSalary;
                    break;

                case 5:
                    operation =
                    EmployeeAnalyticsEngine::filterEmployees;
                    break;

                case 6:
                    System.out.println("Exit...");
                    return;

                default:
                    System.out.println("Invalid choice");
                    continue;
            }


            operation.execute(employees);
        }
    }



    public static void main(String[] args){

        loadData();

        System.out.println(
            "Employee Analytics Engine Started..."
        );

        menu();
    }
}
