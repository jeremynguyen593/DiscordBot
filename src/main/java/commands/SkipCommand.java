package commands;

import lavaplayer.GuildMusicManager;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

public class SkipCommand {
    public static void skip(GuildMusicManager musicManager, MessageChannel channel) {
        if(musicManager.scheduler.getPlayingTrack()==null) {
            channel.sendMessage("Music is not currently playing.").queue();
        } else {
            musicManager.scheduler.nextTrack();
        }
    }
}
