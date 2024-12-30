package java15.dto.request.restaurant;

import jakarta.validation.constraints.*;
import java15.enums.RestType;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class RestaurantRequest {

    @NotBlank(message = "Имя ресторана не может быть пустым")
    @Size(min = 4,max = 30,message = "Имя ресторана должно быть от 4 до 30 символов")
    private String name;

    @NotBlank(message = "Местоположение не может быть пустым")
    @Size(min = 4,max = 30,message = "Местоположение ресторана должно быть от 4 до 30 символов")
    private String location;

    @NotNull(message = "Тип ресторана не может быть пустым")
    private RestType restType;

    @Max(value = 15,message = "Количество сотрудников не может превышать 15")
    private int numberOfEmployees;

    @NotNull(message = "Цена не может быть пустой")
    @DecimalMin(value = "0.0", inclusive = false, message = "Цена должна быть больше 0")
    private BigDecimal service;
}
