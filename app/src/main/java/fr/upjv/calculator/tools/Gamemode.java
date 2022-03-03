package fr.upjv.calculator.tools;

import fr.upjv.calculator.activities.GameActivity;
import fr.upjv.calculator.activities.game.ChronoGame;

public enum Gamemode {
    CHRONO("CHR0N0", ChronoGame.class),
    ENDURANCE("3NDUR4NC3", null),
    INFINITY("1NF1N1TY", null),
    TIME_ATTACK("T1M3 4TT4CK", null),
    SECRET("53CR3T", null);

    private String name;
    private Class<? extends GameActivity> gameClass;

    Gamemode(String name, Class<? extends GameActivity> gameClass) {
        this.name = name;
        this.gameClass = gameClass;
    }

    public String getName() {
        return name;
    }

    public Class<? extends GameActivity> getGameClass() {
        return gameClass;
    }
}
