package homeostaticseasons.common.block;

import java.util.List;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import it.unimi.dsi.fastutil.longs.Long2ObjectLinkedOpenHashMap;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;

public class ReplacedMeltablesSavedData extends SavedData {

    public static final Codec<ReplacedMeltablesSavedData> CODEC = RecordCodecBuilder.create(
        instance -> instance.group(
            BlockStateWithPosition.CODEC
                .listOf()
                .optionalFieldOf("records", List.of())
                .forGetter(data ->
                    data.replacedMeltablesMap.long2ObjectEntrySet().stream().map(BlockStateWithPosition::from).toList())
        ).apply(instance, ReplacedMeltablesSavedData::new)
    );
    public static final SavedDataType<ReplacedMeltablesSavedData> TYPE = new SavedDataType<>("homeostaticseasons_replaced_meltables", ReplacedMeltablesSavedData::new, CODEC, null);

    private final Long2ObjectLinkedOpenHashMap<BlockState> replacedMeltablesMap = new Long2ObjectLinkedOpenHashMap<>();

    public ReplacedMeltablesSavedData() {
        this.setDirty();
    }

    private ReplacedMeltablesSavedData(List<BlockStateWithPosition> records) {
        for (BlockStateWithPosition record : records) {
            replacedMeltablesMap.put(record.blockPos(), record.blockState());
        }
    }

    public BlockState getReplaced(BlockPos blockPos) {
        return replacedMeltablesMap.get(blockPos.asLong());
    }

    public void addReplaced(BlockPos pos, BlockState replacedState) {
        replacedMeltablesMap.put(pos.asLong(), replacedState);

        setDirty();
    }

    public void removeReplaced(BlockPos pos) {
        if (replacedMeltablesMap.remove(pos.asLong()) != null) {
            setDirty();
        }
    }

    record BlockStateWithPosition(long blockPos, BlockState blockState) {

        public static final Codec<BlockStateWithPosition> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                Codec.LONG.fieldOf("blockPos").forGetter(BlockStateWithPosition::blockPos),
                BlockState.CODEC.fieldOf("blockState").forGetter(BlockStateWithPosition::blockState)
            ).apply(instance, BlockStateWithPosition::new)
        );

        public static BlockStateWithPosition from(Long2ObjectLinkedOpenHashMap.Entry<BlockState> entry) {
            return new BlockStateWithPosition(entry.getLongKey(), entry.getValue());
        }

    }

}
