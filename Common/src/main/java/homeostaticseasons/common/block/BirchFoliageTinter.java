package homeostaticseasons.common.block;

import java.util.HashMap;
import java.util.Map;

import org.jspecify.annotations.NonNull;

import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;

import homeostaticseasons.api.HomeostaticSeasonsAPI;
import homeostaticseasons.api.Season;
import homeostaticseasons.common.biome.BiomeColormap;
import homeostaticseasons.common.biome.BiomeColormapManager;
import homeostaticseasons.config.ConfigHandler;
import homeostaticseasons.util.ColorHelper;

public class BirchFoliageTinter {

    private static final Map<BlockState, Integer> cache = new HashMap<>();

    public static BlockTintSource getBirchTintSource() {
        return new BlockTintSource() {

            @Override
            public int color(@NonNull BlockState blockState) {
                return getBirchTintedColor(blockState);
            }

            @Override
            public int colorInWorld(@NonNull BlockState state, @NonNull BlockAndTintGetter level, @NonNull BlockPos pos) {
                return getBirchTintedColor(state);
            }

        };
    }

    public static int getBirchTintedColor(BlockState blockState) {
        if (cache.containsKey(blockState)) {
            return cache.get(blockState);
        }

        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        int originalColor = FoliageColor.FOLIAGE_BIRCH;

        if (player != null) {
            Level level = player.level();
            Holder<Biome> biomeHolder = level.getBiome(player.blockPosition());
            Season currentSeason = HomeostaticSeasonsAPI.getCurrentSeason(level);
            Season nextSeason = null;

            if (ConfigHandler.Client.isBlacklistBirchColorBiome(biomeHolder)) {
                return originalColor;
            }

            if (currentSeason != null) {
                nextSeason = HomeostaticSeasonsAPI.getNextSeason(level, currentSeason);
            }

            if (currentSeason != null && nextSeason != null) {
                BiomeColormap biomeColormap = BiomeColormapManager.getColormap(
                    biomeHolder,
                    currentSeason
                );
                BiomeColormap nextSeasonBiomeColormap = BiomeColormapManager.getColormap(
                    biomeHolder,
                    nextSeason
                );

                if (biomeColormap != null && nextSeasonBiomeColormap != null) {
                    long seasonLength = currentSeason.getSeasonLength();
                    long timeUntilNextSeason = HomeostaticSeasonsAPI.getTimeUntilNextSeason(level);
                    int newColor = ColorHelper.mix(
                        biomeColormap.getBirchColor(originalColor, biomeHolder),
                        nextSeasonBiomeColormap.getBirchColor(originalColor, biomeHolder),
                        timeUntilNextSeason / (float) seasonLength
                    );

                    cache.put(blockState, newColor);

                    return newColor;
                }
            }
        }

        return originalColor;
    }

    public static void clearCache() {
        cache.clear();
    }

}
