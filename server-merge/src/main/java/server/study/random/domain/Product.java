package server.study.random.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private String code;
    private String image_name;
    private String name;
    private String manufacturer;
    private String kind;
    private String serving_per_container;
    private String energe;
    private String carbohydrate;
    private String dietary_fiber;
    private String sugar;
    private String protein;
    private String total_fat;
    private String saturated_fat;
    private String trans_fat;
    private String cholesterol;
    private String sodium;
    private String calcium;
    private String iron;

    public static Product createProduct(String code, String image_name, String name, String manufacturer, String kind, String servingPerContainer, String energy,
                                        String carbohydrate, String dietaryFiber, String sugar, String protein, String totalFat, String saturatedFat,
                                        String transFat, String cholesterol, String sodium, String calcium, String iron) {
        Product product = new Product();

        product.code = code;
        product.image_name = image_name;
        product.name = name;
        product.manufacturer = manufacturer;
        product.kind = kind;
        product.serving_per_container = servingPerContainer;
        product.energe = energy;
        product.carbohydrate = carbohydrate;
        product.dietary_fiber = dietaryFiber;
        product.sugar = sugar;
        product.protein = protein;
        product.total_fat = totalFat;
        product.saturated_fat = saturatedFat;
        product.trans_fat = transFat;
        product.cholesterol = cholesterol;
        product.sodium = sodium;
        product.calcium = calcium;
        product.iron = iron;

        return product;
    }

}
