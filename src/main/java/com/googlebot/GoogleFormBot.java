package com.googlebot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class GoogleFormBot {

    private WebDriver driver;
    private List<QuestionModel> questionModelArrayList;
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
        questionModelArrayList = new ArrayList<>();
        List<WebElement> questionList = driver.findElements(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div"));
        for (int i = 0; i<questionList.size(); i++) {
            String questionXpath = "//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div["+(i+1)+"]";
            WebElement questionElement = driver.findElement(By.xpath(questionXpath));
            QuestionType questionType = getQuestionType(questionElement);
            if (questionType!=null){
                String title = getQuestionTitle(questionElement);
                QuestionModel questionModel = new QuestionModel();
                questionModel.setQuestionElement(questionElement);
                questionModel.setQuestionType(questionType);
                questionModel.setQuestionTitle(title);
                questionModel.setPage(pageNow);

                if (questionModel.getQuestionType() == QuestionType.MULTIPLE_CHOICE){
                    List<MultipleChoiceModel> multipleChoiceModels = getQuestionMultipleChoicesData(
                            questionModel.getQuestionElement()
                    );
                    questionModel.setMultipleChoiceModelList(multipleChoiceModels);
                }else if (questionModel.getQuestionType() == QuestionType.CHECKBOXES){
                    List<CheckboxesModel> checkboxesModelList = getQuestionCheckboxesData(
                            questionModel.getQuestionElement()
                    );
                    questionModel.setCheckboxesModelList(checkboxesModelList);
                }

                questionModelArrayList.add(questionModel);

            }

        }

        for (int i = 0; i<questionModelArrayList.size(); i++){
            System.out.println(i+" : "+questionModelArrayList.get(i).getQuestionTitle()+" | "+questionModelArrayList.get(i).getQuestionType());

            if (questionModelArrayList.get(i).getQuestionType() == QuestionType.MULTIPLE_CHOICE){
                for (int j = 0; j<questionModelArrayList.get(i).getMultipleChoiceModelList().size(); j++){
                    System.out.println("     "+j+" : "+questionModelArrayList.get(i).getMultipleChoiceModelList().get(j).getTitle());
                }
            }else if (questionModelArrayList.get(i).getQuestionType() == QuestionType.CHECKBOXES){
                for (int j = 0; j<questionModelArrayList.get(i).getCheckboxesModelList().size(); j++){
                    System.out.println("     "+j+" : "+questionModelArrayList.get(i).getCheckboxesModelList().get(j).getTitle());
                }
            }

        }

    }

    private String getQuestionTitle(WebElement element){
        String xpath = ".//div/div/div[1]/div/div/span[1]";
        String title = element.findElement(By.xpath(xpath)).getText();
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

    private List<MultipleChoiceModel> getQuestionMultipleChoicesData(WebElement element){

        List<MultipleChoiceModel> multipleChoiceModelList = new ArrayList<>();
        List<WebElement> multipleChoiceList = element.findElements(By.xpath("./div/div/div[2]/div[1]/div/span/div/div"));
        for (int i = 0 ;i<multipleChoiceList.size() ;i++){
            WebElement multipleChoiceElement = multipleChoiceList.get(i).findElement(By.xpath("./label/div"));
            String choiceText = multipleChoiceElement.findElement(By.xpath("./div[2]/div/span")).getText();
            WebElement radioButton = multipleChoiceElement.findElement(By.xpath("./div[1]/div"));
            MultipleChoiceModel multipleChoiceModel = new MultipleChoiceModel();
            multipleChoiceModel.setTitle(choiceText);
            multipleChoiceModel.setCheckBoxElement(radioButton);
            multipleChoiceModelList.add(multipleChoiceModel);
        }

        return multipleChoiceModelList;

    }

    private List<CheckboxesModel> getQuestionCheckboxesData(WebElement element){
        List<CheckboxesModel> checkboxesModelList = new ArrayList<>();
        List<WebElement> checkboxesList = element.findElements(By.xpath("./div/div/div[2]/div[1]/div"));
        for (int i = 0; i<checkboxesList.size(); i++){
           String checkboxesTitle = checkboxesList.get(i).findElement(By.xpath("./label/div/div[2]/div/span")).getText();
           WebElement checkboxesElement = checkboxesList.get(i).findElement(By.xpath("./label/div/div[1]"));
           CheckboxesModel checkboxesModel  = new CheckboxesModel();
           checkboxesModel.setTitle(checkboxesTitle);
           checkboxesModel.setCheckboxElement(checkboxesElement);
           checkboxesModelList.add(checkboxesModel);
        }
        return checkboxesModelList;
    }


}
