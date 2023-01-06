import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import org.jetbrains.annotations.NotNull;

public class CommandManager extends ListenerAdapter {
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        //Grab the user's message to see if it's a command
        String message = event.getMessage().getContentRaw();

        //Just a test command to see if the bot is working properly
        if (message.equals("!test")) {
            event.getChannel().sendMessage("Testing!").queue();
            //
        } else if (message.startsWith("!play")) {
            String song = message.substring(message.indexOf(" ") + 1);
            if (message.equals("!play")) {
                event.getChannel().sendMessage("Please enter a song!").queue();
            } else {
                //Checks if the user is in a voice channel
                if (event.getMember().getVoiceState().getChannel() == null) {
                    event.getChannel().sendMessage("You are not in a voice channel!").queue();
                } else {
                    //playCommand(ArrayList queue, String song, MessageReceivedEvent event)
                }

            }
        }
    }


}
