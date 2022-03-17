package fr.upjv.calculator.entity;

public class TimeAttack extends BaseEntity {

    Integer Score;
    String Name;

    public Integer getScore() {
        return Score;
    }

    public String getName() {
        return Name;
    }

    public void setScore(Integer score) {
        this.Score = Score;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
}
