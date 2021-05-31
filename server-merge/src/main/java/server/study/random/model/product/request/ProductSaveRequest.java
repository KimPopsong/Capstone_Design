package server.study.random.model.product.request;

import lombok.Data;

@Data
public class ProductSaveRequest {
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
}
