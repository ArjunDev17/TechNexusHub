package com.codeneeti.technexushub.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "category")
public class Category {
    @Id
    @Column(name = "id")
    private String categoryId;
    @Column(name = "category_title",length =60,nullable = false )
    private String title;
    @Column(name = "category_desc",length = 500)
    private String description;
    private String coverImage;
    //bidirectioinal

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Product> productList=new ArrayList<>();

}
