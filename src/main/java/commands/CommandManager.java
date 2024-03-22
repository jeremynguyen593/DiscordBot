package commands;

import lavaplayer.GuildMusicManager;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import static commands.ClearCommand.clear;
import static commands.HelpCommand.help;
import static commands.LeaveCommand.leave;
import static commands.LyricsCommand.lyrics;
import static commands.PlayCommand.play;
import static commands.PauseCommand.pause;
import static commands.QueueCommand.queue;
import static commands.ResumeCommand.resume;
import static commands.SkipCommand.skip;
import static commands.StopCommand.stop;
import static lavaplayer.MusicBot.getGuildAudioPlayer;

public class CommandManager extends ListenerAdapter {
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        // If the bot sent the message, ignore it
        if (event.getAuthor().isBot()) {
            return;
        }

        GuildMusicManager musicManager = getGuildAudioPlayer(event.getGuild(), event);
        // Grab the user's message to see if it's a command
        String message = event.getMessage().getContentRaw();

        // Just a test command to see if the bot is working properly
        if (message.equals("!help")) {
            help(event.getChannel());
        } else if (message.startsWith("!play")) {
            String song = message.substring(message.indexOf(" ") + 1);
            if (message.equals("!play")) {
                event.getChannel().sendMessage("Please enter a song.").queue();
            } else {
                play(song, event, musicManager);
            }
        } else if (message.equals("!pause")) {
            pause(musicManager, event.getChannel());
        } else if (message.equals("!resume")) {
            resume(musicManager, event.getChannel());
        } else if (message.equals("!skip")) {
            skip(musicManager, event.getChannel());
        } else if (message.equals("!stop")) {
            stop(musicManager, event.getChannel());
        } else if (message.equals("!queue")) {
            queue(musicManager, event);
        } else if (message.equals("!clear")) {
            clear(musicManager, event.getChannel());
        } else if (message.startsWith("!lyrics")) {
            String query = message.substring(message.indexOf(" ") + 1);

            try {
                lyrics(event.getChannel(), query);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (message.equals("!leave")) {
            VoiceChannel voiceChannel = (VoiceChannel) event.getMember().getVoiceState().getChannel();
            leave(voiceChannel);
        }
    }


}
