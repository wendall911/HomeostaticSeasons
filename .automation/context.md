# HomeostaticSeasons — Project Context

## What This Is
A seasons mod adding four traditional seasons each divided into three sub-seasons (twelve total). Each sub-season has distinct visual, weather, and temperature changes.

Sub-season duration is configurable (default: 3 in-game days, 9-day seasons).  Integrates with Homeostatic for temperature effects but does not require it. ClimateSettings is the shared dependency.

License: MIT

## Project Structure
Multi-loader: `Common/` + `NeoForge/` + `Fabric/`

## Branch Convention
| Branch | Modloaders        |
|--------|-------------------|
| 1.21.1 | NeoForge + Fabric |
| 26.1   | NeoForge + Fabric |

Maintained: 1.21.1, 26.1

## Dependencies
- ClimateSettings (jarJar/include — currently an embedded sub-project of Homeostatic; planned to become a standalone library)
- WhiteNoise (jarJar/include)
- Homeostatic (optional runtime integration)

## Design Origin
Not a fork of any existing mod. Inspired by the design of Fabric Seasons — the author gave explicit permission for a NeoForge/Fabric version, but that fork was never built. HomeostaticSeasons was eventually written from scratch when time permitted.

Key technical differentiator from all other climate and season mods: ClimateSettings provides **per-meter temperature resolution** rather than per-biome resolution. This is a fundamental architectural difference that enables significantly more realistic temperature modelling.

## Ecosystem Context
HomeostaticSeasons is the second tier of a growing mod ecosystem:
- ClimateSettings — temperature registry API (currently inside Homeostatic repo)
- Homeostatic — temperature + thirst mod
- HomeostaticSeasons — seasons expansion (this mod)
- [Planned] Crop growth mod — limits plant growth by season; third-tier expansion

ClimateSettings migration to a standalone library is a prerequisite for scaling this ecosystem cleanly. See Homeostatic's `project-context.md` for migration context.

## Planned API Changes
Temperature variance improvements are planned for 26.1.2+. These are API-breaking but will not be backported — 26.1.2+ only, no concern for the existing user base on earlier builds.

## Distribution
Side: both (clientRequired = true, serverRequired = true)

## Release Process
Follow the standard wendall911 release process in `../docs/minecraft/MINECRAFT_DEVELOPMENT_NOTES.md`.
