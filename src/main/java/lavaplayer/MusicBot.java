package lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.HashMap;
import java.util.Map;

public class MusicBot extends ListenerAdapter {
    public static final AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
    private static final Map<Guild, GuildMusicManager> musicManagers = new HashMap<>();

    public MusicBot() {
        AudioSourceManagers.registerRemoteSources(playerManager);
    }

    public static GuildMusicManager getGuildAudioPlayer(Guild guild, MessageReceivedEvent event) {
        GuildMusicManager musicManager = musicManagers.get(guild);
        if (musicManager == null) {
            musicManager = new GuildMusicManager(playerManager, event);
            musicManagers.put(guild, musicManager);
        }
        guild.getAudioManager().setSendingHandler(musicManager.getSendHandler());
        return musicManager;
    }
}
