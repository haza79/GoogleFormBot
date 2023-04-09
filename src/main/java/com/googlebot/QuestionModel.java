package com.googlebot;

import lombok.Data;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

@Data
public class QuestionModel {

    private int page = 1;
    private WebElement questionElement;
    private QuestionType questionType;
    private String questionTitle;

    private List<MultipleChoiceModel> multipleChoiceModelList;
    private List<CheckboxesModel> checkboxesModelList;

}
