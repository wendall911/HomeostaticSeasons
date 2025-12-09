package homeostaticseasons.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;

import net.minecraft.world.level.block.Blocks;

import homeostaticseasons.common.block.BirchFoliageTinter;

public class ClientEventListener {

    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(ClientEventHandler::onClientTick);
        ColorProviderRegistry.BLOCK.register(BirchFoliageTinter::getBirchTintedColor, Blocks.BIRCH_LEAVES);
    }

}
