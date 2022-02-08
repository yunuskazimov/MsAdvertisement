package az.xazar.msadvertisement.model;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class PageDto {
    private int pageNumber = 0;
    private int pageSize = 10;
    private Sort.Direction sortDirection = Sort.Direction.DESC;
    private String sortBy = "createdAt";
}
