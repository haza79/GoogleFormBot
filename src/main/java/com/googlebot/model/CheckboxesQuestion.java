package com.googlebot.model;

import com.googlebot.CheckboxesModel;
import com.googlebot.MultipleChoiceModel;
import com.googlebot.Question;
import com.googlebot.QuestionType;
import lombok.Data;
import org.openqa.selenium.WebElement;

import java.util.List;

@Data
public class CheckboxesQuestion implements Question {

    private int page = 1;
    private WebElement questionElement;
    private QuestionType questionType;
    private String questionTitle;
    private List<CheckboxesModel> questionData;

    @Override
    public String choiceXpath() {
        return "./div/div/div[2]/div[1]/div";
    }

    @Override
    public String controlXpath() {
        return "./label/div/div[1]";
    }

    @Override
    public String labelXpath() {
        return "./label/div/div[2]/div/span";
    }
}
