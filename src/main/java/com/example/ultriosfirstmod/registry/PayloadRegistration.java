package com.example.ultriosfirstmod.registry;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class PayloadRegistration {

    public static void register(IEventBus eventBus) {
        eventBus.addListener(RegisterPayloadHandlersEvent.class, (event) -> {
            final PayloadRegistrar registrar = event.registrar("1");

        });
    }
}
