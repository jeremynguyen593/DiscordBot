package commands;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import lavaplayer.GuildMusicManager;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

public class SkipCommand {
    public static void skip(GuildMusicManager musicManager, MessageChannel channel) {
        AudioTrack track = musicManager.scheduler.getPlayingTrack();
        if(musicManager.scheduler.getPlayingTrack()==null) {
            channel.sendMessage("Music is not currently playing.").queue();
        } else {
            channel.sendMessage("Skipping: **`" + track.getInfo().title + "`** by **`" + track.getInfo().author + "`**").queue();
            musicManager.scheduler.nextTrack();
        }
    }
}
