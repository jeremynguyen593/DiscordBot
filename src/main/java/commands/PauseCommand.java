package commands;

import lavaplayer.GuildMusicManager;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

public class PauseCommand {
    public static void pause(GuildMusicManager musicManager, MessageChannel channel) {
        if(musicManager.player.isPaused()) {
            channel.sendMessage("Music is currently paused.").queue();
        } else if(musicManager.scheduler.getPlayingTrack()==null) {
            channel.sendMessage("Music is not currently playing.").queue();
        } else {
            musicManager.player.setPaused(true);
            channel.sendMessage("Pausing music...").queue();
        }
    }
}
