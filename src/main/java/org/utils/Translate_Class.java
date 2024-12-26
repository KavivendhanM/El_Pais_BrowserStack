package org.utils;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import java.util.HashMap;
import java.util.Map;

public class Translate_Class {
    private static final String API_KEY = "YOUR_API_KEY";

    public String translateToEnglish(String text) {
        String translatedText = "";
        try {
            Translate translate = TranslateOptions.newBuilder().setApiKey(API_KEY).build().getService();

            Translation translation = translate.translate(
                text,
                Translate.TranslateOption.sourceLanguage("es"),
                Translate.TranslateOption.targetLanguage("en")
            );

            translatedText = translation.getTranslatedText();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return translatedText;
    }
}


