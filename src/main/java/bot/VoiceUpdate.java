package bot;

import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class VoiceUpdate extends ListenerAdapter {
    public void onGuildVoiceUpdate(GuildVoiceUpdateEvent event) {
        // If the bot is the one that triggers the event, ignore it
        if(event.getEntity().equals(event.getJDA().getSelfUser())) return;
        VoiceChannel voiceChannel = (VoiceChannel) event.getChannelLeft();
        // Checks if the bot is connected still
        if (event.getGuild().getAudioManager().isConnected()) {
            // Checks if the bot is connected to the channel that someone just left
            if (event.getGuild().getAudioManager().getConnectedChannel().equals(voiceChannel)) {
                // If the bot is by itself, disconnect
                if (voiceChannel.getMembers().size() == 1) {
                    event.getGuild().getAudioManager().closeAudioConnection();
                }
            }
        }
    }
}