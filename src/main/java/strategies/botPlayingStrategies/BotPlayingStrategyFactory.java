package strategies.botPlayingStrategies;

import models.BotDifficultyLevel;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategyByLevel(BotDifficultyLevel botDifficultyLevel) {
        return new EasyBotPlayingStrategy();
    }
}
