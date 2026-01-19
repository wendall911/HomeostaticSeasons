package homeostaticseasons;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

import homeostaticseasons.event.ClientEventListener;
import homeostaticseasons.network.SyncBiomeColormap;

public class HomeostaticSeasonsClientFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        HomeostaticSeasonsClient.init();
        ClientEventListener.init();

        ClientPlayNetworking.registerGlobalReceiver(SyncBiomeColormap.TYPE, (payload, ctx) -> {
            ctx.client().execute(() -> {
                payload.handle(ctx.player());

                var mc = ctx.client();
                if (mc.level != null) {
                    mc.level.clearTintCaches();
                }
                if (mc.levelRenderer != null) {
                    mc.levelRenderer.allChanged();
                }
            });
        });
    }

}
