import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandManager extends ListenerAdapter {
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        System.out.println("hi");
        Message message = event.getMessage();

// Get the content of the message as a string
        String messageContent = message.getContentRaw();

// Print the message to the console
        System.out.println(messageContent);


    }
    /*
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        System.out.println("here");
        String command = event.getName();
        event.reply(command).queue();
        if (command.equals("test")) {
            event.reply("Testing!").queue();
        } else if (command.contains("play")) {

        }

    }
    */

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();
        //commandData.add(Commands.message("!test"));
        //commandData.add(Commands.slash("test", "Testing commands"));
        //commandData.add(Commands.slash("play", "Play a song"));
        event.getGuild().updateCommands().addCommands(commandData).queue();
    }

}
