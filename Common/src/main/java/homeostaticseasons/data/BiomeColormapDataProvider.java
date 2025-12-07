package homeostaticseasons.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.NotNull;

import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;

import homeostaticseasons.HomeostaticSeasons;
import homeostaticseasons.common.biome.BiomeColormap;
import homeostaticseasons.common.biome.BiomeColormapManager;

public class BiomeColormapDataProvider implements DataProvider {

    private final Map<ResourceLocation, BiomeColormap> BIOME_TYPES_MAP = new HashMap<>();
    private final PackOutput packOutput;

    public BiomeColormapDataProvider(final PackOutput packOutput) {
        this.packOutput = packOutput;
    }

    protected void registerBiomeColormapData() {
        /*
         * Colormaps for NORMAL biomes
         */
        add(HomeostaticSeasons.prefix("early_spring_normal"), 0x6F937F, 0.75F, 0x6F817F, 0.75F, 0x869A68);
        add(HomeostaticSeasons.prefix("mid_spring_normal"), 0x4F9AAF, 0.9F, 0x4F85AF, 0.9F, 0x6EB282);
        add(HomeostaticSeasons.prefix("late_spring_normal"), 0x5F9A9F, 0x5F859F, 0x74AE74);
        add(HomeostaticSeasons.prefix("early_summer_normal"), 0x6F968F, 0x6F828F, 0x7AAA63);
        add(HomeostaticSeasons.prefix("mid_summer_normal"), 0xFFFFFF, 0xFFFFFF, 0x80A756);
        add(HomeostaticSeasons.prefix("late_summer_normal"), 0x9F714F, 0x9F5F4F, 0x98A549);
        add(HomeostaticSeasons.prefix("early_autumn_normal"), 0xC45441, 0xC44041, 0xB1A441);
        add(HomeostaticSeasons.prefix("mid_autumn_normal"), 0xEF3522, 0xEF2122, 0xE2A232);
        add(HomeostaticSeasons.prefix("late_autumn_normal"), 0xF52E1B, 0.85F, 0xF51A1B, 0.85F, 0xE8A42B);
        add(HomeostaticSeasons.prefix("early_winter_normal"), 0xFA2815, 0.6F, 0XFA1415, 0.6F, 0xF0A624);
        add(HomeostaticSeasons.prefix("mid_winter_normal"), 0xF91E0B, 0.45F, 0XF90A0B, 0.45F, 0xF5A417);
        add(HomeostaticSeasons.prefix("late_winter_normal"), 0xFE1603, 0.6F, 0XFE0203, 0.6F, 0XFDA203);

        /*
         * Colormaps for TEMPERATE biomes
         */
        add(HomeostaticSeasons.prefix("early_spring_temperate"), 0xFFFFFF, 0xFFFFFF, 0x80A76);
        add(HomeostaticSeasons.prefix("mid_spring_temperate"), 0xFFFFFF, 0xFFFFFF, 0x80A76);
        add(HomeostaticSeasons.prefix("late_spring_temperate"), 0xA58667, 0.8F, 0xB7868C, 0.95F, 0x98A547);
        add(HomeostaticSeasons.prefix("early_summer_temperate"), 0xA58669, 0.8F, 0xB7868C, 0.95F, 0x98A55B);
        add(HomeostaticSeasons.prefix("mid_summer_temperate"), 0x8E7B7D, 0.9F, 0xA08B87, 0.975F, 0x80A754);
        add(HomeostaticSeasons.prefix("late_summer_temperate"), 0x8E7B7D, 0.9F, 0xA08B87, 0.975F, 0x80A754);
        add(HomeostaticSeasons.prefix("early_autumn_temperate"), 0x758C7A, 0x728C91, 0x80A755);
        add(HomeostaticSeasons.prefix("mid_autumn_temperate"), 0x758C7A, 0x728C81, 0x80A745);
        add(HomeostaticSeasons.prefix("late_autumn_temperate"), 0x548374, 0x2497AE,  0x76AC5C);
        add(HomeostaticSeasons.prefix("early_winter_temperate"), 0x548374, 0x2488AE,  0x76AC5C);
        add(HomeostaticSeasons.prefix("mid_winter_temperate"), 0x658979, 0x4E8883, 0x80A745);
        add(HomeostaticSeasons.prefix("late_winter_temperate"), 0x658979, 0x4E8883, 0x80A745);
    }

    protected void add(ResourceLocation id, int grassColor, float grassSaturation, int foliageColor, float foliageSaturation, int birchColor) {
        BiomeColormap biomeColormap = new BiomeColormap(id, grassColor, grassSaturation, foliageColor, foliageSaturation, birchColor);

        BIOME_TYPES_MAP.put(id, biomeColormap);
    }

    protected void add(ResourceLocation id, int grassColor, int foliageColor, int birchColor) {
        BiomeColormap biomeColormap = new BiomeColormap(id, grassColor, foliageColor, birchColor);

        BIOME_TYPES_MAP.put(id, biomeColormap);
    }

    @Override
    @NotNull
    public CompletableFuture<?> run(@NotNull CachedOutput cache) {
        List<CompletableFuture<?>> futures = new ArrayList<>();

        registerBiomeColormapData();

        for (Map.Entry<ResourceLocation, BiomeColormap> entry : BIOME_TYPES_MAP.entrySet()) {
            PackOutput.PathProvider pathProvider = getPath();

            futures.add(DataProvider.saveStable(cache,
                BiomeColormapManager.parseBiomeColormapData(entry.getValue()),
                pathProvider.json(entry.getKey())));
        }

        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    @Override
    public @NotNull String getName() {
        return HomeostaticSeasons.MOD_NAME + " - Biome Colormap Data";
    }

    private PackOutput.PathProvider getPath() {
        return this.packOutput.createPathProvider(PackOutput.Target.DATA_PACK, "biome/colormaps/");
    }

}
