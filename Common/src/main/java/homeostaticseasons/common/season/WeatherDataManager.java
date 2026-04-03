package homeostaticseasons.common.season;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.jetbrains.annotations.NotNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.profiling.ProfilerFiller;

import homeostaticseasons.HomeostaticSeasons;
import homeostaticseasons.api.Season;

public class WeatherDataManager extends SimpleJsonResourceReloadListener<JsonElement> {

    private static final Map<Season, WeatherFrequencyData> WEATHER_DATA = new HashMap<>();

    private static final Gson GSON = new GsonBuilder().registerTypeAdapter(WeatherFrequencyData.class, new WeatherFrequencyData.Serializer()).create();

    public WeatherDataManager() {
        super(ExtraCodecs.JSON, FileToIdConverter.json("seasons/weather"));
    }

    public static WeatherFrequencyData getWeatherData(Season season) {
        return WEATHER_DATA.getOrDefault(season, null);
    }

    public static JsonElement parseWeatherData(WeatherFrequencyData weatherData) {
        return GSON.toJsonTree(weatherData);
    }

    @Override
    protected void apply(Map<Identifier, JsonElement> pObject, @NotNull ResourceManager resourceManager, @NotNull ProfilerFiller profilerFiller) {
        WEATHER_DATA.clear();

        for (Map.Entry<Identifier, JsonElement> entry : pObject.entrySet()) {
            try {
                WeatherFrequencyData weatherData = GSON.fromJson(entry.getValue(), WeatherFrequencyData.class);

                WEATHER_DATA.put(Season.valueOf(entry.getKey().getPath().toUpperCase(Locale.ROOT)), weatherData);
            }
            catch (Exception e) {
                HomeostaticSeasons.LOGGER.error("Failed to load weather data: {}", entry.getKey(), e);
            }
        }

        HomeostaticSeasons.LOGGER.info("Loaded {} weather data entries", WEATHER_DATA.size());
        HomeostaticSeasons.LOGGER.info("Loaded {} weather data", WEATHER_DATA);
    }

}
