package homeostaticseasons.event;

import java.util.Optional;

import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.clock.WorldClock;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.storage.ServerLevelData;

import homeostaticseasons.api.HomeostaticSeasonsAPI;
import homeostaticseasons.api.Season;
import homeostaticseasons.api.SeasonChangeMethod;
import homeostaticseasons.api.SeasonWeather;
import homeostaticseasons.common.season.Weather;
import homeostaticseasons.config.ConfigHandler;
import homeostaticseasons.config.ConfigHandler.Common;
import homeostaticseasons.platform.Services;

public class ServerEventHandler {

    static Season lastSeason = null;

    /*
     * Check every second to see if the season has changed, and update the weather accordingly.
     */
    public static void onLevelTick(ServerLevel level) {
        if (SeasonWeather.isValid(level) && level.getDefaultClockTime() % 20 == 0) {
            Season currentSeason = HomeostaticSeasonsAPI.getCurrentSeason(level);

            if (lastSeason != currentSeason) {
                lastSeason = currentSeason;
                Weather.updateWeather(level, currentSeason);
            }
        }
    }

    public static void onLevelLoad(ServerLevel level) {
        if (SeasonWeather.isValid(level) && Common.snowAccumulationHeight() > 1) {
            level.getGameRules().set(GameRules.MAX_SNOW_ACCUMULATION_HEIGHT, Common.snowAccumulationHeight(), level.getServer());
        }

        if (ConfigHandler.Common.seasonChangeMethod() == SeasonChangeMethod.CONFIGURED
                && ConfigHandler.Common.startingSeason() != Season.EARLY_SPRING) {
            for (ServerLevel serverlevel : level.getServer().getAllLevels()) {
                ServerLevelData levelData = Services.PLATFORM.getServerLevelData(serverlevel);

                // Check if this is a newly created world and set the starting season if not the default
                if (levelData.getGameTime() < 100L) {
                    long time = HomeostaticSeasonsAPI.getSeasonTime(serverlevel, ConfigHandler.Common.startingSeason());
                    Optional<Holder<WorldClock>> clockHolder = level.dimensionType().defaultClock();

                    if (clockHolder.isPresent() && time != -1L) {
                        level.getServer().clockManager().setTotalTicks(clockHolder.get(), time);
                    }
                }
            }
        }
    }

}
