package bot;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class JoinMessage extends ListenerAdapter {
    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        Guild guild = event.getGuild();
        TextChannel channel = (TextChannel) guild.getDefaultChannel();
        channel.sendMessage("Hello, I am Musicz! Thanks for inviting me! To get started, type **`!play <song_name>`**.\n\n" +
                "Use **`!help`** for a list of commands.").queue();
    }
}
