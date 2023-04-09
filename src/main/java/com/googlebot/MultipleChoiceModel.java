package com.googlebot;

import lombok.Data;
import org.openqa.selenium.WebElement;

@Data
public class MultipleChoiceModel {

    private WebElement checkBoxElement;
    private String title;

}
