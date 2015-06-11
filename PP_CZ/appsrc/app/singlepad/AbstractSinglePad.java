package app.singlepad;

import app.ConnectChannel;

/**
 * 
 * @author hxht
 * @version 2014-9-17
 */
public abstract class AbstractSinglePad implements ISinglePad{

	protected ISinglePadBean bean;
	protected SinglePadState state = SinglePadState.offline;
	protected ConnectChannel channel;
	
	@Override
	public ISinglePadBean getBean() {
		return bean;
	}

	@Override
	public void setBean(ISinglePadBean bean) {
		this.bean = bean;
	}

	@Override
	public SinglePadState getState() {
		return state;
	}

	@Override
	public void setState(SinglePadState state) {
		this.state = state;
	}

	@Override
	public void setConnectChannel(ConnectChannel cc) {
		this.channel = cc;
	}
	
}
