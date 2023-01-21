package commands;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import lavaplayer.GuildMusicManager;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

import java.util.List;

public class ClearCommand {
    public static void clear(GuildMusicManager musicManager, MessageChannel channel) {
        List<AudioTrack> queueList = musicManager.scheduler.getQueue();
        AudioTrack track = musicManager.player.getPlayingTrack();

        if (queueList.isEmpty() && track == null) {
            channel.sendMessage("There is nothing playing at the moment.").queue();
        } else {
            musicManager.scheduler.clearQueue(track);
            channel.sendMessage("Queue is cleared!").queue();
        }
    }
}
