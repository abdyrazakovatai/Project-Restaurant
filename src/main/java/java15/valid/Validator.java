package java15.valid;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java15.dto.request.employee.SaveEmployeeRequest;
import java15.enums.Role;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Validator implements ConstraintValidator<ValidRole, SaveEmployeeRequest> {

    @Override
    public boolean isValid(SaveEmployeeRequest employee, ConstraintValidatorContext context) {
        if (employee == null) {
            return true;
        }

        Role role = employee.getRole();
        int experience = employee.getExperience();

        LocalDate dateOfBirth = employee.getDateOfBirth();
        LocalDate now = LocalDate.now();
        Long age = ChronoUnit.YEARS.between(dateOfBirth, now);

        context.disableDefaultConstraintViolation();

        if (role == Role.WAITER) {
            if (experience < 1) {
                context.buildConstraintViolationWithTemplate("Для роли 'WAITER' опыт должен быть не менее 1 года")
                        .addPropertyNode("experience") // Указываем поле, к которому относится ошибка
                        .addConstraintViolation();
                return false;
            }
            if (age < 18 || age > 30) {
                context.buildConstraintViolationWithTemplate("Возраст для роли 'WAITER' должен быть от 18 до 30 лет")
                        .addPropertyNode("dateOfBirth") // Указываем поле, связанное с ошибкой
                        .addConstraintViolation();
                return false;
            }
        } else if (role == Role.CHEF) {
            if (experience < 2) {
                context.buildConstraintViolationWithTemplate("Для роли 'CHEF' опыт должен быть не менее 2 лет")
                        .addPropertyNode("experience")
                        .addConstraintViolation();
                return false;
            }
            if (age < 25 || age > 45) {
                context.buildConstraintViolationWithTemplate("Возраст для роли 'CHEF' должен быть от 25 до 45 лет")
                        .addPropertyNode("dateOfBirth")
                        .addConstraintViolation();
                return false;
            }
        }

        return true;
    }
}
