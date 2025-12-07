# HomeostaticSeasons [![Project](http://cf.way2muchnoise.eu/full_1122223_downloads.svg)](https://www.curseforge.com/minecraft/mc-mods/homeostatic-seasons) [![Project](https://modrinth.roughness.technology/full_homeostatic-seasons_downloads.svg)](https://modrinth.com/mod/homeostatic-seasons)
[![](https://modrinth.roughness.technology/versions/homeostatic-seasons.svg)](https://modrinth.com/mod/homeostatic-seasons/versions)
[![](https://img.shields.io/badge/NeoForge-20+-orange.svg?longCache=true&style=flat)](https://www.curseforge.com/minecraft/mc-mods/homeostatic-seasons/files?gameVersionTypeId=6)
[![](https://img.shields.io/badge/Fabric-0.46.0+-yellowgreen.svg?longCache=true&style=flat)](https://www.curseforge.com/minecraft/mc-mods/homeostatic-seasons/files?gameVersionTypeId=4)
![MIT](https://img.shields.io/badge/license-MIT-blue.svg?longCache=true&style=flat)

## Description

Homeostatic Seasons adds the four traditional seasons to Minecraft, each
comprised of three distinct sub-seasons, for a total of twelve sub-seasons.
Each sub-season has its own visual, weather and temperature changes.

The duration of each sub-season is three in-game days (configurable), resulting
in 9-day seasons. The current season is defined by the
[Gametime](https://minecraft.wiki/w/Commands/time); using /season set
EARLY_SPRING will reset to day 1 of early spring and set the Gametime to 0.
The /season command can be used to check the current sub-season, skip to a
specific sub-season or set a specific sub-season.

There is a robust configuration to customize the mod to your liking.

[Homeostatic](https://github.com/wendall911/Homeostatic) is not a required
dependency unless players want to experience temperature and thirst in-game.
But it is highly recommended to use
[Homeostatic](https://github.com/wendall911/Homeostatic) alongside Homeostatic
Seasons for the full experience.

## What is different about this mod?

Homeostatic Seasons is focused on performance and compatibility from the start.
The design should allow for easy compatibility with other mods and should not
cause significant performance issues. Homeostatic Seasons does not add any new
blocks or items, and only modifies existing game mechanics.

Additionally, the mod is highly configurable, allowing players to tailor the
experience to their preferences. Players can adjust the length of seasons, the
intensity of weather effects, and other aspects of the mod.

Snow and ice accumulation are handled so as not to cause significant
performance issues, even in large worlds. The mod uses a chunk-based system to
track snow accumulation, updating only loaded, visible chunks to the player.
Plants and other blocks that are affected by snow accumulation are replaced
with snow, then will revert to their original state when the snow melts.

Temperature is calculated on a per-block basis, accounting for surrounding
biomes, altitude, and the current season. This allows for a more realistic
temperature system that is affected by the environment. Biomes can experience a
mixture of snow and rain simultaneously, depending on the temperature of the
blocks in the area.

## Seasonal Biomes

Biomes will have different colors and other visual features according to the
current season. This is a purely visual change and will not affect your game
performance. Additionally, shaders are fully supported.

Non-vanilla biomes from the major biome mods are fully supported. If the custom
biome cannot be determined, it will behave as a neutral biome, with seasonal
changes. If any biome is missing, please create an issue on GitHub so the biome
can be fully supported.

### Seasonal Weather

The weather will change according to the current season. In spring, rain is
more frequent, while in summer, thunderstorms are more common. In autumn, rain
becomes more frequent again, while in winter, snow is the predominant form of
precipitation in cold areas. The intensity and duration of weather events can
be configured to suit player preferences. Temperate biomes, such as
rainforests, deserts and swamps, will experience a rainy season from mid-autumn
to late winter. So, yes, you could experience snow and rain in a desert biome
during winter!

## Links of Interest

+ [HomeostaticSeasons Wiki](https://github.com/wendall911/HomeostaticSeasons/wiki)
+ [HomeostaticSeasons Curseforge Page](https://www.curseforge.com/minecraft/mc-mods/homeostatic-seasons)
+ [HomeostaticSeasons Modrinth Page](https://modrinth.com/mod/homeostatic-seasons)
+ [Homeostatic Curseforge Page](https://www.curseforge.com/minecraft/mc-mods/homeostatic)
+ [Homeostatic Modrinth Page](https://modrinth.com/mod/homeostatic)
