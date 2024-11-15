package tfar.materialconfigps1;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MaterialConfigPS1.MOD_ID)
public class MaterialConfigPS1Forge {
    
    public MaterialConfigPS1Forge() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, tfar.materialconfigps1.ModConfig.SERVER_SPEC);
        // This method is invoked by the Forge mod loader when it is ready
        // to load your mod. You can access Forge and Common code in this
        // project.
        bus.addListener(this::configure);
        // Use Forge to bootstrap the Common mod.
        MaterialConfigPS1.init();

    }

    void configure(FMLCommonSetupEvent event) {
        MaterialConfigPS1.configureServer();
    }

}