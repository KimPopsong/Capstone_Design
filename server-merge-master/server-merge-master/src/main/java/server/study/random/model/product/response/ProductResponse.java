package server.study.random.model.product.response;

import lombok.Data;
import server.study.random.domain.Product;

@Data
public class ProductResponse {

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

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.image_name = product.getImage_name();
        this.name = product.getName();
        this.manufacturer = product.getManufacturer();
        this.kind = product.getKind();
        this.servingPerContainer = product.getServing_per_container();
        this.energy = product.getEnerge();
        this.carbohydrate = product.getCarbohydrate();
        this.dietaryFiber = product.getDietary_fiber();
        this.sugar = product.getSugar();
        this.protein = product.getProtein();
        this.totalFat = product.getTotal_fat();
        this.saturatedFat = product.getSaturated_fat();
        this.transFat = product.getTrans_fat();
        this.cholesterol = product.getCholesterol();
        this.sodium = product.getSodium();
        this.calcium = product.getCalcium();
        this.iron = product.getIron();
    }
}
