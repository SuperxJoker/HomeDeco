package com.user.homedeco.model;

import java.util.Objects;

public class Forum{
    private String titleKey;
    private String questionKey;
    private String answerKey;

    @Override
    public String toString() {
        return "Forum{" +
                "titleKey='" + titleKey + '\'' +
                ", questionKey='" + questionKey + '\'' +
                ", answerKey='" + answerKey + '\'' +
                '}';
    }

    public Forum(String titleKey, String questionKey, String answerKey){
        this.titleKey = titleKey;
        this.questionKey = questionKey;
        this.answerKey = answerKey;
    }

    public Forum() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Forum)) return false;
        Forum forum = (Forum) o;
        return Objects.equals(getTitleKey(), forum.getTitleKey()) && Objects.equals(getQuestionKey(), forum.getQuestionKey()) && Objects.equals(getAnswerKey(), forum.getAnswerKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitleKey(), getQuestionKey(), getAnswerKey());
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
