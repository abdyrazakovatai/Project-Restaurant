package java15.repo;

import java15.exception.NotFoundException;
import java15.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);

    Optional<Employee> findById(Long id);


    default Employee getEmployeeByEmail(String email) {
        return findByEmail(email).orElseThrow(() ->
                new NotFoundException("Employee with " + email + "not found!"));
    }

    ;

    default Employee getEmployeeById(Long id) {
        return findById(id).orElseThrow(() ->
                new NotFoundException("Employee with " + id + "not found!"));
    }

    ;

    Employee findEmployeeByEmailEqualsIgnoreCase(String email);

    boolean existsByEmail(String email);

    @Query("select e from Employee e where e.id = :employeeId and e.role = 'EMPLOYEE' ")
    Employee getAllByRoleById(@Param ("employeeId")Long employeeId);

    @Query("select e from Employee e where e.role = 'EMPLOYEE' ")
    List<Employee> getAllByRole();
}
