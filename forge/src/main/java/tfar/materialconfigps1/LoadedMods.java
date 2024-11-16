package tfar.materialconfigps1;

import net.minecraftforge.fml.loading.LoadingModList;

public enum LoadedMods {
    fullturtlearmor,enderitemod;
    public final boolean loaded = LoadingModList.get().getModFileById(name()) != null;

}
