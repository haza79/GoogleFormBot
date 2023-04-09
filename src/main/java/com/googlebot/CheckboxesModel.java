package com.googlebot;

import lombok.Data;
import org.openqa.selenium.WebElement;

@Data
public class CheckboxesModel {

    private WebElement checkboxElement;
    private String title;

}
