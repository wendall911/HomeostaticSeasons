package homeostaticseasons.mixin;

import net.minecraft.client.renderer.WeatherEffectRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome.Precipitation;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import homeostaticseasons.api.SeasonWeather;

@Mixin(WeatherEffectRenderer.class)
public class WeatherEffectRendererMixin {

    @Inject(method = "getPrecipitationAt", at = @At("HEAD"), cancellable = true)
    private void homeostaticseasons$overrideGetPrecipitationAt(Level level, BlockPos pos, CallbackInfoReturnable<Precipitation> cir) {
        cir.setReturnValue(SeasonWeather.getPrecipitationType(
            pos,
            level
        ));
    }

}
