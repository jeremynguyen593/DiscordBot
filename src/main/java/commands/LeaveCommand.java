package commands;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import lavaplayer.GuildMusicManager;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.List;

public class LeaveCommand {
    public static void leave(VoiceChannel channel) {
        AudioManager audioManager = channel.getGuild().getAudioManager();

        audioManager.closeAudioConnection();
    }
}
