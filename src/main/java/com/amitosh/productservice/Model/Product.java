package com.amitosh.productservice.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseModel {

    private String title;
    private String description;
    private String image;
    /*
    *          P  :  C
    * ->       1  :  1
    * ->       M  :  1
    *card->    M  :  1 */

    @ManyToOne
    @JoinColumn
    private Category category;
    //when mapping we have to do like this
    @ManyToOne
    private Price price;

}
