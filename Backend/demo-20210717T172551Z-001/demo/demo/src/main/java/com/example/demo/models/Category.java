package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collection;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlTransient;

@Entity

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    private  int qt;
    private  String nom ;
    private Date date_creation ;
    private   Date date_modif ;
    @JsonIgnore
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private Set<product> products ;

    /*@OneToMany( mappedBy ="Category",fetch=FetchType.LAZY )
    @JoinColumn(name = "id")
    private Collection<product> products ;*/

    public Category() {
        super(); }
    @JsonIgnore
    @XmlTransient
    public Collection<product> getProducts(){
        Collection<product> produits = null;
        return produits;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", qt=" + qt +
                ", nom='" + nom + '\'' +
                ", date_creation=" + date_creation +
                ", date_modif=" + date_modif +
                '}';
    }

    public Date getDate_modif() {
        return date_modif;
    }

    public void setDate_modif(Date date_modif) {
        this.date_modif = date_modif;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getQt() {
        return qt;
    }
    public void setQt(int qt) {
        this.qt = qt;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

}
