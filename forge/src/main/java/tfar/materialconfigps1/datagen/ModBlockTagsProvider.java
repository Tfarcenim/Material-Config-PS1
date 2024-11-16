package tfar.materialconfigps1.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import tfar.materialconfigps1.MaterialConfigPS1;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(DataGenerator p_126511_,@Nullable ExistingFileHelper existingFileHelper) {
        super(p_126511_, MaterialConfigPS1.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(BlockTags.NEEDS_IRON_TOOL).remove(Blocks.DIAMOND_BLOCK,Blocks.DIAMOND_ORE,Blocks.DEEPSLATE_DIAMOND_ORE);
        tag(Tags.Blocks.NEEDS_GOLD_TOOL).add(Blocks.DIAMOND_BLOCK,Blocks.DIAMOND_ORE,Blocks.DEEPSLATE_DIAMOND_ORE);
    }
}
