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
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "localization")
    private String localization;

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//    private List<CountryLanguage> countryLanguages;

    public Language(String localization) {
        this.localization = localization;
    }

}
