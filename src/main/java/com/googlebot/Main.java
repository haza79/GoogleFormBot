package com.googlebot;

import com.googlebot.model.MultipleChoiceQuestion;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.chromedriver().ignoreDriverVersions();
        WebDriver driver = new ChromeDriver();
        GoogleFormBot googleFormBot = new GoogleFormBot(driver);
        googleFormBot.readPage(
                "https://docs.google.com/forms/d/e/1FAIpQLSef8Sbj23E7p1qNX3_Vsedckpqxpw1-b-qRrXNAGeqYEM5maA/viewform"
        );


//        googleFormBot.readPage(
//                "https://docs.google.com/forms/d/e/1FAIpQLSdH00witdUtpvJFmdisaPtI5UJbnCCAhFq4PKmsnpSOLmOgBg/viewform"
//        );






    }

}
