package com.codeneeti.technexushub.dtos;

import com.codeneeti.technexushub.entities.Fresher;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageableResponse<T> {
    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private long totalElement;
    private int totalPages;
    private boolean lastPage;

    // Method to create PageableResponse from a Page of Fresher entities
    public static PageableResponse<FresherDTO> createResponse(Page<Fresher> fresherPage, Class<FresherDTO> fresherDTOClass) {
        ModelMapper modelMapper = new ModelMapper(); // ModelMapper for entity to DTO conversion

        // Convert the content of the page (List<Fresher>) to List<FresherDTO>
        List<FresherDTO> fresherDTOList = fresherPage.getContent().stream()
                .map(fresher -> modelMapper.map(fresher, fresherDTOClass)) // Convert each Fresher entity to FresherDTO
                .collect(Collectors.toList());

        // Build the PageableResponse with the necessary information
        return PageableResponse.<FresherDTO>builder()
                .content(fresherDTOList)
                .pageNumber(fresherPage.getNumber())  // Current page number
                .pageSize(fresherPage.getSize())      // Size of each page
                .totalElement(fresherPage.getTotalElements())  // Total number of elements across all pages
                .totalPages(fresherPage.getTotalPages())  // Total number of pages
                .lastPage(fresherPage.isLast())  // Whether this is the last page
                .build();
    }
}
