package commands;

import lavaplayer.GuildMusicManager;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

public class ResumeCommand {
    public static void resume(GuildMusicManager musicManager, MessageChannel channel) {
        musicManager.player.setPaused(false);
        channel.sendMessage("Resuming music...").queue();
    }
}
