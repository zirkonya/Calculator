package fr.upjv.calculator.entity;

public class Chrono extends BaseEntity {

    String Name;
    Integer Score;

    public Integer getScore() {
        return Score;
    }

    public String getName() {
        return Name;
    }

    public void setScore(Integer Score) {
        this.Score = Score;
    }

    public void setName(String Name) {
        this.Name = Name;
    }



}
