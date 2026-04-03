package homeostaticseasons.common.season;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.WeatherData;

import homeostaticseasons.api.Season;
import homeostaticseasons.api.SeasonWeather;

public class Weather {

    public static void updateWeather(ServerLevel level, Season season) {
        if (!SeasonWeather.isValid(level)) {
            return;
        }

        WeatherFrequencyData weatherFrequencyData = WeatherDataManager.getWeatherData(season);
        WeatherData weatherData = level.getWeatherData();

        if (weatherFrequencyData != null) {
            if (weatherFrequencyData.canRain()) {
                if (!weatherData.isRaining() && weatherData.getRainTime() > weatherFrequencyData.maxRainTime()) {
                    weatherData.setRainTime(
                        level.getRandom().nextInt(weatherFrequencyData.maxRainTime() - weatherFrequencyData.minRainTime()) + weatherFrequencyData.minRainTime());
                }
            }
            else if (weatherData.isRaining()) {
                weatherData.setRaining(false);
            }

            if (weatherFrequencyData.canThunder()) {
                if (!weatherData.isThundering() && weatherData.getThunderTime() > weatherFrequencyData.maxThunderTime()) {
                    weatherData.setThunderTime(
                        level.getRandom().nextInt(weatherFrequencyData.maxThunderTime() - weatherFrequencyData.minThunderTime()) + weatherFrequencyData.minThunderTime());
                }
            }
            else if (weatherData.isThundering()) {
                weatherData.setThundering(false);
            }
        }
    }

}
