package commands;

import lavaplayer.GuildMusicManager;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

public class ResumeCommand {
    public static void resume(GuildMusicManager musicManager, MessageChannel channel) {
        if(musicManager.scheduler.getPlayingTrack()==null) {
            channel.sendMessage("Music is not currently playing").queue();
        } else if(!musicManager.player.isPaused()) {
            channel.sendMessage("Music is not currently paused").queue();
        } else {
            musicManager.player.setPaused(false);
            channel.sendMessage("Resuming music...").queue();
        }
    }
}
