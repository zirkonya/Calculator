package fr.upjv.calculator.tools;

public enum Gamemode {
    CHRONO("CHR0N0"),
    ENDURANCE("3NDUR4NC3"),
    INFINITY("1NF1N1TY"),
    TIME_ATTACK("T1M3 4TT4CK"),
    SECRET("53CR3T");

    private String name;
    //private Class<? extends GameActivity> gameClass;

    Gamemode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
