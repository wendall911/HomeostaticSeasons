package homeostaticseasons.event;

import java.util.List;

import net.minecraft.world.level.block.Blocks;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

import homeostaticseasons.common.block.BirchFoliageTinter;
import homeostaticseasons.common.block.LeafLitterTinter;

@EventBusSubscriber(value = Dist.CLIENT)
public class ColorEventListener {

    @SubscribeEvent
    public static void onRegisterColorHandlers(RegisterColorHandlersEvent.BlockTintSources event) {
        event.register(List.of(BirchFoliageTinter.getBirchTintSource()), Blocks.BIRCH_LEAVES);
        event.register(List.of(LeafLitterTinter.getLeafLitterTintSource()), Blocks.LEAF_LITTER);
    }

}
