package org.pages;

import org.base.Base_Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class Opinion_Page extends Base_Page {
    private By articleTitles = By.xpath("//h2[@class='c_t c_t-i ']/child::a");
    private By articleContents = By.xpath("//h2[@class='c_t c_t-i ']/following::p"); 
    private By articleImages = By.xpath("//img[@class='c_m_e _re lazyload a_m-h']");

    public Opinion_Page(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getArticleTitles() {
        return driver.findElements(articleTitles);
    }

    public WebElement getArticleContent(int index) {
        return driver.findElements(articleContents).get(index);
    }

    public WebElement getArticleImage(int index) {
        return driver.findElements(articleImages).get(index);
    }

    public void navigateToOpinionSection() {
        driver.get("https://elpais.com/opinion/");
    }
}