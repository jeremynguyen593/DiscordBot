package commands;

import lavaplayer.GuildMusicManager;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

public class PauseCommand {
    public static void pause(GuildMusicManager musicManager, MessageChannel channel) {
        musicManager.player.setPaused(true);
        channel.sendMessage("Pausing music...").queue();
    }
}
