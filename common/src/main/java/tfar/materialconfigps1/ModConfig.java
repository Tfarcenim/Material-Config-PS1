package tfar.materialconfigps1;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class ModConfig {

    static final Server SERVER;
    public static final ForgeConfigSpec SERVER_SPEC;

    static {
        final Pair<Server, ForgeConfigSpec> specPair2 = new ForgeConfigSpec.Builder().configure(Server::new);
        SERVER_SPEC = specPair2.getRight();
        SERVER = specPair2.getLeft();
    }

    static class Server{

        public static ForgeConfigSpec.IntValue IRON_ARMOR_DURABILITY_MULTIPLIER;//normally 15
        public static ForgeConfigSpec.IntValue IRON_HELMET_DEFENSE;//new int[]{2, 5, 6, 2}
        public static ForgeConfigSpec.IntValue IRON_CHESTPLATE_DEFENSE;
        public static ForgeConfigSpec.IntValue IRON_LEGGINGS_DEFENSE;
        public static ForgeConfigSpec.IntValue IRON_BOOTS_DEFENSE;

        public static ForgeConfigSpec.IntValue IRON_TIER_DURABILITY;//normally 250
        public static ForgeConfigSpec.DoubleValue IRON_TIER_SPEED;

        public static ForgeConfigSpec.IntValue GOLD_ARMOR_DURABILITY_MULTIPLIER;//normally 7
        public static ForgeConfigSpec.IntValue GOLD_HELMET_DEFENSE;//new int[]{1, 3, 5, 2}
        public static ForgeConfigSpec.IntValue GOLD_CHESTPLATE_DEFENSE;
        public static ForgeConfigSpec.IntValue GOLD_LEGGINGS_DEFENSE;
        public static ForgeConfigSpec.IntValue GOLD_BOOTS_DEFENSE;

        public static ForgeConfigSpec.IntValue GOLD_TIER_DURABILITY;//normally 33
        public static ForgeConfigSpec.DoubleValue GOLD_TIER_SPEED;



        Server(ForgeConfigSpec.Builder builder) {
            builder.push("armor");

            builder.push("iron");
            IRON_ARMOR_DURABILITY_MULTIPLIER = builder.defineInRange("durability_multiplier",12,1,10000);
            IRON_HELMET_DEFENSE = builder.defineInRange("helmet_defense",2,1,10000);
            IRON_CHESTPLATE_DEFENSE = builder.defineInRange("chestplate_defense",4,1,10000);
            IRON_LEGGINGS_DEFENSE = builder.defineInRange("leggings_defense",5,1,10000);
            IRON_BOOTS_DEFENSE = builder.defineInRange("boots_defense",2,1,10000);
            builder.pop();

            builder.push("gold");
            GOLD_ARMOR_DURABILITY_MULTIPLIER = builder.defineInRange("durability_multiplier",33,1,10000);
            GOLD_HELMET_DEFENSE = builder.defineInRange("helmet_defense",2,1,10000);
            GOLD_CHESTPLATE_DEFENSE = builder.defineInRange("chestplate_defense",6,1,10000);
            GOLD_LEGGINGS_DEFENSE = builder.defineInRange("leggings_defense",7,1,10000);
            GOLD_BOOTS_DEFENSE = builder.defineInRange("boots_defense",3,1,10000);
            builder.pop();

            builder.pop();

            builder.push("tools");
            builder.push("iron");
            IRON_TIER_DURABILITY = builder.defineInRange("tier_durability",200,1,100000);
            IRON_TIER_SPEED = builder.defineInRange("tier_speed",5.5,0,10000);
            builder.pop();
            builder.push("gold");
            GOLD_TIER_DURABILITY = builder.defineInRange("tier_durability",300,1,100000);
            GOLD_TIER_SPEED = builder.defineInRange("tier_speed",7.,0,10000);
            builder.pop();
            builder.pop();
        }
    }

}
