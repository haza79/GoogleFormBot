package com.googlebot;

public enum QuestionType {
    SHORT_ANSWER("jscontroller=\"oCiKKc\""),
    PARAGRAPH("jscontroller=\"oCiKKc\""),
    MULTIPLE_CHOICE("jscontroller=\"UmOCme\""),
    CHECKBOXES("jscontroller=\"sW52Ae\""),
    DROPDOWN("jscontroller=\"liFoG\""),
    LINEAR_SCALE("jscontroller=\"FYWcYb\""),
    MULTIPLE_CHOICE_GRID("jscontroller=\"tjSPQb\""),
    CHECKBOX_GRID("jscontroller=\"tjSPQb\""),
    DATE("jscontroller=\"lLliLe\""),
    TIME("jscontroller=\"OZjhxc\"");

    private final String value;
    QuestionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }



}
