package com.example.merge.entities;

import androidx.annotation.Nullable;

/**
 * 제품의 정보를 나타내는 클래스입니다.
 */
public class ProductInfo {
    private Long id;
    private String code;
    private String image_name;
    private String name;
    private String manufacturer;
    private String kind;
    private String servingPerContainer;
    private String energy;
    private String carbohydrate;
    private String dietaryFiber;
    private String sugar;
    private String protein;
    private String totalFat;
    private String saturatedFat;
    private String transFat;
    private String cholesterol;
    private String sodium;
    private String calcium;
    private String iron;

    public ProductInfo(Long id, String code, String image_name, String name, String manufacturer, String kind, String servingPerContainer, String energy,
                       String carbohydrate, String dietaryFiber, String sugar, String protein, String totalFat, String saturatedFat,
                       String transFat, String cholesterol, String sodium, String calcium, String iron) {
        this.id = id;
        this.code = code;
        this.image_name = image_name;
        this.name = name;
        this.manufacturer = manufacturer;
        this.kind = kind;
        this.servingPerContainer = servingPerContainer;
        this.energy = energy;
        this.carbohydrate = carbohydrate;
        this.dietaryFiber = dietaryFiber;
        this.sugar = sugar;
        this.protein = protein;
        this.totalFat = totalFat;
        this.saturatedFat = saturatedFat;
        this.transFat = transFat;
        this.cholesterol = cholesterol;
        this.sodium = sodium;
        this.calcium = calcium;
        this.iron = iron;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof ProductInfo) {
            return this.id.equals(((ProductInfo) obj).id);
        } else {
            return super.equals(obj);
        }
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

//    public String getKind() {
//        return GoogleTranslate.translate(kind);
//    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getServingPerContainer() {
        return servingPerContainer;
    }

    public void setServingPerContainer(String servingPerContainer) {
        this.servingPerContainer = servingPerContainer;
    }

    public String getEnergy() {
        return energy;
    }

    public void setEnergy(String energy) {
        this.energy = energy;
    }

    public String getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(String carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public String getDietaryFiber() {
        return dietaryFiber;
    }

    public void setDietaryFiber(String dietaryFiber) {
        this.dietaryFiber = dietaryFiber;
    }

    public String getSugar() {
        return sugar;
    }

    public void setSugar(String sugar) {
        this.sugar = sugar;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(String totalFat) {
        this.totalFat = totalFat;
    }

    public String getSaturatedFat() {
        return saturatedFat;
    }

    public void setSaturatedFat(String saturatedFat) {
        this.saturatedFat = saturatedFat;
    }

    public String getTransFat() {
        return transFat;
    }

    public void setTransFat(String transFat) {
        this.transFat = transFat;
    }

    public String getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(String cholesterol) {
        this.cholesterol = cholesterol;
    }

    public String getSodium() {
        return sodium;
    }

    public void setSodium(String sodium) {
        this.sodium = sodium;
    }

    public String getCalcium() {
        return calcium;
    }

    public void setCalcium(String calcium) {
        this.calcium = calcium;
    }

    public String getIron() {
        return iron;
    }

    public void setIron(String iron) {
        this.iron = iron;
    }
}
