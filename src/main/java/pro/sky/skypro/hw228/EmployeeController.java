package pro.sky.skypro.hw228;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/departments")
public class EmployeeController {
    private final EmployeeService EmployeeService;

    public EmployeeController(EmployeeService EmployeeService) {
        this.EmployeeService = EmployeeService;
    }


    @GetMapping("/all")
    public Map<Integer, List<Employee>> getEmployeeByDepartmen(@RequestParam(
            value = "departmentId", required = false) Integer departmentId) {
        if (departmentId == null) {
            return EmployeeService.getEmployeesToTheDepartament();
        }
        List<Employee> employees = EmployeeService.getEmployeesDepartament(departmentId);
        if (employees.isEmpty()) {
            throw new RuntimeException( "Сотрудники не найдены для отдела с ID: " + departmentId);
        }
        return  Map.of(departmentId, employees);
    }

    @GetMapping("/min-salary")
    public Optional<Employee> getMinSalariDepartmen(@RequestParam("departmentId") int departmentId) {
        return EmployeeService.getEmployeeMinSalariDepartament(departmentId);
    }

    @GetMapping("/max-salary")
    public Optional<Employee> getMaxSalariDepartmen(@RequestParam("departmentId") int departmentId) {
        return EmployeeService.getEmployeeMaxSalariDepartament(departmentId);
    }
}