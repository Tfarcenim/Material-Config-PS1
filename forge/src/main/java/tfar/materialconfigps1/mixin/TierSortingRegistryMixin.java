package tfar.materialconfigps1.mixin;

import com.google.common.collect.BiMap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraftforge.common.TierSortingRegistry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TierSortingRegistry.class)
public class TierSortingRegistryMixin {
    @Shadow @Final private static BiMap<ResourceLocation, Tier> tiers;

    @Inject(method = "<clinit>",at = @At("RETURN"))
    private static void onInit(CallbackInfo ci) {
        tiers.remove(new ResourceLocation("gold"));
    }
}
