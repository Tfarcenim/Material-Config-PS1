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
        public static ForgeConfigSpec.IntValue GOLD_ARMOR_DURABILITY_MULTIPLIER;//normally 7

        Server(ForgeConfigSpec.Builder builder) {
            builder.push("armor");
            builder.push("iron");
            IRON_ARMOR_DURABILITY_MULTIPLIER = builder.defineInRange("durability_multiplier",12,1,10000);
            builder.pop();

            builder.push("gold");
            GOLD_ARMOR_DURABILITY_MULTIPLIER = builder.defineInRange("durability_multiplier",18,1,10000);
            builder.pop();

            builder.pop();
        }
    }

}
