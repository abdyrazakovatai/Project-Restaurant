package java15.valid;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = Validator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRole {
    String message() default "Некорректный опыт или возраст для роли";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
