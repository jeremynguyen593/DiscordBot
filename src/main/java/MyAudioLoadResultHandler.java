import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

public class MyAudioLoadResultHandler implements AudioLoadResultHandler {
    private final AudioPlayer player;

    public MyAudioLoadResultHandler(AudioPlayer player) {
        this.player = player;
    }

    @Override
    public void trackLoaded(AudioTrack track) {
        // The track has been successfully loaded
        player.playTrack(track);
    }

    @Override
    public void playlistLoaded(AudioPlaylist playlist) {
        // The playlist has been successfully loaded
        // You can get the tracks in the playlist using the getTracks() method
    }

    @Override
    public void noMatches() {
        // No tracks were found that match the search criteria
    }

    @Override
    public void loadFailed(FriendlyException exception) {
        // An error occurred while loading the track
    }
}
