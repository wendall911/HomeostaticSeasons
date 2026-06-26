package homeostaticseasons.common.biome.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.Holder;
import net.minecraft.world.level.ColorResolver;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;

import homeostaticseasons.api.HomeostaticSeasonsAPI;
import homeostaticseasons.api.Season;
import homeostaticseasons.common.biome.BiomeColormap;
import homeostaticseasons.common.biome.BiomeColormapManager;
import homeostaticseasons.config.ConfigHandler;
import homeostaticseasons.util.ColorHelper;
import homeostaticseasons.util.RegistryHelper;

public class ColormapResolvers {

    private static ColorResolver builtinGrassColorResolver;
    private static ColorResolver builtinFoliageColorResolver;

    public static void init() {
        // Cache the built-in color resolvers
        builtinGrassColorResolver = BiomeColors.GRASS_COLOR_RESOLVER;
        builtinFoliageColorResolver = BiomeColors.FOLIAGE_COLOR_RESOLVER;

        // Override with custom resolvers
        BiomeColors.GRASS_COLOR_RESOLVER = (biome, temperature, humidity) ->
            getColor(ColormapType.GRASS, biome, temperature, humidity);
        BiomeColors.FOLIAGE_COLOR_RESOLVER = (biome, temperature, humidity) ->
            getColor(ColormapType.FOLIAGE, biome, temperature, humidity);
    }

    private static int getColor(ColormapType type, Biome biome, double temperature, double humidity) {
        int originalColor = switch (type) {
            case GRASS -> builtinGrassColorResolver.getColor(biome, temperature, humidity);
            case FOLIAGE -> builtinFoliageColorResolver.getColor(biome, temperature, humidity);
        };
        Minecraft mc = Minecraft.getInstance();
        Level level = mc.level;

        if (level != null && ConfigHandler.Common.isValidDimension(level.dimension())) {
            Holder<Biome> biomeHolder = RegistryHelper.getBiomeHolder(biome, level);
            Season currentSeason = HomeostaticSeasonsAPI.getCurrentSeason(level);

            if (biomeHolder != null && currentSeason != null) {
                Season nextSeason = HomeostaticSeasonsAPI.getNextSeason(level, currentSeason);
                BiomeColormap nextSeasonBiomeColormap = null;

                if (type == ColormapType.GRASS && ConfigHandler.Client.isBlacklistGrassColorBiome(biomeHolder)) {
                    return originalColor;
                }
                else if (type == ColormapType.FOLIAGE && ConfigHandler.Client.isBlacklistFoliageColorBiome(biomeHolder)) {
                    return originalColor;
                }

                BiomeColormap biomeColormap = BiomeColormapManager.getColormap(
                    biomeHolder,
                    currentSeason
                );

                if (nextSeason != null) {
                    nextSeasonBiomeColormap = BiomeColormapManager.getColormap(
                        biomeHolder,
                        nextSeason
                    );
                }

                if (biomeColormap != null && nextSeasonBiomeColormap != null) {
                    long seasonLength = currentSeason.getSeasonLength();
                    long timeUntilNextSeason = HomeostaticSeasonsAPI.getTimeUntilNextSeason(level);
                    if (type == ColormapType.GRASS) {
                        return ColorHelper.mix(
                            biomeColormap.getGrassColor(originalColor, biomeHolder),
                            nextSeasonBiomeColormap.getGrassColor(originalColor, biomeHolder),
                            timeUntilNextSeason / (float) seasonLength
                        );
                    }
                    else {
                        return ColorHelper.mix(
                            biomeColormap.getFoliageColor(originalColor, biomeHolder),
                            nextSeasonBiomeColormap.getFoliageColor(originalColor, biomeHolder),
                            timeUntilNextSeason / (float) seasonLength
                        );
                    }
                }
            }
        }

        return originalColor;
    }

    private enum ColormapType {
        GRASS,
        FOLIAGE
    }

}
