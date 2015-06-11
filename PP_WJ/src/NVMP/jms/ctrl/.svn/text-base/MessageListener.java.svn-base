package NVMP.jms.ctrl;

import java.io.FileNotFoundException;
import java.util.Calendar;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;


import NVMP.jms.base.BaseHeader;
import NVMP.jms.base.BaseProtocol;
import NVMP.jms.util.ByteArrayUtil;
import NVMP.jms.util.Encoding;


public class MessageListener implements javax.jms.MessageListener {

	private IMessageHander imh;
	
	public void setIh(IMessageHander imh) {
		this.imh = imh;
	}
    



	/**
	 * 用来处理广播信息接受的。
	 * 
	 * */
	@Override
	public void onMessage(Message message) {
		
//		Calendar ca = Calendar.getInstance();
//		long t1 = ca.getTimeInMillis();
//		try {
//			message.acknowledge();
//		} catch (JMSException e1) {
//			// TODO Auto-generated catch block
//			System.out.println("===========消息接收失败!");
//			return;
//		}
		// TODO Auto-generated method stub
		
		//接收时间和数据大小
	

		int n =0;
		
		if (message instanceof BytesMessage) {  
			try {
				BytesMessage bmg = (BytesMessage)message;
				/**
				 * 将信息进行分解，3个部分，包头，包体 ，数据。
				 * */

				n = bmg.getIntProperty("Length");
				//文本写入，接收大小  n
				
				
				byte[] array = new byte[n];
				bmg.readBytes(array);
				
				byte[] h = ByteArrayUtil.Trim(array, 0, BaseProtocol.HeadSize);
				if(h==null)
				{
					System.out.println("头文件解析错误，此次操作失败");
					return;
				}
				
				BaseHeader header = new BaseHeader(h);
				if(!header.ValidateMagic()) {
					System.out.println("头文件校验错误，此次操作失败");
					return;
				}
				
                
				//如果是正常处理。
				if(header.getType() == Protocol.RpcMessage) {
					byte[] body = ByteArrayUtil.Trim(array, BaseProtocol.HeadSize,header.getBodyLength());
					
					NVMP.jms.rpc.Message mg = new NVMP.jms.rpc.Message(Encoding.byteToString(body));
					byte[] data = ByteArrayUtil.Trim(array, BaseProtocol.HeadSize+header.getBodyLength(),array.length-BaseProtocol.HeadSize-header.getBodyLength());
					
					imh.ReceiveMessage(mg,data);
				}
				
				//计算处理时间
				
				
				
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

//		ca = Calendar.getInstance();
//		long t2 = ca.getTimeInMillis();
//		long t = t2-t1;
//		totalTime += t;
//		seq++;
//		String time =Common.getTimeNow()+ "：第"+seq+"次发送数据，" +
//				"耗时："+t+"毫秒，累计耗时："+totalTime+"毫秒。数组长度："+n+"\n";
//		writeTime(time);
	}
	
	
	

}
