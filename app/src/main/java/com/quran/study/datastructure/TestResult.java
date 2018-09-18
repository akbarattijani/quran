package com.quran.study.datastructure;

public class TestResult {
    private String id;
    private String nameSurah;
    private String date;
    private int scor;
    private String rate;

    public TestResult setId(String id) {
        this.id = id;
        return this;
    }

    public TestResult setNameSurah(String nameSurah) {
        this.nameSurah = nameSurah;
        return this;
    }

    public TestResult setDate(String date) {
        this.date = date;
        return this;
    }

    public TestResult setScor(int scor) {
        this.scor = scor;
        return this;
    }

    public TestResult setRate(String rate) {
        this.rate = rate;
        return this;
    }

    public String getId() {
        return id;
    }

    public String getNameSurah() {
        return nameSurah;
    }

    public String getDate() {
        return date;
    }

    public int getScor() {
        return scor;
    }

    public String getRate() {
        return rate;
    }
}
