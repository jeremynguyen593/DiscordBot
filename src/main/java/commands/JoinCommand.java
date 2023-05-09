package commands;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class JoinCommand {
    public static void joinChannel(MessageReceivedEvent event) {
        // Test
        Guild guild = event.getGuild();

        VoiceChannel userVoiceChannel = (VoiceChannel) event.getMember().getVoiceState().getChannel();

        if (!userVoiceChannel.getGuild().getSelfMember().hasPermission(userVoiceChannel, Permission.VOICE_CONNECT)) {
            event.getChannel().sendMessage("I do not have permission to join this voice channel.").queue();
            return;
        }

        guild.getAudioManager().openAudioConnection(userVoiceChannel);

    }
}
