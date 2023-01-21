package commands;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import lavaplayer.GuildMusicManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class QueueCommand {
    public static void queue(GuildMusicManager musicManager, MessageReceivedEvent event) {
        List<AudioTrack> queueList = musicManager.scheduler.getQueue();
        AudioTrack track = musicManager.player.getPlayingTrack();

        if (queueList.isEmpty() && track == null) {
            event.getChannel().sendMessage("The queue is currently empty.").queue();
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Currently playing: **`" +  track.getInfo().title + "`** by **`" + track.getInfo().author + "`**\n\n");

            for (int i = 0; i < queueList.size(); i++) {
                sb.append((i + 1) + ". **`" + queueList.get(i).getInfo().title + "`** by **`" + queueList.get(i).getInfo().author + "`**\n\n");
            }
            event.getChannel().sendMessage(sb.toString()).queue();
        }
    }
}
