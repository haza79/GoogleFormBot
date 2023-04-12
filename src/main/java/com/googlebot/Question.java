package com.googlebot;

import org.openqa.selenium.WebElement;

import java.util.List;

public interface Question {

    String questionXpath = "//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div";
    String titleXpath = "./div/div/div[1]/div/div/span[1]";

    String choiceXpath();
    String controlXpath();
    String labelXpath();

}
