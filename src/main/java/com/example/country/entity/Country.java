package com.example.country.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code")
    private String code;


//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//    private List<CountryLanguage> countryLanguages;

    public Country(String code) {
        this.code = code;
    }


}