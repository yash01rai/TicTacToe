package models;

import strategies.botPlayingStrategies.BotPlayingStrategy;

public class Bot extends Player {

    BotDifficultyLevel botDifficultyLevel;
    BotPlayingStrategy botPlayingStrategy;


    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }
}
