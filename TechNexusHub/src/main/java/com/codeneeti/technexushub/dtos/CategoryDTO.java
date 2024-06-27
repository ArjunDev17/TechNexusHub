package com.codeneeti.technexushub.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO {

    private String categoryId;
    @NotBlank
    @Min(value = 4,message = "title must be of 4 charecter")
    @NotBlank(message = "title is required")
    @Size(min = 4,message = "title must be of 4")
    private String title;
    @NotBlank
    private String description;
    @NotBlank(message = "cover page required")
    private String coverImage;
}
