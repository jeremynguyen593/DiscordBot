package commands;

import lavaplayer.GuildMusicManager;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

public class StopCommand {
    public static void stop(GuildMusicManager musicManager, MessageChannel channel) {
        if(musicManager.scheduler.getPlayingTrack()==null) {
            channel.sendMessage("Music is not currently playing.").queue();
        } else {
            channel.sendMessage("Stopping all music.").queue();
            musicManager.player.stopTrack();
        }
    }
}
