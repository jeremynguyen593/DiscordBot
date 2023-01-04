import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class DiscordBot extends ListenerAdapter{
    public static void main(String[] args) throws LoginException {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        final String DISCORD = dotenv.get("DISCORD_API_KEY");

        JDABuilder jdaBuilder = JDABuilder.createDefault(DISCORD);
        jdaBuilder.enableIntents(GatewayIntent.MESSAGE_CONTENT);
        JDA jda = jdaBuilder.build();

        jda.addEventListener(new CommandManager());
    }

}
