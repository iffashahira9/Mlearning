package com.example.m_learning;

public class QuesList {
    private String question,option1,option2,option3,answer;
    private String UserSelectAns;




    public QuesList(String question, String option1, String option2, String option3, String answer) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.answer = answer;
        this.UserSelectAns = "";

    }

    public String getQuestion() {

        return question;
    }

    public String getOption1() {

        return option1;
    }

    public String getOption2() {

        return option2;
    }

    public String getOption3() {

        return option3;
    }

    public String getAnswer() {

        return answer;
    }

    public String getUserSelectAns() {

        return UserSelectAns;
    }

    public void setUserSelectAns(String UserSelectAns){

        this.UserSelectAns = UserSelectAns;
    }


}
