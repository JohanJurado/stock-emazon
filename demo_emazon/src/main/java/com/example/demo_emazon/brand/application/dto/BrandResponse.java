package com.example.demo_emazon.brand.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BrandResponse {

    private Long idBrand;
    private String nameBrand;
    private String descriptionBrand;

}
