package io.github.confuser2188.packetlistener;

import io.github.confuser2188.packetlistener.event.ReceivedPacketEvent;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PacketHandler extends ChannelDuplexHandler{

    private Player p;

    public PacketHandler(final Player p) {
        this.p = p;
    }

    // Packet received from player
    public void channelRead(ChannelHandlerContext c, Object m) throws Exception {
        // Call packet event
        ReceivedPacketEvent event = new ReceivedPacketEvent(p, m);
        Bukkit.getPluginManager().callEvent(event);

        // Drop packet if event is cancelled
        if(event.isCancelled()) return;

        super.channelRead(c, m);
    }
}
