package pro.sky.skypro.hw228;


import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service

public class EmployeeServiceImp implements EmployeeService {


    private List<Employee> employees = new ArrayList<>(List.of(
            new Employee("Кирилл", "Доминов", 40000, 1),
            new Employee("Иван", "Иванов", 45000, 1),
            new Employee("Пётр", "Петров", 50000, 2),
            new Employee("Сергей", "Сергеев", 55000, 2),
            new Employee("Дмитрий", "Елькин", 60000, 3),
            new Employee("Евгений", "Мехоношин", 75000, 3)
    ));

    @Override
    public Map<Integer, List<Employee>> getEmployeesToTheDepartament() {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartmentId));

    }

    @Override
    public List<Employee> getEmployeesDepartament(int number) {
        return employees.stream()
                .filter(e -> e.getDepartmentId() == number)
                .collect(Collectors.toList());

    }

    @Override
    public Optional<Employee> getEmployeeMinSalariDepartament(int number) {
        return employees.stream()
                .filter(e -> e.getDepartmentId() == number)
                .min(Comparator.comparing(Employee::getSalary));

    }

    @Override
    public Optional<Employee> getEmployeeMaxSalariDepartament(int number) {
        return employees.stream()
                .filter(e -> e.getDepartmentId() == number)
                .max(Comparator.comparing(Employee::getSalary));

    }

    @Override
    public String getEmployeAverageSalariDepartments(int number) {
        Double averageSalari = employees.stream()
                .filter(e -> e.getDepartmentId() == number)
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
        return ("Средняя зарплата по отделу " + number + ": " + averageSalari);


    }

    @Override
    public String getEmployeesAverageSalari() {
        Double averageSalari = employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
        return ("Средняя заработная плата всех сотрудников: " + averageSalari);

    }

    @Override
    public List<Employee> indexingSalari(int percent) {
        Double percentValue = percent / 100.0;
        List<Employee> indexedEmployees = employees.stream()
                .map(employee -> {
                    Double newSalari = employee.getSalary() + (employee.getSalary() * percentValue);
                    employee.setSalary(newSalari);
                    return employee;
                })
                .collect(Collectors.toList());
        System.out.println("заработная плата была проиндексирована : " + percent + " процент");
        indexedEmployees.forEach(System.out::println);

        return indexedEmployees;
    }

}