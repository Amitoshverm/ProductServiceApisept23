package com.amitosh.productservice.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "categories")
@Getter
@Setter
public class Category extends BaseModel{

    private String name;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
    // This is done while mapping on both the sides
}
