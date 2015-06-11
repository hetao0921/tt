package app.handler;

import java.util.UUID;

import org.misc.log.LogUtil;

import app.ConnectChannel;

import NVMP.Proxy.CommandDomain;

public class CommandHandler extends CommandDomain.EventHandler {
	ConnectChannel channel;

	public CommandHandler(ConnectChannel channel) {
		this.channel = channel;
	}

	/**
	 * 指挥端推送图像
	 */
	@Override
	public Object VideoeAssignEvent(String DevicerId, Integer Index,
			String DestCommander, Boolean IsStart) {
		String padIP = channel.getPad().getBean().getSinglePadIP();
		LogUtil.info("单兵系统：指挥端推送视频给单兵（"+padIP+"）------");
		LogUtil.info("DevicerId："+DevicerId);
		LogUtil.info("Index："+Index);
		LogUtil.info("DestCommander："+DestCommander);
		LogUtil.info("IsStart："+IsStart);
		String uuid = "_"+UUID.randomUUID();
		channel.noticeSPToPlayVideo(DevicerId, Index, uuid);

		return null;
	}
}
