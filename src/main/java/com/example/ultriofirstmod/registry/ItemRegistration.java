package com.example.ultriofirstmod.registry;

import com.example.ultriofirstmod.UltriosFirstMod;
import com.example.ultriofirstmod.item.ChocoFish;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemLore;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemRegistration {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(BuiltInRegistries.ITEM, UltriosFirstMod.MODID);

    public static final DeferredHolder<Item, Item> CHOCOFISH = ITEMS.register(
            "chocofish",
            () -> new Item(new Item.Properties()
                    .food(ChocoFish.FOOD_PROPERTIES)
                    .component(DataComponents.LORE, ChocoFish.LORE)
            )
    );

    public static final DeferredHolder<Item, Item> ICON = ITEMS.register("icon_item", ()->new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
