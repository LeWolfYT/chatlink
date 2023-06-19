package com.sidgames5.chatlink.bot;

import com.sidgames5.chatlink.PluginConfig;
import com.sidgames5.chatlink.bot.event.MessageEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.Collection;

public class Bot {
    private static final Logger logger = LoggerFactory.getLogger("ChatLink Bot");
    public static JDA bot;

    public static void run() throws LoginException {
        logger.info("Starting bot");

        Collection<GatewayIntent> intents = new ArrayList<>();
        intents.add(GatewayIntent.GUILD_MEMBERS);
        intents.add(GatewayIntent.GUILD_MESSAGES);

        bot = JDABuilder.create(PluginConfig.get("BOT_TOKEN"), intents)
                .setActivity(Activity.playing("Minecraft"))
                .build();

        bot.addEventListener(new MessageEvent());

        logger.info("Bot started!");
    }

    public static void stop() {
        bot.shutdown();
    }

    public static void sendToDiscord(String sender, String message) {
        String messageFormatted = "<" + sender + "> " + message;
    }
}