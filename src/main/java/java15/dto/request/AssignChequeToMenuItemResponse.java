package java15.dto.request;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public class AssignChequeToMenuItemResponse {
    private String message;
    private Long menuItemId;
    private Long checkId;
    private HttpStatus httpStatus;
}
