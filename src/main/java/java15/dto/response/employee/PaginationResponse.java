package java15.dto.response.employee;

import lombok.Data;

import java.util.List;

@Data
public class PaginationResponse<T> {
    private int pageNumber;
    private int pageSize;
    private long numberOrElements;
    private long numberOfPages;
    private List<T> objects;
}
