package homeostaticseasons.event;

import net.minecraft.world.level.block.Blocks;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

import homeostaticseasons.common.block.BirchFoliageTinter;

@EventBusSubscriber(value = Dist.CLIENT)
public class ColorEventListener {

    @SubscribeEvent
    public static void onRegisterColorHandlers(RegisterColorHandlersEvent.Block event) {
        event.register(BirchFoliageTinter::getBirchTintedColor, Blocks.BIRCH_LEAVES);
    }


}
