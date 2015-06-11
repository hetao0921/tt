package NVMP.DeviceManage.Implement.devicectrl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.misc.log.LogUtil;

import sun.misc.BASE64Encoder;

public class RTSPClient extends Thread {
	private enum Status {
		INIT, OPTIONS, DESCRIBE, SETUP, PLAY, PAUSE, TEARDOWN
	}

	private Status sysStatus;

	private static final String VERSION = " RTSP/1.0\r\n";
	private static final String RTSP_OK = "RTSP/1.0 200 OK";
	private final static String CRLF = "\r\n";
	private AtomicBoolean shutdown; // �߳��Ƿ����
	private boolean isSended; // ÿ�η��ʹ���ı�־

	private static final int BUFFER_SIZE = 8192;
	private static final int VIDEOTIME = 30 * 60000; // ��Сʱ

	private InetSocketAddress remoteAddress;
	private String AuthorStr = "";

	private boolean bThreadRun = true;

	/** * ����ͨ�� */
	private SocketChannel socketChannel;
	/** ���ͻ����� */
	private final ByteBuffer sendBuf;
	/** ���ջ����� */
	private final ByteBuffer receiveBuf;

	/** �˿�ѡ���� */
	private Selector selector;

	/** ��Ӧ���� */
	private int seq = 1;
	private String sessionId;
	private String setUPVideoURL;
	private Timer timer; // ��ʱ�������ر�����

