package commands;

import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

public class HelpCommand {
    public static void help(MessageChannel channel) {
        channel.sendMessage("Here are the list of commands you can currently use with Musicz!\n\n" +
                "1. **`!help`** **`Displays a list of commands you can use.`**\n\n" +
                "2. **`!play <song>`** **`Adds the song into the current queue and plays the first one.`**\n\n" +
                "3. **`!pause`** **`Pauses the current song playing.`**\n\n" +
                "4. **`!resume`** **`Resumes the paused song.`**\n\n" +
                "5. **`!skip`** **`Skips over to the next song in the queue.`**\n\n" +
                "6. **`!stop`** **`Stops the whole bot from playing music.`**\n\n" +
                "7. **`!clear`** **`Clears the music queue but continues playing the current song.`**\n\n" +
                "8. **`!queue`** **`Reveals the music queue.`**\n\n" +
                "9. **`!lyrics <song> <artist>`** **`Pulls up the lyrics to a song. Add both song and artist name for the best results.`**\n\n").queue();
    }
}
