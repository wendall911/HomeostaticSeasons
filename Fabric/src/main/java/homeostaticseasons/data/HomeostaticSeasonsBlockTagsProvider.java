package homeostaticseasons.data;

import java.util.concurrent.CompletableFuture;

import org.jspecify.annotations.NonNull;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;

import homeostaticseasons.common.TagManager;
import homeostaticseasons.util.RegistryHelper;

public class HomeostaticSeasonsBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {

    public HomeostaticSeasonsBlockTagsProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider provider) {
        getOrCreateRawBuilder(TagManager.Blocks.REPLACEABLE_BY_SNOW)
            .addOptionalTag(BlockTags.SAPLINGS.location())
            .addOptionalTag(BlockTags.FLOWERS.location())
            .addOptionalTag(BlockTags.SMALL_FLOWERS.location())
            .addElement(RegistryHelper.getBlockId(Blocks.SHORT_GRASS))
            .addElement(RegistryHelper.getBlockId(Blocks.TALL_GRASS))
            .addElement(RegistryHelper.getBlockId(Blocks.FERN))
            .addElement(RegistryHelper.getBlockId(Blocks.LARGE_FERN))
            .addElement(RegistryHelper.getBlockId(Blocks.DEAD_BUSH))
            .addElement(RegistryHelper.getBlockId(Blocks.GLOW_LICHEN))
            .addOptionalTag(TagManager.Blocks.FLOWERS.location())
            .addOptionalTag(TagManager.Blocks.FS_REPLACEABLE_BY_SNOW.location());
    }

}
