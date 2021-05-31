package com.example.merge.product.info;

import com.example.merge.Config;
import com.example.merge.entities.ProductInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 제품 정보중 하나의 '항목'을 나타냅니다.
 *
 * UI에다가 똑같이 생긴 LinearLayout 여러 개 만들기 싫어서 이렇게 했습니다.
 * 나중에 혹시라도 ProductInfo가 바뀐다면 여기서 그것만 반영해주면 UI까지 바꾸지는 않아도 됩니다.
 * 아래 TO DO 참고하세요
 */
public class ProductInfoAttribute {
    private final String name;
    private final String value;
    private final String unit;

    public ProductInfoAttribute(String name, String value, String unit) {
        this.name = name;
        this.value = value;
        this.unit = unit;
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }

    public String getUnit() {
        return this.unit;
    }
    

    public static List<ProductInfoAttribute> attributesFromProductInfo(ProductInfo info) {
        ArrayList<ProductInfoAttribute> l = new ArrayList<>();
        // 한글 이름과 그 값의 매핑은 여기서 일어납니다.
//        l.add(new ProductInfoAttribute("이름", info.getName(), ""));
//        l.add(new ProductInfoAttribute("제조사", info.getManufacturer(), ""));

        //l.add(new ProductInfoAttribute(Config.infoArray.get(0), info.getKind(), ""));
        l.add(new ProductInfoAttribute(Config.infoArray.get(1), info.getServingPerContainer(), "g"));
        l.add(new ProductInfoAttribute(Config.infoArray.get(2), info.getEnergy(), "kcal"));
        l.add(new ProductInfoAttribute(Config.infoArray.get(3), info.getCarbohydrate(), "g"));
        l.add(new ProductInfoAttribute(Config.infoArray.get(4), info.getDietaryFiber(), "g"));
        l.add(new ProductInfoAttribute(Config.infoArray.get(5), info.getSugar(), "g"));
        l.add(new ProductInfoAttribute(Config.infoArray.get(6), info.getProtein(), "g"));
        l.add(new ProductInfoAttribute(Config.infoArray.get(7), info.getTotalFat(), "g"));
        l.add(new ProductInfoAttribute(Config.infoArray.get(8), info.getSaturatedFat(), "g"));
        l.add(new ProductInfoAttribute(Config.infoArray.get(9), info.getTransFat(), "g"));
        l.add(new ProductInfoAttribute(Config.infoArray.get(10), info.getCholesterol(), "mg"));
        l.add(new ProductInfoAttribute(Config.infoArray.get(11), info.getSodium(), "mg"));
        l.add(new ProductInfoAttribute(Config.infoArray.get(12), info.getCalcium(), "mg"));
        l.add(new ProductInfoAttribute(Config.infoArray.get(13), info.getIron(), "mg"));

        return l;
    }
}
