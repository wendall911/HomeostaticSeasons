package homeostaticseasons.common.biome;

import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.resources.Identifier;

import static homeostaticseasons.HomeostaticSeasons.prefix;

public class FabricBiomeColormapManager extends BiomeColormapManager implements IdentifiableResourceReloadListener {

    @Override
    public Identifier getFabricId() {
        return prefix("reload_biome_colormaps");
    }

}
