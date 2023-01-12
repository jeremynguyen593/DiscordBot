package commands;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import io.github.cdimascio.dotenv.Dotenv;
import lavaplayer.AudioPlayerSendHandler;
import lavaplayer.TrackScheduler;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

public class PlayCommand {
    public static void play(String song, MessageReceivedEvent event) throws Exception{
        /*
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        final String YOUTUBE_KEY = dotenv.get("YOUTUBE_KEY");

        YouTube yt = new YouTube.Builder(GoogleNetHttpTransport.newTrustedTransport(),
                     JacksonFactory.getDefaultInstance(), null)
                    .setApplicationName("Discord Bot")
                    .build();

        YouTube.Search.List search = yt.search().list(Arrays.asList("id", "snippet"));
        search.setKey(YOUTUBE_KEY); // Replace with your API key
        search.setQ(song); // Replace with the search query (e.g. "Happy Birthday")
        search.setType(Arrays.asList("video"));
        search.setFields("items(id(videoId),snippet(publishedAt,channelId,channelTitle,title,description))");
        search.setMaxResults(1L);

        // Execute the search request
        SearchListResponse searchResponse = search.execute();
        List<SearchResult> searchResultList = searchResponse.getItems();
        if (searchResultList == null || searchResultList.isEmpty()) {
            // No results were found
            event.getChannel().sendMessage("Song was not found.");
            return;
        }

        // Get the first result from the search results
        SearchResult result = searchResultList.get(0);
        String videoId = result.getId().getVideoId();

         */

        // Build the YouTube video URL
        String youtubeVideoUrl = "https://www.youtube.com/watch?v=nV_uKxGPF_I"; //+ videoId

        AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(playerManager);

        AudioPlayer player = playerManager.createPlayer();

        TrackScheduler trackScheduler = new TrackScheduler(player);
        player.addListener(trackScheduler);

        Guild guild = event.getGuild();
        guild.getAudioManager().setSendingHandler(new AudioPlayerSendHandler(player));

        // Load and play the YouTube video
        playerManager.loadItem(youtubeVideoUrl, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack track) {
                // The track was successfully loaded
                player.playTrack(track);
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {
                // The playlist was successfully loaded
                for (AudioTrack track : playlist.getTracks()) {
                    player.playTrack(track);
                }
            }

            @Override
            public void noMatches() {
                // No tracks were found that match the search query
                event.getChannel().sendMessage("No songs were found.").queue();
            }

            @Override
            public void loadFailed(FriendlyException exception) {
                // An error occurred while loading the track
                event.getChannel().sendMessage("An error has occured.").queue();
            }
        });

    }
}
