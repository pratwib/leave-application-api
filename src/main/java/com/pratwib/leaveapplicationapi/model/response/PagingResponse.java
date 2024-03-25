package com.pratwib.leaveapplicationapi.model.response;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class PagingResponse {
    @Min(0)
    private Integer currentPage;
    @Min(1)
    private Integer totalPages;
    @Min(1)
    private Integer size;
    @Min(0)
    private Long totalItems;
}
