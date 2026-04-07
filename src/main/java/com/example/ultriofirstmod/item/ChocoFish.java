package com.example.ultriofirstmod.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemLore;

import java.util.ArrayList;
import java.util.List;

public class ChocoFish extends Item {
    public static final FoodProperties FOOD_PROPERTIES = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(4.0F)
            .effect(() -> new MobEffectInstance(MobEffects.DARKNESS, 10, 0), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 3, 5), 0.5F)
            .build();

    public static final ItemLore LORE = new ItemLore(
        List.of(Component.translatable("ultriosfirstmod.lore.fishgusting"))
    );

    public ChocoFish(Properties properties) {
        super(properties);
    }
}
