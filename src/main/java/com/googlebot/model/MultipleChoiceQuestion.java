package com.googlebot.model;

import com.googlebot.MultipleChoiceModel;
import com.googlebot.Question;
import com.googlebot.QuestionModel;
import com.googlebot.QuestionType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebElement;

import java.util.List;

@Data
public class MultipleChoiceQuestion implements Question  {

    private int page = 1;
    private WebElement questionElement;
    private QuestionType questionType;
    private String questionTitle;
    private List<MultipleChoiceModel> questionData;

    @Override
    public String choiceXpath() {
        return "./div/div/div[2]/div[1]/div/span/div/div/label/div";
    }

    @Override
    public String controlXpath() {
        return "./div[1]/div";
    }

    @Override
    public String labelXpath() {
        return "./div[2]/div/span";
    }
}
