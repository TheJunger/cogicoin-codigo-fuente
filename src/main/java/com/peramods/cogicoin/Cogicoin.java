package com.peramods.cogicoin;

import com.peramods.cogicoin.init.InitItems;
import com.peramods.cogicoin.loot.ModLoadModifier;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Cogicoin.MODID)
public class Cogicoin {

    public static final String MODID = "cogicoin";

    public Cogicoin() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        InitItems.register(modEventBus);
        ModLoadModifier.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }
}
