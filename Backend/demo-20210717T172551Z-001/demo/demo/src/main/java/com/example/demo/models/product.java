package com.example.demo.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Entity
@Data
public class product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id_prod;

    private String nom;
    private int quantite;
    private  boolean disponble;
    private Date date_creation;
    private  Date  date_modif;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "category_id", insertable = true, updatable = false)
    private  Category category;


}
