package push_server;

import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SocketChannelPool {
	private Map<SocketChannel,ChannelMessage> channelPool = new HashMap<SocketChannel,ChannelMessage>();
	
	/**
	 * 添加socketChannel
	 * @param socket
	 * @param life
	 */
	public void put(SocketChannel socket,long life){
		synchronized(channelPool){
			channelPool.put(socket, new ChannelMessage(life));
		}
	}
	
	/**
	 * 获取所有
	 * @param socket
	 * @return
	 */
	public Set<SocketChannel> getChannels(){
		Set<SocketChannel> socketChannels = new HashSet<SocketChannel>();
		for(SocketChannel channel:channelPool.keySet()){
			socketChannels.add(channel);
		}
		return socketChannels;
	}
	
	/**
	 * @param channel
	 */
	public void UpdateListTime(SocketChannel channel){
		channelPool.get(channel).lastUpdateTime = new Date().getTime();
	}
	
	/**
	 * 移除指定的socketChannel
	 * @param channel
	 */
	public void remove(SocketChannel channel){
		synchronized(channelPool){
			channelPool.remove(channel);
		}
	}
	
	/**
	 * @param socketChannel
	 * @return
	 * @throws Exception
	 */
	public boolean isTimeout(SocketChannel socketChannel) throws Exception{
		ChannelMessage cm = channelPool.get(socketChannel);
		
		if(cm == null){
			throw new Exception("channel is not in this channel pool");
		}else {
			return (new Date().getTime() - cm.lastUpdateTime) > cm.life;
		}
	}
	
	/**
	 * 指定的socketChannel是否在通道池里存在
	 * @param socketChannel
	 * @return
	 */
	public boolean contains(SocketChannel socketChannel){
		return channelPool.keySet().contains(channelPool);
	}
	
	/**
	 * 
	 * 
	 */
	class ChannelMessage {
		public long lastUpdateTime;
		public long addTime;
		public long life;
		
		public ChannelMessage(long life){
			this.lastUpdateTime = new Date().getTime();
			this.addTime 		= this.lastUpdateTime;
			this.life			= life;
		}
	}
}