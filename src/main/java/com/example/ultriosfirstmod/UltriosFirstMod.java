package com.example.ultriosfirstmod;

import com.example.ultriosfirstmod.registry.CreativeTabRegistration;
import com.example.ultriosfirstmod.registry.ItemRegistration;
import net.neoforged.bus.api.IEventBus;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import net.neoforged.fml.common.Mod;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(UltriosFirstMod.MODID)
public class UltriosFirstMod {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "ultriosfirstmod";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public UltriosFirstMod(IEventBus modBus) {
        CreativeTabRegistration.register(modBus);
        ItemRegistration.register(modBus);
    }

}
