package java15.exception.response;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record ExceptionResponse(
          HttpStatus status,
          String exceptionsClassName,
          String message
) {
}
