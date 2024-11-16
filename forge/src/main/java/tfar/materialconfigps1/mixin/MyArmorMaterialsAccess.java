package tfar.materialconfigps1.mixin;

import com.fullturtlearmor.categoricals.MyArmorMaterials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = MyArmorMaterials.class,remap = false)
public interface MyArmorMaterialsAccess {
    @Accessor int getDurabilityMultiplier();
    @Accessor int[] getSlotProtections();

    @Accessor @Mutable void setDurabilityMultiplier(int value);
}
