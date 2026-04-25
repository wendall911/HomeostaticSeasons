package homeostaticseasons.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import homeostaticseasons.event.ClientEventHandler;

@Mixin(Player.class)
public abstract class PlayerMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    public void hs$tick(CallbackInfo ci) {
        Player player = (Player)(Object)this;

        if (player.isLocalPlayer()) {
            ClientEventHandler.onClientTick(Minecraft.getInstance());
        }
    }

}
