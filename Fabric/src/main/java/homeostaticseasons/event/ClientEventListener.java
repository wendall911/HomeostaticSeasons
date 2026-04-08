package homeostaticseasons.event;

import java.util.List;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;

import net.minecraft.world.level.block.Blocks;

import homeostaticseasons.common.block.BirchFoliageTinter;
import homeostaticseasons.common.block.LeafLitterTinter;

public class ClientEventListener {

    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(ClientEventHandler::onClientTick);
        BlockColorRegistry.register(List.of(BirchFoliageTinter.getBirchTintSource()), Blocks.BIRCH_LEAVES);
        BlockColorRegistry.register(List.of(LeafLitterTinter.getLeafLitterTintSource()), Blocks.LEAF_LITTER);

    }

}
