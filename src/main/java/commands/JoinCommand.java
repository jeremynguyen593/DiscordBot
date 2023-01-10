package commands;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class JoinCommand {
    public static void joinChannel(MessageReceivedEvent event) {
        Guild guild = event.getGuild();

        JDA jda = event.getJDA();

        String channelUserIsIn = event.getMember().getVoiceState().getChannel().toString();

        VoiceChannel voiceChannel = guild.getVoiceChannelsByName(channelUserIsIn, true).get(0);

        Member bot = guild.getSelfMember();

    }
}
