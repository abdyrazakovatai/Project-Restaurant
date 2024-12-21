package java15.dto.request;

import java15.enums.RestType;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RestaurantRequest {
    private String name;
    private String location;
    private RestType restType;
    private int numberOfEmployees;
    private int service;
}
