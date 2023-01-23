package commands;

import core.ProjectInfo;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class LyricsCommand {
    private static final String GENIUS_EMBED_URL_HEAD = "https://genius.com/songs/";
    private static final String GENIUS_EMBED_URL_TAIL = "/embed.js";
    public static void lyrics(MessageChannel channel, String query) throws IOException {
        String lyricApi = System.getenv("TOKEN2");

        String titleAndAuthor = query.replace(" ", "%20");

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://genius-song-lyrics1.p.rapidapi.com/search/?q=" + titleAndAuthor +"&per_page=10&page=1")
                .get()
                .addHeader("X-RapidAPI-Key", lyricApi)
                .addHeader("X-RapidAPI-Host", "genius-song-lyrics1.p.rapidapi.com")
                .build();

        Response response = client.newCall(request).execute();

        ResponseBody body = response.body();
        try {
            String responseBody = body.string();
            JSONObject json = new JSONObject(responseBody);
            JSONArray hits = json.getJSONArray("hits");
            String id = hits.getJSONObject(0).getJSONObject("result").get("id").toString();

            String lyrics = parseLyrics(id);

            EmbedBuilder embed = new EmbedBuilder();
            embed.setDescription(lyrics);

            channel.sendMessageEmbeds(embed.build()).queue();
        } finally {
            body.close();
            }
        }
    private static String parseLyrics(String id) {
        try {
            URLConnection connection = new URL(GENIUS_EMBED_URL_HEAD + id + GENIUS_EMBED_URL_TAIL).openConnection();
            connection.setRequestProperty("User-Agent", ProjectInfo.VERSION);
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\A");
            String raw = "";
            while (scanner.hasNext()) {
                raw += scanner.next();
            }
            if (raw.equals("")) {
                return null;
            }
            return getReadable(raw);
        } catch (IOException e) {
            return null;
        }
    }

    private static String getReadable(String rawLyrics) {
        //Remove start
        rawLyrics = rawLyrics.replaceAll("[\\S\\s]*<div class=\\\\\\\\\\\\\"rg_embed_body\\\\\\\\\\\\\">[ (\\\\\\\\n)]*", "");
        //Remove end
        rawLyrics = rawLyrics.replaceAll("[ (\\\\\\\\n)]*<\\\\/div>[\\S\\s]*", "");
        //Remove tags between
        rawLyrics = rawLyrics.replaceAll("<[^<>]*>", "");
        //Unescape spaces
        rawLyrics = rawLyrics.replaceAll("\\\\\\\\n","\n");
        //Unescape '
        rawLyrics = rawLyrics.replaceAll("\\\\'", "'");
        //Unescape "
        rawLyrics = rawLyrics.replaceAll("\\\\\\\\\\\\\"", "\"");
        return rawLyrics;
    }
}
