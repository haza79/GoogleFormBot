package com.googlebot;

import lombok.Data;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

@Data
public class QuestionModel {

    private static final String questionXpath = "//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div";
    private static final String titleXpath = "./div/div/div[1]/div/div/span[1]";

    private int page = 1;
    private WebElement questionElement;
    private QuestionType questionType;
    private String questionTitle;
    private String choiceXpath;
    private String controlXpath;
    private String labelXpath;




}
