package com.googlebot;

import com.googlebot.model.CheckboxesQuestion;
import com.googlebot.model.MultipleChoiceQuestion;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class GoogleFormBot {

    private WebDriver driver;
    private List<Question> questionList;
    private int pageNow = 1;

    public GoogleFormBot(WebDriver driver){
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void readPage(String url){
        driver.get(url);
        questionList = new ArrayList<>();

        List<WebElement> questionElementList = driver.findElements(By.xpath(Question.questionXpath));
        for (int i = 0; i<questionElementList.size(); i++) {
            QuestionType questionType = getQuestionType(questionElementList.get(i));
            if (questionType!=null){

                String questionTitle = getQuestionTitle(questionElementList.get(i));

                if (questionType == QuestionType.MULTIPLE_CHOICE){
                    MultipleChoiceQuestion question = new MultipleChoiceQuestion();
                    question.setQuestionTitle(questionTitle);
                    question.setQuestionType(questionType);
                    question.setQuestionElement(questionElementList.get(i));

                    List<MultipleChoiceModel> multipleChoiceModels = getQuestionMultipleChoicesData(
                            question
                    );
                    question.setQuestionData(multipleChoiceModels);

                    questionList.add(question);


                }else if (questionType == QuestionType.CHECKBOXES){
                    CheckboxesQuestion question = new CheckboxesQuestion();
                    question.setQuestionTitle(questionTitle);
                    question.setQuestionType(questionType);
                    question.setQuestionElement(questionElementList.get(i));

                    List<CheckboxesModel> checkboxesModels = getQuestionCheckboxesData(
                            question
                    );
                    question.setQuestionData(checkboxesModels);
                    questionList.add(question);

                }

            }

        }

        printQuestionPage();

    }

    private void printQuestionPage(){
        for(int i = 0; i<questionList.size(); i++){

            if (questionList.get(i) instanceof MultipleChoiceQuestion){
                MultipleChoiceQuestion question = (MultipleChoiceQuestion) questionList.get(i);
                System.out.println(i+" : "+question.getQuestionTitle()+"\t"+question.getQuestionType());
                for (int j = 0; j<question.getQuestionData().size(); j++){
                    System.out.println("\t"+i+" : "+question.getQuestionData().get(j).getTitle());
                }
            }else if (questionList.get(i) instanceof CheckboxesQuestion){
                CheckboxesQuestion question = (CheckboxesQuestion) questionList.get(i);
                System.out.println(i+" : "+question.getQuestionTitle()+"\t"+question.getQuestionType());
                for (int j = 0; j<question.getQuestionData().size(); j++){
                    System.out.println("\t"+j+" : "+question.getQuestionData().get(j).getTitle());
                }
            }

        }
    }

    private String getQuestionTitle(WebElement element){
        String title = element.findElement(By.xpath(Question.titleXpath)).getText();
        return title;
    }

    private QuestionType getQuestionType(WebElement element){
        String htmlCode = element.getAttribute("innerHTML");
        if (htmlCode.contains(QuestionType.PARAGRAPH.getValue())
        && htmlCode.contains("class=\"edhGSc zKHdkd kRy7qc RdH0ib yqQS1\" ")){
            return QuestionType.PARAGRAPH;
        }else if (htmlCode.contains(QuestionType.SHORT_ANSWER.getValue())){
            return QuestionType.SHORT_ANSWER;
        }else if (htmlCode.contains(QuestionType.MULTIPLE_CHOICE.getValue())){
            return QuestionType.MULTIPLE_CHOICE;
        }else if (htmlCode.contains(QuestionType.CHECKBOXES.getValue())){
            return QuestionType.CHECKBOXES;
        }else if (htmlCode.contains(QuestionType.DROPDOWN.getValue())){
            return QuestionType.DROPDOWN;
        }else if (htmlCode.contains(QuestionType.LINEAR_SCALE.getValue())){
            return QuestionType.LINEAR_SCALE;
        }else if (htmlCode.contains(QuestionType.CHECKBOX_GRID.getValue())
        && htmlCode.contains("jsname=\"IfcKPb\"")){
            return QuestionType.CHECKBOX_GRID;
        }else if (htmlCode.contains(QuestionType.MULTIPLE_CHOICE_GRID.getValue())){
            return QuestionType.MULTIPLE_CHOICE_GRID;
        }else if (htmlCode.contains(QuestionType.DATE.getValue())){
            return QuestionType.DATE;
        }else if (htmlCode.contains(QuestionType.TIME.getValue())){
            return QuestionType.TIME;
        }
        return null;
    }

    private List<MultipleChoiceModel> getQuestionMultipleChoicesData(MultipleChoiceQuestion question){

        List<MultipleChoiceModel> multipleChoiceModelList = new ArrayList<>();
        List<WebElement> multipleChoiceList = question.getQuestionElement().findElements(By.xpath(question.choiceXpath()));
        for (int i = 0 ;i<multipleChoiceList.size() ;i++){
            String choiceText = multipleChoiceList.get(i).findElement(By.xpath(question.labelXpath())).getText();
            WebElement radioButton = multipleChoiceList.get(i).findElement(By.xpath(question.controlXpath()));
            MultipleChoiceModel multipleChoiceModel = new MultipleChoiceModel();
            multipleChoiceModel.setTitle(choiceText);
            multipleChoiceModel.setCheckBoxElement(radioButton);
            multipleChoiceModelList.add(multipleChoiceModel);
        }

        return multipleChoiceModelList;

    }

    private List<CheckboxesModel> getQuestionCheckboxesData(CheckboxesQuestion question){
        List<CheckboxesModel> checkboxesModelList = new ArrayList<>();
        List<WebElement> checkboxesList = question.getQuestionElement().findElements(By.xpath(question.choiceXpath()));
        for (int i = 0; i<checkboxesList.size(); i++){
           String checkboxesTitle = checkboxesList.get(i).findElement(By.xpath(question.labelXpath())).getText();
           WebElement checkboxesElement = checkboxesList.get(i).findElement(By.xpath(question.controlXpath()));
           CheckboxesModel checkboxesModel  = new CheckboxesModel();
           checkboxesModel.setTitle(checkboxesTitle);
           checkboxesModel.setCheckboxElement(checkboxesElement);
           checkboxesModelList.add(checkboxesModel);
        }
        return checkboxesModelList;
    }


}
