package de.vepexlegit.stepdebug;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = StepDebug.MODID, version = StepDebug.VERSION)
public class StepDebug {
    public static final String MODID = "stepdebug";
    public static final String VERSION = "1.0";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        System.out.println("DIRT BLOCK >> " + Blocks.dirt.getUnlocalizedName());
    }
}
