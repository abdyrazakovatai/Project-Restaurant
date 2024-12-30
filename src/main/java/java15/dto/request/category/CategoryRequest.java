package java15.dto.request.category;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryRequest {
    @NotBlank(message = "Имя категории не должен быть пустым")
    private String name;

    @JsonCreator
    public CategoryRequest(@JsonProperty("name") String name) {
        this.name = name;
    }
}
