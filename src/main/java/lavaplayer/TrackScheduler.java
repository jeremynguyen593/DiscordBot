package lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TrackScheduler extends AudioEventAdapter {
    private final AudioPlayer player;
    private final BlockingQueue<AudioTrack> queue;
    private final MessageReceivedEvent event;

    public TrackScheduler(AudioPlayer player, MessageReceivedEvent event) {
        this.player = player;
        this.queue = new LinkedBlockingQueue<>();
        this.event = event;
    }

    public void queue(AudioTrack track) {
        if (!this.player.startTrack(track, true)) {
            this.queue.offer(track);
        }
    }

    public void nextTrack() {
        this.player.startTrack(this.queue.poll(), false);
    }
    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        if (endReason.mayStartNext) {
            nextTrack();
        }
    }
    @Override
    public void onTrackStart(AudioPlayer player, AudioTrack track) {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.schedule(() -> {
            event.getChannel().sendMessage("Now playing: **`" + track.getInfo().title + "`** by **`" + track.getInfo().author + "`**").queue();
        }, 1, TimeUnit.SECONDS);
    }

    @Override
    public void onPlayerPause(AudioPlayer player) {
        player.setPaused(true);
    }

    @Override
    public void onPlayerResume(AudioPlayer player) {
        player.setPaused(false);
    }
    public AudioTrack getPlayingTrack() {
        return player.getPlayingTrack();
    }

    public List<AudioTrack> getQueue() {
        return new ArrayList<>(queue);
    }

    public void clearQueue(AudioTrack track) {
        track = queue.poll();
        queue.clear();
    }
}
