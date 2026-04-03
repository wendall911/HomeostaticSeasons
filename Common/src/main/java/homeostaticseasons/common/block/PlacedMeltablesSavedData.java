package homeostaticseasons.common.block;

import com.mojang.serialization.Codec;

import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.longs.LongSet;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;

import homeostaticseasons.HomeostaticSeasons;

public class PlacedMeltablesSavedData extends SavedData {

    private final LongSet all;
    private static final Codec<LongSet> LONG_SET = Codec.LONG_STREAM.xmap(LongOpenHashSet::toSet, LongSet::longStream);
    private static final Codec<PlacedMeltablesSavedData> CODEC = LONG_SET.xmap(PlacedMeltablesSavedData::new, data -> data.all);
    private static final Identifier ID = Identifier.fromNamespaceAndPath(HomeostaticSeasons.MODID, "placed_meltables");
    public static final SavedDataType<PlacedMeltablesSavedData> TYPE = new SavedDataType<>(ID, PlacedMeltablesSavedData::new, CODEC, null);

    private PlacedMeltablesSavedData(LongSet all) {
        this.all = all;
    }

    public PlacedMeltablesSavedData() {
        this(new LongOpenHashSet());
    }

    public boolean isManuallyPlaced(BlockPos pos) {
        return all.contains(pos.asLong());
    }

    public void addPosition(BlockPos pos) {
        all.add(pos.asLong());
        setDirty();
    }

    public void removePosition(BlockPos pos) {
        if (all.remove(pos.asLong())) {
            setDirty();
        }
    }

}
