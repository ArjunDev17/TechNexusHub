//package com.codeneeti.technexushub.entities;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Entity
//@Table(name = "category")
//public class Category {
//    @Id
//    @Column(name = "id")
//    private String categoryId;
//    @Column(name = "category_title",length =60,nullable = false )
//    private String title;
//    @Column(name = "category_desc",length = 500)
//    private String description;
//    private String coverImage;
//    //bidirectioinal
//
//    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    private List<Product> productList=new ArrayList<>();
//
//}

package com.codeneeti.technexushub.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "categories")
public class Category {
    @Id
    private String categoryId;
    private String title;
    private String description;
    private String coverImage;

    @OneToMany(mappedBy = "category")
    @JsonBackReference
    private List<Product> productList;
}
