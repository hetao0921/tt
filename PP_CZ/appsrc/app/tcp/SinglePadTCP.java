package app.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import app.singlepad.ISinglePad;
import app.singlepad.impl.DefaultSinglePadBeanImpl;
import app.singlepad.impl.DefaultSinglePadImpl;

/**
 * 向单兵发送指令
 * @author hxht
 * @version 2014-9-17
 */
public class SinglePadTCP {
	
	public static final short RETURN = 1;//返回码
	public static final short GET = 2;//获取参数指令
	public static final short SET = 3;//设置参数指令
	public static final short SYS = 4;//系统控制指令
	public static final short WTF = 5;//写文本文件指令
	public static final short RTF = 6;//读文本文件指令
	public static final short LFS = 7;//本地文件检索指令
	
	private ISinglePad singlePad;
	
	private Socket socket;
	private OutputStream out;
	private BufferedReader in;
	
	public SinglePadTCP(ISinglePad singlePad){
		this.singlePad = singlePad;
	}
	
	public void connect(){
		try {
			if(socket != null){
				socket.close();
			}
			if(out != null){
				out.close();
			}
			if(in != null){
				in.close();
			}
			Integer port = singlePad.getBean().getSinglePadPort();
			if(port == null){
				port = 6180;
			}
			socket = new Socket(singlePad.getBean().getSinglePadIP(),port);
			out = socket.getOutputStream();
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			new Thread(new Receive()).start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	class Receive implements Runnable{

		@Override
		public void run() {
			while(true){
				try {
					String echo = in.readLine();
					byte[] header = echo.substring(0, 4).getBytes();
					short type = header[1];
					short body_length;
					if(header[3] < 0){
						body_length = (short) (header[2]*(2^8) - header[3]);
					}else{
						body_length = (short) (header[2]*(2^8) + header[3]);
					}
					System.out.println("From Server : "+echo);
					System.out.println("Type : "+type);
					System.out.println("Body length : "+body_length);
					System.out.println("Body : "+echo.substring(4));
				} catch (Exception e) {
					e.printStackTrace();
					//异常重连
					connect();
					break;
				}
			}
		}
		
	}

	public boolean send(short type, String text){
		//协议头和协议体组成的协议报文
		byte[] cmd;
		//协议体长度
		short bodyLength;
		//协议头 4个字节
		byte[] header = new byte[4];
		//协议体
		byte[] body = text.getBytes();
		
		bodyLength = (short) body.length;
		header[0] = 0x00;
		if(type > 255){
			return false;
		}
		header[1] = (byte) type;
		if(body.length > 65535){
			return false;
		}
		header[2] = (byte) (bodyLength >> 8);
		header[3] = (byte) (bodyLength & 0xff);
		
		cmd = arraycat(header,body);
		
		try {
			out.write(cmd);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			//异常重连
			connect();
			return false;
		}
	}

	private byte[] arraycat(byte[] buf1, byte[] buf2) {
		byte[] bufret = null;
		int len1 = 0;
		int len2 = 0;
		if (buf1 != null)
			len1 = buf1.length;
		if (buf2 != null)
			len2 = buf2.length;
		if (len1 + len2 > 0)
			bufret = new byte[len1 + len2];
		if (len1 > 0)
			System.arraycopy(buf1, 0, bufret, 0, len1);
		if (len2 > 0)
			System.arraycopy(buf2, 0, bufret, len1, len2);
		return bufret;
	}

	public static void main(String[] args) {
		String ip = "192.168.1.49";
		int port = 6180;
		
		DefaultSinglePadBeanImpl bean = new DefaultSinglePadBeanImpl();
		bean.setSinglePadIP(ip);
		bean.setSinglePadPort(port);
		
		DefaultSinglePadImpl pad = new DefaultSinglePadImpl();
		pad.setBean(bean);
		
		SinglePadTCP tcp = new SinglePadTCP(pad);
		tcp.connect();
//		tcp.send(GET, "net_send2.url\n");
//		tcp.send(SET, "net_recv1.url=rtsp://admin:12345@192.168.1.165:554/H264\n");
		tcp.send(GET, "net_send1.client1.ipaddr\n");
	}

	public static String analysisByte(byte data) {
		StringBuilder result = new StringBuilder();
		for (int i = 7; i >= 0; i--) {
			result.append((data >> i) & 0x01);
		}
		return result.toString();
	}

}
