package tfar.materialconfigps1;

import com.fullturtlearmor.categoricals.MyArmorMaterials;
import com.fullturtlearmor.lists.ArmorItems;
import net.enderitemc.enderitemod.init.Registration;
import net.enderitemc.enderitemod.materials.EnderiteMaterial;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraftforge.common.TierSortingRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import tfar.materialconfigps1.datagen.ModDatagen;
import tfar.materialconfigps1.mixin.MyArmorMaterialsAccess;

import java.util.List;

@Mod(MaterialConfigPS1.MOD_ID)
public class MaterialConfigPS1Forge {
    
    public MaterialConfigPS1Forge() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ModConfigs.SERVER_SPEC);
        // This method is invoked by the Forge mod loader when it is ready
        // to load your mod. You can access Forge and Common code in this
        // project.
        bus.addListener(this::configure);
        bus.addListener(ModDatagen::gather);
        // Use Forge to bootstrap the Common mod.
        MaterialConfigPS1.init();

    }

    void configure(FMLCommonSetupEvent event) {
        TierSortingRegistry.registerTier(Tiers.GOLD,new ResourceLocation(MaterialConfigPS1.MOD_ID,"gold"), List.of(Tiers.IRON),List.of(Tiers.DIAMOND));
        MaterialConfigPS1.configure();
        if (LoadedMods.fullturtlearmor.loaded) {
            ConfigureFullTurtleArmor.configure();
        }
        if (LoadedMods.enderitemod.loaded) {
            ConfigureEnderiteMod.configure();
        }
    }

    static class ConfigureFullTurtleArmor{

        static void configure() {
            configureMaterial(MyArmorMaterials.TURTLE, ModConfigs.Server.TURTLE_ARMOR_DURABILITY_MULTIPLIER.get(),
                    ArmorItems.turtle_boots,ArmorItems.turtle_leggings,ArmorItems.turtle_chestplate,(ArmorItem) Items.TURTLE_HELMET,
                    ModConfigs.Server.TURTLE_BOOTS_DEFENSE.get(),ModConfigs.Server.TURTLE_LEGGINGS_DEFENSE.get(),ModConfigs.Server.TURTLE_CHESTPLATE_DEFENSE.get(),ModConfigs.Server.TURTLE_HELMET_DEFENSE.get()
                    );
        }

        static void configureMaterial(MyArmorMaterials armorMaterials, int durabilityMultiplier,
                                      ArmorItem boots, ArmorItem leggings, ArmorItem chestplate, ArmorItem helmet,
                                      int bootsDefense, int leggingsDefense, int chestplateDefense, int helmetDefense) {

            MyArmorMaterialsAccess myArmorMaterialsAccess = (MyArmorMaterialsAccess)(Object) armorMaterials;

            myArmorMaterialsAccess.setDurabilityMultiplier(durabilityMultiplier);

            int[] slotProtections = myArmorMaterialsAccess.getSlotProtections();

            slotProtections[boots.getSlot().getIndex()] = bootsDefense;
            slotProtections[leggings.getSlot().getIndex()] = leggingsDefense;
            slotProtections[chestplate.getSlot().getIndex()] = chestplateDefense;
            slotProtections[helmet.getSlot().getIndex()] = helmetDefense;

            MaterialConfigPS1.patchArmor(boots,bootsDefense);
            MaterialConfigPS1.patchArmor(leggings,leggingsDefense);
            MaterialConfigPS1.patchArmor(chestplate,chestplateDefense);
            MaterialConfigPS1.patchArmor(helmet,helmetDefense);
        }

    }

    static class ConfigureEnderiteMod {

        static void configure() {
            configureTier(EnderiteMaterial.ENDERITE,(AxeItem) Registration.ENDERITE_AXE.get(),(HoeItem) Registration.ENDERITE_HOE.get(),
                    (PickaxeItem) Registration.ENDERITE_PICKAXE.get(),(ShovelItem) Registration.ENDERITE_SHOVEL.get(),(SwordItem)Registration.ENDERITE_SWORD.get());
        }

        static void configureTier(EnderiteMaterial tiers, AxeItem axe, HoeItem hoe, PickaxeItem pickaxe, ShovelItem shovel, SwordItem sword

        ) {
            MaterialConfigPS1.patchTool(axe,(float)(double)ModConfigs.Server.ENDERITE_AXE_DAMAGE.get());
            MaterialConfigPS1.patchTool(hoe,(float)(double)ModConfigs.Server.ENDERITE_HOE_DAMAGE.get());
            MaterialConfigPS1.patchTool(pickaxe,(float)(double)ModConfigs.Server.ENDERITE_PICKAXE_DAMAGE.get());
            MaterialConfigPS1.patchTool(shovel,(float)(double)ModConfigs.Server.ENDERITE_SHOVEL_DAMAGE.get());
            MaterialConfigPS1.patchSword(sword,(float)(double)ModConfigs.Server.ENDERITE_SWORD_DAMAGE.get());
        }

    }
}