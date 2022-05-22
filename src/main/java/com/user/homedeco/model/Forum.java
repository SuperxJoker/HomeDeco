package com.user.homedeco.model;

public class Forum{
    private String titleKey;
    private String questionKey;
    private String answerKey;

    public Forum(String titleKey,String questionKey,String answerKey){
        this.titleKey = titleKey;
        this.questionKey = questionKey;
        this.answerKey = answerKey;

    }

    public String getTitleKey(){
        return titleKey;
    }

    public void setTitleKey(String titleKey){
        this.titleKey = titleKey;
    }

    public String getQuestionKey(){
        return questionKey;
    }

    public void setQuestionKey(String questionKey){
        this.questionKey = questionKey;
    }

    public String getAnswerKey(){
        return answerKey;
    }

    public void setAnswerKey(String answerKey){
        this.answerKey = answerKey;
    }
}
