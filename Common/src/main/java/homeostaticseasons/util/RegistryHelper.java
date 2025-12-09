package homeostaticseasons.util;

import java.util.Optional;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;

import technology.roughness.whitenoise.util.ResourceLocationHelper;

public class RegistryHelper extends ResourceLocationHelper {

    public static Holder<Biome> getBiomeHolder(Biome biome, Level level) {
        Registry<Biome> biomeRegistry;

        if (level instanceof ServerLevel) {
            biomeRegistry = level.registryAccess().lookupOrThrow(Registries.BIOME);
        }
        else {
            ClientPacketListener connection = Minecraft.getInstance().getConnection();

            if (connection != null) {
                biomeRegistry = connection.registryAccess().lookupOrThrow(Registries.BIOME);
            }
            else {
                return null;
            }
        }

        Optional<ResourceKey<Biome>> optionalKey = biomeRegistry.getResourceKey(biome);
        if (optionalKey.isEmpty()) {
            throw new NullPointerException("Biome " + biome + " is not registered in the biome registry!");
        }

        return biomeRegistry.wrapAsHolder(biomeRegistry.getValueOrThrow(optionalKey.get()));
    }

}
