package com.example.merge;

import android.content.res.Resources;

import com.example.merge.untility.GoogleTranslate;

import java.util.ArrayList;
import java.util.List;

public class Config {
    //public static String serverBaseUrl = "http://10.0.2.2:8080";

    public static final String serverBaseUrl = "http://c03894666c37.ngrok.io";  // TODO 포트 포워딩 하기

    public static final String productImageBaseUrl = "https://www.foodsafetykorea.go.kr/fcdb/images/processed/";

    public static String selectedLanguage = "en";

    public static List<String> infoArray;

    public static void translateInfo() {
        infoArray = new ArrayList<>();

        Resources resources = ThisApplication.getContext().getResources();

        infoArray.add(GoogleTranslate.translate(resources.getString(R.string.info1)));
        infoArray.add(GoogleTranslate.translate(resources.getString(R.string.info2)));
        infoArray.add(GoogleTranslate.translate(resources.getString(R.string.info3)));
        infoArray.add(GoogleTranslate.translate(resources.getString(R.string.info4)));
        infoArray.add(GoogleTranslate.translate(resources.getString(R.string.info5)));
        infoArray.add(GoogleTranslate.translate(resources.getString(R.string.info6)));
        infoArray.add(GoogleTranslate.translate(resources.getString(R.string.info7)));
        infoArray.add(GoogleTranslate.translate(resources.getString(R.string.info8)));
        infoArray.add(GoogleTranslate.translate(resources.getString(R.string.info9)));
        infoArray.add(GoogleTranslate.translate(resources.getString(R.string.info10)));
        infoArray.add(GoogleTranslate.translate(resources.getString(R.string.info11)));
        infoArray.add(GoogleTranslate.translate(resources.getString(R.string.info12)));
        infoArray.add(GoogleTranslate.translate(resources.getString(R.string.info13)));
        infoArray.add(GoogleTranslate.translate(resources.getString(R.string.info14)));
    }
}
