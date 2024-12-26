package org.tests;

import org.base.Base_Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.pages.Opinion_Page;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.utils.Translate_Class;
import org.utils.WebdriverUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpinionScraperTest {
    WebDriver driver;
    Opinion_Page opinionPage;
    Translate_Class translator;

    @BeforeTest
    public void setup() {
        driver = WebdriverUtil.setupDriver();
        opinionPage = new Opinion_Page(driver);
        translator = new Translate_Class();
        driver.manage().window().maximize();
        driver.get("https://elpais.com/");
    }

    @Test
    public void scrapeAndAnalyze() {
        opinionPage.navigateToOpinionSection();

        List<WebElement> articleTitles = opinionPage.getArticleTitles();
        Map<String, Integer> wordCount = new HashMap<>();

        for (int i = 0; i < 5; i++) {
            WebElement titleElement = articleTitles.get(i);
            String title = titleElement.getText();
            System.out.println("Title in Spanish: " + title);

            
            String translatedTitle = translator.translateToEnglish(title);
            System.out.println("Translated Title: " + translatedTitle);

           
            for (String word : translatedTitle.split(" ")) {
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            }

            WebElement contentElement = opinionPage.getArticleContent(i);
            System.out.println("Article Content: " + contentElement.getText());

            WebElement imageElement = opinionPage.getArticleImage(i);
            opinionPage.saveImage(imageElement, "path/to/save/image" + i + ".jpg");
        }

        wordCount.forEach((word, count) -> {
            if (count > 2) {
                System.out.println("Word '" + word + "' appears " + count + " times.");
            }
        });
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}

