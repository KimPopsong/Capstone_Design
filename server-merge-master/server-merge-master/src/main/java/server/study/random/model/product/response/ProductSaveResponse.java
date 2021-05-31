package server.study.random.model.product.response;

import lombok.Data;
import server.study.random.domain.Product;

import java.util.List;

@Data
public class ProductSaveResponse {

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

    public ProductSaveResponse(Product product) {
        this.id = product.getId();
        this.image_name = product.getImage_name();
        this.name = product.getName();
        this.manufacturer = product.getManufacturer();
        this.kind = product.getKind();
        this.serving_per_container = product.getServing_per_container();
        this.energe = product.getEnerge();
        this.carbohydrate = product.getCarbohydrate();
        this.dietary_fiber = product.getDietary_fiber();
        this.sugar = product.getSugar();
        this.protein = product.getProtein();
        this.total_fat = product.getTotal_fat();
        this.saturated_fat = product.getSaturated_fat();
        this.trans_fat = product.getTrans_fat();
        this.cholesterol = product.getCholesterol();
        this.sodium = product.getSodium();
        this.calcium = product.getCalcium();
        this.iron = product.getIron();
    }

    public ProductSaveResponse(List<Product> productList){

    }
}