	public RTSPClient(String rtspURL) {
		String strIPPORT = "";
		int iserverPort = 554;
		String strServerIP = "";
		String urlsuffix = "";
		if (rtspURL.contains("@")) {
			int sindex = rtspURL.indexOf("@");
			int sindex2 = rtspURL.indexOf("/", 7);
			urlsuffix = rtspURL.substring(sindex2);
			strIPPORT = rtspURL.substring(sindex + 1, sindex2);
			AuthorStr = rtspURL.substring(7, sindex);
			if (strIPPORT.contains(":")) {
				int sindex3 = strIPPORT.indexOf(":");
				strServerIP = strIPPORT.substring(0, sindex3);
				try {
					iserverPort = Integer
							.parseInt(strIPPORT.substring(sindex3));
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getStackTrace());
				}
			} else {
				strServerIP = strIPPORT;
			}
		} else {
			int sindex4 = rtspURL.indexOf("/", 7);
			urlsuffix = rtspURL.substring(sindex4);
			strIPPORT = rtspURL.substring(7, sindex4);
			if (strIPPORT.contains(":")) {
				int sindex3 = strIPPORT.indexOf(":");
				strServerIP = strIPPORT.substring(0, sindex3);
				try {
					iserverPort = Integer
							.parseInt(strIPPORT.substring(sindex3));
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getStackTrace());
				}
			} else {
				strServerIP = strIPPORT;
			}
		}
		List<Inet4Address> addrList = IPAddressTool
				.GetAllIPV4AdressesWithoutLoopBackAddress();
		if (addrList.size() == 0) {
			this.targetIP = "127.0.0.1";
		} else {
			this.targetIP = addrList.get(0).toString().substring(1);
		}
		this.targetPort = 65324;
		this.remoteAddress = new InetSocketAddress(strServerIP, iserverPort);
		this.rtspUrl = "rtsp://" + strIPPORT + urlsuffix;
		this.recordTime = "10";
		// ��ʼ��������
		sendBuf = ByteBuffer.allocateDirect(BUFFER_SIZE);
		receiveBuf = ByteBuffer.allocateDirect(BUFFER_SIZE);
		if (selector == null) {
			try {
				selector = Selector.open();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		this.startup();
		sysStatus = Status.INIT;
		shutdown = new AtomicBoolean(false);
		isSended = false;
	}

	// public RTSPClient(String serverIP, int serverPort, String targetIP,
	// int targetPort, String rtspUrl,String recordTime) {
	// this.remoteAddress = new InetSocketAddress(serverIP, serverPort);
	// this.rtspUrl = rtspUrl;
	// this.targetIP=targetIP;
	// this.targetPort=targetPort;
	// if(recordTime==null){
	// this.recordTime="";
	// }else{
	// this.recordTime=recordTime;
	// }
	//
	// // ��ʼ��������
	// sendBuf = ByteBuffer.allocateDirect(BUFFER_SIZE);
	// receiveBuf = ByteBuffer.allocateDirect(BUFFER_SIZE);
	// if (selector == null) {
	// try {
	// selector = Selector.open();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// this.startup();
	// sysStatus = Status.INIT;
	// shutdown = new AtomicBoolean(false);
	// isSended = false;
	// }

	/**
	 * ��������
	 */
	public void startup() {
		try {
			// ��ͨ��
			socketChannel = SocketChannel.open();
			socketChannel.socket().setSoTimeout(30000);
			socketChannel.configureBlocking(false);
			socketChannel.connect(remoteAddress); // finish

			socketChannel.register(selector, SelectionKey.OP_CONNECT
					| SelectionKey.OP_READ | SelectionKey.OP_WRITE, this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * �ر�����
	 */
	public void shutdown() {
		
		bThreadRun = false;
		
		if (isConnected()) {
			try {
				socketChannel.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				socketChannel = null;
			}
		}

		try {
			selector.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		// ������ѭ������
		while (bThreadRun) {
			try {
				if (isConnected() && (!isSended)) {
					switch (sysStatus) {
					case INIT:
						this.doOption();
						break;
					case OPTIONS:
						this.doDescribe();
						break;
					case DESCRIBE:
						this.doSetup();
						break;
					case SETUP:
						if (sessionId == null || sessionId.length() == 0) {
							System.err.println("SETUP��û�����");
						} else {
							this.doPlay();
							/** ������ʱ������ */
							if (timer == null) {
								timer = new Timer();
								long executeTime = 0;
								if (recordTime.equals("")) {
									executeTime = VIDEOTIME;
								} else {
									executeTime = Integer.parseInt(recordTime);
								}
								timer.schedule(new TimerTask() {
									public void run() {
										doTeardown();
										sysStatus = Status.TEARDOWN;
										timer.cancel();
									}
								}, executeTime);
							}
						}
						break;
					case PAUSE:
						this.doPause();
						break;
					case TEARDOWN:
						this.doTeardown();
						bThreadRun = false;
						break;
					default:
						break;
					}
				}
				this.select(); // ��ȡ��Ӧ
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		System.out.println("zha zou zhong yu cong lao li chu lai le\n");
		this.shutdown();

	}

	/**
	 * ������Ϣ
	 */
	public void send(String msg) {
		byte[] out = msg.getBytes();
		if (out == null || out.length < 1)
			return;

		synchronized (sendBuf) {
			sendBuf.clear();
			sendBuf.put(out);
			sendBuf.flip();
		}

		if (isConnected()) {
			try {
				socketChannel.write(sendBuf);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("ͨ��Ϊ�ջ���û��������");
		}
		this.isSended = true;
	}

	public byte[] recieve() {
		if (isConnected()) {
			try {
				int len = 0;
				int readBytes = 0;

				synchronized (receiveBuf) {
					receiveBuf.clear();
					// д�����
					while ((len = socketChannel.read(receiveBuf)) > 0) {
						readBytes += len;
					}
					receiveBuf.flip();
					if (readBytes > 0) {
						byte[] tmp = new byte[readBytes];
						receiveBuf.get(tmp); // д�����
						return tmp;
					} else {
						System.err.println("���յ����Ϊ��");
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private void select() {
		if (selector == null) {
			return;
		}

		int n = 0;
		try {
			n = selector.select(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (n > 0) {
			for (Iterator<SelectionKey> i = selector.selectedKeys().iterator(); i
					.hasNext();) {
				SelectionKey sk = i.next();
				i.remove();

				if (sk.isValid()) { // �����ݺϷ�
					try {
						if (sk.isConnectable()) {
							this.reConnect(sk); // ��������
						} else if (sk.isReadable()) {
							this.handle(sk); // �������
						}
					} catch (Exception e) {
						e.printStackTrace();
						sk.cancel();
					}
				}
			}
		}
	}

	public boolean isConnected() {
		return socketChannel != null && socketChannel.isConnected();
	}

	public void reConnect(SelectionKey key) throws IOException {
		if (isConnected())
			return;

		socketChannel.finishConnect(); // �������
		while (!socketChannel.isConnected()) {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			socketChannel.finishConnect();
		}
	}

	public void handle(SelectionKey key) {
		byte[] msg = this.recieve();
		if (msg == null) {
//			key.cancel();
			this.shutdown();
			return;
		}

		String tmp = new String(msg);
		System.err.println(tmp);
		LogUtil.VideoInfo(tmp);
		if (tmp.startsWith(RTSP_OK)) {
			switch (sysStatus) {
			case INIT:
				sysStatus = Status.OPTIONS;
				break;
			case OPTIONS:
				sysStatus = Status.DESCRIBE;
				int sindex = tmp.indexOf("m=video");
				int s2index = tmp.indexOf("a=control:", sindex);
				int s3index = tmp.indexOf("\r", s2index);
				if ((s2index > 0) && (s3index > s2index)) {
					setUPVideoURL = tmp.substring(s2index + 10, s3index);
				}
				if (setUPVideoURL != null) {
					if (!setUPVideoURL.startsWith("rtsp://")) {
						setUPVideoURL = rtspUrl + setUPVideoURL;
					}
				} else {
					setUPVideoURL = rtspUrl;
				}
				System.out.println("setupvideourl = \n" + setUPVideoURL);
				break;
			case DESCRIBE:
				// ����sessionId
				int startIndex = tmp.indexOf("Session: ") + 9;
				int endIndex = tmp.indexOf(CRLF, startIndex);
				sessionId = tmp.substring(startIndex, endIndex);
				if (sessionId != null && sessionId.length() > 0) {
					sysStatus = Status.SETUP;
				}
				break;
			case SETUP:
				sysStatus = Status.PLAY;
				break;
			case TEARDOWN:
				shutdown.set(true);
			default:
				break;
			}
			isSended = false;
		} else {
			System.err.println("������Ϣ����");
			this.shutdown();
		}

	}

	private void doOption() {
		StringBuilder sb = new StringBuilder();
		sb.append("OPTIONS ").append(rtspUrl).append(VERSION);
		sb.append("CSeq: ").append(seq++).append(CRLF);
		if (!AuthorStr.equalsIgnoreCase("")) {
			sb.append(
					"Authorization: Basic "
							+ new BASE64Encoder().encode(AuthorStr.getBytes()))
					.append(CRLF);
		}
		sb.append(CRLF); // �������ü�
		System.out.println(sb.toString());
		LogUtil.VideoInfo(sb.toString());
		this.send(sb.toString());
	}

	private void doDescribe() {
		StringBuilder sb = new StringBuilder();
		sb.append("DESCRIBE ").append(this.rtspUrl).append(VERSION);
		sb.append("CSeq: ").append(seq++).append(CRLF);
		if (!AuthorStr.equalsIgnoreCase("")) {
			sb.append(
					"Authorization: Basic "
							+ new BASE64Encoder().encode(AuthorStr.getBytes()))
					.append(CRLF);
		}
		sb.append("User-Agent: GDDWStation/1.0").append(CRLF);
		sb.append("Accept: application/sdp").append(CRLF);
		sb.append(CRLF);
		System.out.println(sb.toString());
		LogUtil.VideoInfo(sb.toString());
		this.send(sb.toString());
	}

	private void doSetup() {
		if (!setUPVideoURL.startsWith("rtsp")) {
			setUPVideoURL = rtspUrl;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("SETUP ").append(setUPVideoURL).append(VERSION);
		sb.append("CSeq: ").append(seq++).append(CRLF);
		if (!AuthorStr.equalsIgnoreCase("")) {
			sb.append(
					"Authorization: Basic "
							+ new BASE64Encoder().encode(AuthorStr.getBytes()))
					.append(CRLF);
		}
		sb.append("Transport: RTP/AVP;unicast;destination=").append(targetIP)
				.append(";client_port=")
				.append(targetPort + "-" + (targetPort + 1)).append(CRLF);
		sb.append(CRLF);
		System.out.println(sb.toString());
		LogUtil.VideoInfo(sb.toString());
		this.send(sb.toString());
	}

	private void doPlay() {
		StringBuilder sb = new StringBuilder();
		sb.append("PLAY ").append(rtspUrl).append(VERSION);
		sb.append("Session: ").append(sessionId).append(CRLF);
		sb.append("CSeq: ").append(seq++).append(CRLF);
		if (!AuthorStr.equalsIgnoreCase("")) {
			sb.append(
					"Authorization: Basic "
							+ new BASE64Encoder().encode(AuthorStr.getBytes()))
					.append(CRLF);
		}
		sb.append("Range: ").append("npt=0.000-").append(recordTime)
				.append(CRLF);
		sb.append(CRLF);
		System.out.println(sb.toString());
		LogUtil.VideoInfo(sb.toString());
		this.send(sb.toString());
	}

	private void doPause() {
		StringBuilder sb = new StringBuilder();
		sb.append("PAUSE ").append(rtspUrl).append(VERSION);
		sb.append("CSeq: ").append(seq++).append(CRLF);
		if (!AuthorStr.equalsIgnoreCase("")) {
			sb.append(
					"Authorization: Basic "
							+ new BASE64Encoder().encode(AuthorStr.getBytes()))
					.append(CRLF);
		}
		sb.append("Session: ").append(sessionId).append(CRLF);
		sb.append(CRLF);
		System.out.println(sb.toString());
		LogUtil.VideoInfo(sb.toString());
		this.send(sb.toString());
	}

	private void doTeardown() {
		StringBuilder sb = new StringBuilder();
		sb.append("TEARDOWN ").append(rtspUrl).append(VERSION);
		sb.append("Session: ").append(sessionId).append(CRLF);
		sb.append("CSeq: ").append(seq++).append(CRLF);
		if (!AuthorStr.equalsIgnoreCase("")) {
			sb.append(
					"Authorization: Basic "
							+ new BASE64Encoder().encode(AuthorStr.getBytes()))
					.append(CRLF);
		}
		sb.append(CRLF);
		System.out.println(sb.toString());
		LogUtil.VideoInfo(sb.toString());
		this.send(sb.toString());
	}

	// �û����÷���
	public void play() {
		sysStatus = Status.PLAY;
	}

	public void pause() {
		sysStatus = Status.PAUSE;
	}

	public void teardown() {
		sysStatus = Status.TEARDOWN;
	}

	/** ������� */
	private String serverIP;
	private int serverPort;
	private String rtspUrl;
	private String targetIP;
	private int targetPort;
	private String recordTime; // �ط�ʱ��

	public String getRtspUrl() {
		return rtspUrl;
	}

	public void setRtspUrl(String rtspUrl) {
		this.rtspUrl = rtspUrl;
	}

	public String getTargetIP() {
		return targetIP;
	}

	public void setTargetIP(String targetIP) {
		this.targetIP = targetIP;
	}

	public int getTargetPort() {
		return targetPort;
	}

	public void setTargetPort(int targetPort) {
		this.targetPort = targetPort;
	}

	public String getServerIP() {
		return serverIP;
	}

	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public String getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}

	public static void main(String[] args) throws Exception {
		RTSPClient client = new RTSPClient("rtsp://192.168.1.199/D1");
		client.start();
	}

}

class IPAddressTool {

	public static List<Inet4Address> GetAllIPV4AdressesWithoutLoopBackAddress() {
		List<Inet4Address> Inet4AddressList = new ArrayList<Inet4Address>();
		try {
			for (Enumeration<NetworkInterface> iterator = NetworkInterface
					.getNetworkInterfaces(); iterator.hasMoreElements();) {
				NetworkInterface item = (NetworkInterface) iterator
						.nextElement();
				for (Enumeration<InetAddress> iterator2 = item
						.getInetAddresses(); iterator2.hasMoreElements();) {
					InetAddress item2 = (InetAddress) iterator2.nextElement();
					if (!(item2.isLoopbackAddress())) {
						if (item2 instanceof Inet4Address) {
							Inet4AddressList.add((Inet4Address) item2);
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return Inet4AddressList;
	}

}
