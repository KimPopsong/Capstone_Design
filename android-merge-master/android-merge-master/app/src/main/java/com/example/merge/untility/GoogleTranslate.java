package com.example.merge.untility;

import android.content.Context;
import android.os.StrictMode;

import com.example.merge.Config;
import com.example.merge.R;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

import org.apache.commons.text.StringEscapeUtils;

import java.io.IOException;
import java.io.InputStream;

public class GoogleTranslate {

    static Translate translationService;

    /**
     * @param originalText from ProductReview.java
     * @return translated text
     */
    public static String translate(String originalText) {

        //Get input text to be translated:
        Translation translation = translationService.translate(
                originalText,
                Translate.TranslateOption.targetLanguage(Config.selectedLanguage),
                Translate.TranslateOption.model("nmt")  // not base only nmt. jot damm...
        );

        String translatedText = translation.getTranslatedText();

        return StringEscapeUtils.unescapeHtml4(translatedText);
    }

    /**
     * Why static? Because it called just once on ThisApplication.java.
     */
    public static void initialize(Context context) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try (InputStream is = context.getResources().openRawResource(R.raw.credential)) {

            //Get credentials:
            final GoogleCredentials myCredentials = GoogleCredentials.fromStream(is);

            //Set credentials and get translate service:
            TranslateOptions translateOptions = TranslateOptions.newBuilder().setCredentials(myCredentials).build();
            translationService = translateOptions.getService();

        } catch (IOException ioe) {
            ioe.printStackTrace();

        }
    }
}

