package com.example.demo_emazon.brand.domain.model;

public class Brand {

    private Long idBrand;
    private String nameBrand;
    private String descriptionBrand;

    public Brand(Long idBrand, String nameBrand, String descriptionBrand) {
        this.idBrand = idBrand;
        this.nameBrand = nameBrand;
        this.descriptionBrand = descriptionBrand;
    }

    public Brand() {
    }

    public Long getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(Long idBrand) {
        this.idBrand = idBrand;
    }

    public String getNameBrand() {
        return nameBrand;
    }

    public void setNameBrand(String nameBrand) {
        this.nameBrand = nameBrand;
    }

    public String getDescriptionBrand() {
        return descriptionBrand;
    }

    public void setDescriptionBrand(String descriptionBrand) {
        this.descriptionBrand = descriptionBrand;
    }
}
