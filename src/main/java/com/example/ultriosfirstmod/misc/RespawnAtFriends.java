package com.example.ultriosfirstmod.misc;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.DeathScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

public class RespawnAtFriends {

    private static final Logger logger = LogUtils.getLogger();

    public static void init() {
        NeoForge.EVENT_BUS.addListener((ScreenEvent.Init.Post event) -> {
            Screen screen = event.getScreen();
            if (!(screen instanceof DeathScreen deathScreen)) return;
            int width = deathScreen.width;
            int height = deathScreen.height;
            Button respawnAtFriendButton = Button.builder(
                            Component.translatable("ultrio.deathScreen.respawnAtFriend"),
                            RespawnAtFriends::onRespawnAtFriend
                    )
                    .bounds(width / 2 - 100, height / 4 + 48, 200, 20)
                    .build();
            deathScreen.exitButtons.addFirst(respawnAtFriendButton);
            event.addListener(respawnAtFriendButton);
            respawnAtFriendButton.active = false;
            for (Button button : deathScreen.exitButtons) {
                button.setPosition(button.getX(), button.getY() + 24);
            }
        });

    }

    public static void onRespawnAtFriend(Button button) {
        logger.info("Player tried to respawn at friend.");
    }
}
