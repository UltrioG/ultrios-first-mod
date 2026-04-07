package com.example.ultriosfirstmod.registry;

import com.example.ultriosfirstmod.UltriosFirstMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CreativeTabRegistration {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB, UltriosFirstMod.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN_TAB = CREATIVE_TABS.register(
            "ultrio_main_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ItemRegistration.ICON))
                    .title(Component.translatable("creativetab.ultriosfirstmod.ultrio_main_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ItemRegistration.CHOCOFISH.get());
                    })
                    .build()
    );

    public static void register(IEventBus eventBus) {
        CREATIVE_TABS.register(eventBus);
    }
}
