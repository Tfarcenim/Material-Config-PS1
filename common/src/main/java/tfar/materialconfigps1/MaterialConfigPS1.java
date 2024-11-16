package tfar.materialconfigps1;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

// This class is part of the common project meaning it is shared between all supported loaders. Code written here can only
// import and access the vanilla codebase, libraries used by vanilla, and optionally third party libraries that provide
// common compatible binaries. This means common code can not directly use loader specific concepts such as Forge events
// however it will be compatible with all supported mod loaders.
public class MaterialConfigPS1 {

    public static final String MOD_ID = "materialconfigps1";
    public static final String MOD_NAME = "Material Config PS1";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    // The loader specific projects are able to import and use any code from the common project. This allows you to
    // write the majority of your code here and load it from your loader specific projects. This example has some
    // code that gets invoked by the entry point of the loader specific projects.
    public static void init() {

        // It is common for all supported loaders to provide a similar feature that can not be used directly in the
        // common code. A popular way to get around this is using Java's built-in service loader feature to create
        // your own abstraction layer. You can learn more about this in our provided services class. In this example
        // we have an interface in the common code and use a loader specific implementation to delegate our call to
        // the platform specific approach.
    }

    static void configure() {
        configureMaterial(ArmorMaterials.GOLD,ModConfig.Server.GOLD_ARMOR_DURABILITY_MULTIPLIER.get(),
                (ArmorItem) Items.GOLDEN_BOOTS, (ArmorItem) Items.GOLDEN_LEGGINGS, (ArmorItem) Items.GOLDEN_CHESTPLATE, (ArmorItem) Items.GOLDEN_HELMET,
                ModConfig.Server.GOLD_BOOTS_DEFENSE.get(),ModConfig.Server.GOLD_LEGGINGS_DEFENSE.get(),
                ModConfig.Server.GOLD_CHESTPLATE_DEFENSE.get(), ModConfig.Server.GOLD_HELMET_DEFENSE.get());

        configureMaterial(ArmorMaterials.IRON,ModConfig.Server.IRON_ARMOR_DURABILITY_MULTIPLIER.get(),
                (ArmorItem) Items.IRON_BOOTS, (ArmorItem) Items.IRON_LEGGINGS, (ArmorItem) Items.IRON_CHESTPLATE, (ArmorItem) Items.IRON_HELMET,
                ModConfig.Server.IRON_BOOTS_DEFENSE.get(),ModConfig.Server.IRON_LEGGINGS_DEFENSE.get(),
                ModConfig.Server.IRON_CHESTPLATE_DEFENSE.get(), ModConfig.Server.IRON_HELMET_DEFENSE.get());

        configureTier(Tiers.IRON,ModConfig.Server.IRON_TIER_DURABILITY.get(),(float) (double)ModConfig.Server.IRON_TIER_SPEED.get(),(AxeItem) Items.IRON_AXE,(HoeItem) Items.IRON_HOE,(PickaxeItem) Items.IRON_PICKAXE,(ShovelItem) Items.IRON_SHOVEL,(SwordItem) Items.IRON_SWORD);
        configureTier(Tiers.GOLD,ModConfig.Server.GOLD_TIER_DURABILITY.get(),(float) (double)ModConfig.Server.GOLD_TIER_SPEED.get(),(AxeItem) Items.GOLDEN_AXE,(HoeItem) Items.GOLDEN_HOE,(PickaxeItem) Items.GOLDEN_PICKAXE,(ShovelItem) Items.GOLDEN_SHOVEL,(SwordItem) Items.GOLDEN_SWORD);

        Tiers.GOLD.level = 3;
        Tiers.DIAMOND.level = 4;
        Tiers.NETHERITE.level = 5;
    }

    static void configureMaterial(ArmorMaterials armorMaterials, int durabilityMultiplier,
                                  ArmorItem boots,ArmorItem leggings,ArmorItem chestplate,ArmorItem helmet,
                                  int bootsDefense, int leggingsDefense, int chestplateDefense, int helmetDefense) {
        armorMaterials.durabilityMultiplier = durabilityMultiplier;

        armorMaterials.slotProtections[boots.getSlot().getIndex()] = bootsDefense;
        armorMaterials.slotProtections[leggings.getSlot().getIndex()] = leggingsDefense;
        armorMaterials.slotProtections[chestplate.getSlot().getIndex()] = chestplateDefense;
        armorMaterials.slotProtections[helmet.getSlot().getIndex()] = helmetDefense;

        patchArmor(boots,bootsDefense);
        patchArmor(leggings,leggingsDefense);
        patchArmor(chestplate,chestplateDefense);
        patchArmor(helmet,helmetDefense);
    }

    static void patchArmor(ArmorItem armorItem,int defense) {
        armorItem.maxDamage = armorItem.getMaterial().getDurabilityForSlot(armorItem.getSlot());
        armorItem.defense = defense;
        var map = armorItem.defaultModifiers;
        Multimap<Attribute, AttributeModifier> multimap = HashMultimap.create(map);
        multimap.get(Attributes.ARMOR).clear();
        UUID uuid = ArmorItem.ARMOR_MODIFIER_UUID_PER_SLOT[armorItem.getSlot().getIndex()];
        multimap.get(Attributes.ARMOR).add(new AttributeModifier(uuid, "Armor modifier",defense, AttributeModifier.Operation.ADDITION));
        armorItem.defaultModifiers = multimap;
    }

    static void configureTier(Tiers tiers,int durability,float speed, AxeItem axe,HoeItem hoe,PickaxeItem pickaxe,ShovelItem shovel,
                              SwordItem sword
                              ) {
        tiers.uses = axe.maxDamage = hoe.maxDamage = pickaxe.maxDamage = shovel.maxDamage = sword.maxDamage = durability;
        tiers.speed = axe.speed = hoe.speed = pickaxe.speed = shovel.speed = speed;
    }

}