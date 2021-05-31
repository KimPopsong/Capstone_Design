package com.example.merge.network;

import com.example.merge.entities.ProductInfo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * 아래의 예제 값을 가지고 https://www.jsonschema2pojo.org 에서 만듦
 * <p>
 * {
 * "id": 1,
 * "kind": "ramen",
 * "name": "zzapagetti",
 * "kcal": "10",
 * "protein": "9",
 * "fat": "8",
 * "carbohydrate": "7",
 * "sugar": "6"
 * }
 *
 * =>
 *
 * DB Table 수정
 */
public class ProductInfoDTO {
    @SerializedName("id")
    @Expose
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

    public ProductInfo toProductInfo() {
        return new ProductInfo(
                id, code, image_name, name, manufacturer, kind, servingPerContainer,
                energy, carbohydrate, dietaryFiber, sugar, protein, totalFat,
                saturatedFat, transFat, cholesterol, sodium, calcium, iron
        );
    }
}
