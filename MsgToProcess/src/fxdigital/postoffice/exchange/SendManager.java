package fxdigital.postoffice.exchange;

import fxdigital.mqcore.base.BaseReciver;

public class SendManager {

	public SendManager() {

	}

	public void startSender(BaseReciver receiver,
			ReReceiveHandler handler) {
		new StartSenderThread(receiver,handler).start();
	}

	class StartSenderThread extends Thread {

		private ReReceiveHandler handler;
		private BaseReciver receiver;

		public StartSenderThread(BaseReciver receiver,
				ReReceiveHandler handler) {
			this.receiver = receiver;
			this.handler = handler;
		}

		@Override
		public void run() {
            while(true){
			receiver.recive(handler);
            }

		}

	}

}
