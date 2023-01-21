package commands;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import lavaplayer.GuildMusicManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.net.URI;
import java.net.URISyntaxException;

import static commands.JoinCommand.joinChannel;
import static lavaplayer.MusicBot.playerManager;

public class PlayCommand {
    public static void play(String song, MessageReceivedEvent event, GuildMusicManager musicManager) {
        //Checks if the user is in a voice channel
        if (event.getMember().getVoiceState().getChannel() == null) {
            event.getChannel().sendMessage("You are not in a voice channel!").queue();
            return;
        }
        try {
            URI uri = new URI(song);
            String host = uri.getHost();
            if(!host.startsWith("www.youtube.com")) {
                song = "ytsearch:" +song + " official audio";
            }
        } catch (URISyntaxException e) {
            song = "ytsearch:" +song + " official audio";
        }

        playerManager.loadItemOrdered(musicManager, song, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack audioTrack) {
                musicManager.scheduler.queue(audioTrack);

            }

            @Override
            public void playlistLoaded(AudioPlaylist audioPlaylist) {
                for (AudioTrack tracks : audioPlaylist.getTracks()) {
                    joinChannel(event);
                    event.getChannel().sendMessage("Adding to the queue **`" + tracks.getInfo().title +
                            "`** by **`" + tracks.getInfo().author + "`**").queue();
                    musicManager.scheduler.queue(tracks);
                    break;
                }
            }

            @Override
            public void noMatches() {
                event.getChannel().sendMessage("Song was not found.").queue();
            }

            @Override
            public void loadFailed(FriendlyException e) {
                event.getChannel().sendMessage("Song may be private, age-restricted, and/or was an invalid URL.").queue();
            }
        });
    }
}
