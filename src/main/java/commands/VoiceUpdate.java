package commands;

import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class VoiceUpdate extends ListenerAdapter {
    public void onGuildVoiceUpdate(GuildVoiceUpdateEvent event) {
        if(event.getEntity().equals(event.getJDA().getSelfUser())) return;
        VoiceChannel voiceChannel = (VoiceChannel) event.getChannelLeft();
        if (event.getGuild().getAudioManager().isConnected()) {
            if (event.getGuild().getAudioManager().getConnectedChannel().equals(voiceChannel)) {
                if (voiceChannel.getMembers().size() == 1) {
                    event.getGuild().getAudioManager().closeAudioConnection();
                }
            }
        }
    }
}