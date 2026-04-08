package homeostaticseasons.event;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import homeostaticseasons.api.HomeostaticSeasonsAPI;
import homeostaticseasons.api.Season;
import homeostaticseasons.common.block.BirchFoliageTinter;
import homeostaticseasons.common.block.LeafLitterTinter;

public class ClientEventHandler {

    static Season lastSeason = null;
    static int lastSeasonDay = -1;

    public static void onClientTick(Minecraft minecraft) {
        Player player = minecraft.player;

        // Check every second
        if (player != null && player.tickCount % 20 == 0) {
            Season currentSeason = HomeostaticSeasonsAPI.getCurrentSeason(player.level());

            if (currentSeason != null) {
                if (lastSeason == null) {
                    lastSeason = currentSeason;
                    lastSeasonDay = getSeasonDay(minecraft, currentSeason);
                }
                else if (lastSeason != currentSeason) {
                    lastSeason = currentSeason;
                    lastSeasonDay = 1;
                    updateRenderer(minecraft);
                }
                else {
                    int seasonDay = getSeasonDay(minecraft, currentSeason);

                    if (seasonDay > lastSeasonDay) {
                        lastSeasonDay = seasonDay;
                        updateRenderer(minecraft);
                    }
                }
            }
        }
    }

    private static void updateRenderer(Minecraft minecraft) {
        BirchFoliageTinter.clearCache();
        LeafLitterTinter.clearCache();
        minecraft.levelRenderer.allChanged();
    }

    private static int getSeasonDay(Minecraft minecraft, Season season) {
        long seasonLength = season.getSeasonLength();
        Level level = minecraft.level;

        if (level == null) {
            return -1;
        }

        long timeUntilNextSeason = HomeostaticSeasonsAPI.getTimeUntilNextSeason(level);

        return (int) ((seasonLength - timeUntilNextSeason) / 24000L) + 1;
    }

}
