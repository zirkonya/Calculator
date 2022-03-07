package fr.upjv.calculator.tools;

import fr.upjv.calculator.activities.GameActivity;
import fr.upjv.calculator.activities.game.ChronoGame;
import fr.upjv.calculator.activities.game.EnduranceGame;
import fr.upjv.calculator.activities.game.TimeAttackGame;

public enum GameMode {
    CHRONO("CHR0N0", ChronoGame.class),
    ENDURANCE("3NDUR4NC3", EnduranceGame.class),
    TIME_ATTACK("T1M3 4TT4CK", TimeAttackGame.class);

    private String name;
    private Class<? extends GameActivity> gameClass;

    GameMode(String name, Class<? extends GameActivity> gameClass) {
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
