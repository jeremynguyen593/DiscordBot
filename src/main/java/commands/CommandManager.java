package commands;

import lavaplayer.GuildMusicManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import org.jetbrains.annotations.NotNull;

import static commands.PlayCommand.play;
import static commands.PauseCommand.pause;
import static commands.ResumeCommand.resume;
import static commands.SkipCommand.skip;
import static lavaplayer.MusicBot.getGuildAudioPlayer;

public class CommandManager extends ListenerAdapter {
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        //If the bot sent the message, ignore it
        if (event.getAuthor().isBot()) {
            return;
        }
        GuildMusicManager musicManager = getGuildAudioPlayer(event.getGuild());
        //Grab the user's message to see if it's a command
        String message = event.getMessage().getContentRaw();

        //Just a test command to see if the bot is working properly
        if (message.equals("!help")) {
            event.getChannel().sendMessage("Helping!").queue();
            //
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
        }
    }


}
