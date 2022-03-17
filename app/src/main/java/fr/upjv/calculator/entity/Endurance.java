package fr.upjv.calculator.entity;

public class Endurance extends BaseEntity{

    Integer Score;
    long Temps;
    String Name;

    public Integer getScore() {
        return Score;
    }

    public Long getTemps() {
        return Temps;
    }

    public String getName() {
        return Name;
    }

    public void setScore(Integer score) {
        this.Score = Score;
    }

    public void setTemps(long temps) {
        this.Temps = temps;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

}
