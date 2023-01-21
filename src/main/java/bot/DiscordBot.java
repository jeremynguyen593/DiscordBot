package bot;

import commands.CommandManager;
import commands.VoiceUpdate;
import lavaplayer.MusicBot;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class DiscordBot extends ListenerAdapter{
    @Autowired
    private Environment env;

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(DiscordBot.class, args);
        Environment env = ctx.getBean(Environment.class);
        final String DISCORD = env.getProperty("TOKEN");

        MusicBot musicBot = new MusicBot();

        JDABuilder jdaBuilder = JDABuilder.createDefault(DISCORD);
        jdaBuilder.enableIntents(GatewayIntent.MESSAGE_CONTENT);
        jdaBuilder.enableCache(CacheFlag.VOICE_STATE);
        JDA jda = jdaBuilder.build();

        jda.addEventListener(new VoiceUpdate(), new CommandManager(), musicBot, new JoinMessage());
    }
}
