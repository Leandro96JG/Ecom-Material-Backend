package com.FitCommerce.ecom.admin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
@ToString(exclude = "category")
@EqualsAndHashCode(exclude = "category")
@Table(name = "product")
//evitamos usar la anotacion @Data por el problema con jpa y la carga peresosa
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long price;

    @Lob
    private String description;

    @Lob
    //LONGBLOB es un tipo de datos binarios de MySQL
    @Column(columnDefinition = "longblob")
    private byte[] img;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    //Si se elimina una categoria tambien se eliminaran todas las entidades asociadas a ella
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CategoryEntity category;

}
