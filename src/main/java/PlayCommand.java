import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;

public class PlayCommand {
    public static void play(String song) throws Exception{
        YouTube yt = new YouTube.Builder(GoogleNetHttpTransport.newTrustedTransport(),
                     JacksonFactory.getDefaultInstance(), null)
                    .setApplicationName("Discord Bot")
                    .build();



    }
}
