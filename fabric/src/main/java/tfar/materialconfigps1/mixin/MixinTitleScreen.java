package tfar.materialconfigps1.mixin;

import tfar.materialconfigps1.MaterialConfigPS1;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class MixinTitleScreen {
    
    @Inject(at = @At("HEAD"), method = "init()V")
    private void init(CallbackInfo info) {
        
        MaterialConfigPS1.LOG.info("This line is printed by an example mod mixin from Fabric!");
        MaterialConfigPS1.LOG.info("MC Version: {}", Minecraft.getInstance().getVersionType());
    }
}