package fr.upjv.calculator.tools;

public enum Difficulty {
    SIMPLE("51MPL3"),
    NORMAL("N0RM4L"),
    COMPLEX("C0MPL3X"),
    HARD("H4RD"),
    HELL("H3LL");

    private String name;

    Difficulty(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
