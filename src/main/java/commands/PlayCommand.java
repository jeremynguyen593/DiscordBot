package commands;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import lavaplayer.GuildMusicManager;
import lavaplayer.MusicBot;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.net.URI;
import java.net.URISyntaxException;

import static commands.JoinCommand.joinChannel;
import static lavaplayer.MusicBot.getGuildAudioPlayer;

public class PlayCommand {
    public static void play(String song, MessageReceivedEvent event) {
        //Checks if the user is in a voice channel
        if (event.getMember().getVoiceState().getChannel() == null) {
            event.getChannel().sendMessage("You are not in a voice channel!").queue();
            return;
        }

        joinChannel(event);

        GuildMusicManager musicManager = getGuildAudioPlayer(event.getGuild());

            if(!isUrl(song)) {
                song = "ytsearch:" + song + " official audio";
            }

        MusicBot.playerManager.loadItemOrdered(musicManager, song, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack audioTrack) {
                event.getChannel().sendMessage("Adding to the queue **`" + audioTrack.getInfo().title +
                        "`** by **`" + audioTrack.getInfo().author + "`**").queue();
                musicManager.scheduler.queue(audioTrack);
            }

            @Override
            public void playlistLoaded(AudioPlaylist audioPlaylist) {
                for (AudioTrack tracks : audioPlaylist.getTracks()) {
                    event.getChannel().sendMessage("Adding to the queue **`" + tracks.getInfo().title +
                            "`** by **`" + tracks.getInfo().author + "`**").queue();
                    musicManager.scheduler.queue(tracks);
                    break;
                }
            }

            @Override
            public void noMatches() {
                event.getChannel().sendMessage("Song was not found!").queue();
            }

            @Override
            public void loadFailed(FriendlyException e) {
                event.getChannel().sendMessage("Something went wrong. Please try again later.").queue();
            }
        });
    }

    public static boolean isUrl(String url) {
        try {
            new URI(url);
            return true;
        } catch (URISyntaxException e) {
            return false;
        }
    }
}
