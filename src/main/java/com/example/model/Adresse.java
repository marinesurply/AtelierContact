package com.example.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Adresse {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String adresse;
   // private String ville;
    //private Integer codepostal;


    public Adresse() {

    }
    public Adresse(String adresse) {
        this.adresse = adresse;
       // this.ville = ville;
        //this.codepostal = codepostal;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

   /* public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Integer getCodepostal() {
        return codepostal;
    }

    public void setCodepostal(Integer codepostal) {
        this.codepostal = codepostal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Collection<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Collection<Contact> contacts) {
        this.contacts = contacts;
    }*/
}
