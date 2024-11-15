package tfar.materialconfigps1.mixin;

import tfar.materialconfigps1.MaterialConfigPS1;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraft {
    
    @Inject(at = @At("TAIL"), method = "<init>")
    private void init(CallbackInfo info) {
        
        MaterialConfigPS1.LOG.info("This line is printed by an example mod common mixin!");
        MaterialConfigPS1.LOG.info("MC Version: {}", Minecraft.getInstance().getVersionType());
    }
}