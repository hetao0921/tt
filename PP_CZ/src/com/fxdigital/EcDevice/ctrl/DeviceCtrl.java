package com.fxdigital.EcDevice.ctrl;

public class DeviceCtrl { 
	private static DeviceCtrl _this;
	private static final String ClibName = "HikControl";

	private DeviceCtrl() {
		System.loadLibrary(ClibName);
	}

	private static IDevNotify idn = null;

	public static void setIdn(IDevNotify idn) {
		DeviceCtrl.idn = idn;
	}

	/**
	 * ���м����豸�����Ļص�ִ�нӿڡ�
	 * 
	 * */
	public static void handleAlarm(int LoginHandle, int AlarmType, int Value,
			Object Context) {
		if (idn != null) {
			idn.handleAlarm(LoginHandle, AlarmType, Value, Context);

		}

	};

	/**
	 * ���м����豸���ӵĸ��ֽӿڡ�
	 * 
	 * */
	public static void handleConnect(int LoginHandle, boolean Connected,
			Object Context) {
		if (idn != null) {

			idn.handleConnect(LoginHandle, Connected, Context);
		}
	};

	/**
	 * ��¼�豸��ʼ��
	 * 
	 * */
	public native boolean startup();

	/**
	 * �豸�����ʼ��
	 * */
	public native void startListen(int LoginHandle, String sAddress, int Port);

	/**
	 * ��¼�豸
	 * 
	 * @Param DeviceType �豸���ͣ���ʾĳһ���̵�һ���豸���纣��������
	 * @Param DeviceSubType �豸�����ͣ���ʾĳһ���豸��һ���������ͣ�ͨ������¿��Ժ��ԣ���0
	 * @Param DeviceIp �豸IP��ַ
	 * @Param Port �豸�˿�
	 * @Param Context �����ģ���֪ͨ�ӿ��л����ò���
	 * @Return ��¼���< 0 ��¼ʧ�� ע���¼�ɹ�������ʾ�豸���ߣ�����ͨ������֪ͨ�������ĳЩ�豸longinʼ�շ��سɹ���
	 */
	public native int login(String sAddress, int Port, String sUsrName,
			String sUsrPass, Object Context);

	/**
	 * �ǳ��豸
	 * 
	 * @Param LoginHandle ��¼���
	 * @Return ��
	 */
	public native void logout(int LoginHandle);

	/**
	 * ����״̬֪ͨ������
	 * 
	 * @Param notify ����������Ҫ��ʵ��IDevNotify�ӿ�
	 * @Return ��
	 */
	public native boolean addListener(IDevNotify notify);

	/**
	 * 设置设备时间
	 * */
	public native static boolean setTime(int hSession,int year,int month,int day,int hour,int minute,int second);
	
	
	
	/**
	 * ����ͼ�����
	 * 
	 * @Param LoginHandle ��¼���
	 * @Param Channel ͨ����
	 * @Param Type ����
	 * @Param
	 * @Return ��
	 */
	public native boolean ctrlVideoEffect(int LoginHandle, int Channel,
			int Type, int Value);
	
	public native boolean ctrlVideoCompress(int loginHandle, int channel, int type, int value);

	/**
	 * ���Ʊ�������������
	 * 
	 * @Param notify ����������Ҫ��ʵ��IDevNotify�ӿ�
	 * @Return �ɹ�ʧ�ܣ�ʧ��ԭ��ͨ��Ϊû�е�¼���豸
	 */
	public native boolean ctrlAlarm(int LoginHandle, int Channel, int Type,
			int Value);

	/**
	 * ������̨
	 * 
	 * @Param LoginHandle ��¼���
	 * @Param Channel ͨ���ţ���0��ʼ
	 * @Param Speed �ٶ� 1��10
	 * @Param Action ��̨�������ϡ��¡����ҵ�
	 * @Param Value 0 ��ʼ���� 1 ֹͣ�� ����ֵ��δ����
	 * @Return �ɹ�ʧ�ܣ�ʧ��ԭ��ͨ��Ϊû�е�¼���豸
	 */
	public native boolean ctrlPTZ(int LoginHandle, int Channel, int Speed,
			int Action, int Value);

	/**
	 * ѡ��Ԥ�õ�
	 * 
	 * @Param LoginHandle ��¼���
	 * @Param Channel ͨ���ţ���0��ʼ
	 * @Param Index Ԥ�õ�����ţ���1��ʼ
	 * @Return �ɹ�ʧ�ܣ�ʧ��ԭ��ͨ��Ϊû�е�¼���豸
	 */
	public native boolean selectPrePoint(int LoginHandle, int Channel, int Index);
	
	public native boolean deletePrePoint(int LoginHandle, int Channel, int Index);
	
	public native boolean setPrePoint(int LoginHandle, int Channel, int Index);
	

	/**
	 * ѡ��Ԥ�õ�
	 * 
	 * @Param LoginHandle ��¼���
	 * @Param Channel ͨ���ţ���0��ʼ
	 * @Param Index Ԥ�õ�����ţ���1��ʼ
	 * @Return �ɹ�ʧ�ܣ�ʧ��ԭ��ͨ��Ϊû�е�¼���豸
	 */
	public native boolean setOSD(int LoginHandle, int Channel,
			boolean ShowTime, boolean ShowOsd, int X, int Y, String OSD);

	/**
	 * �豸�Ƿ�֧���Զ����ӹ��?ĳЩ���̵��豸֧���Զ����ӹ��?�����ӶϿ���SDK��֪ͨӦ�ò㣬���Զ������豸��
	 * ���ӳɹ���֪ͨӦ�����ӳɹ����Ը����͵��豸����ҪӦ�ò�������ӡ�
	 * 
	 * ע�⣺�����豸5���������豸�ᱣ�ֻỰID����¼�����Ȼ��Ч��������֪ͨӦ�ò㣬5���Ӻ�ʧЧ����˿�����Ϊ ������֧�������Զ����?
	 * 
	 * @Param LoginHandle ��¼���
	 * @Return true ֧��
	 */
	public native boolean AutoConnectionManage(int LoginHandle);

	/**
	 * ��ȡͼ�����
	 * 
	 * @Param LoginHandle ��¼���
	 * @Param Channel ͨ���ţ���0��ʼ
	 * @Param VideoEffect ���������Ƶ����ֵ�������ȡ��Աȶȡ�ɫ�ȡ����Ͷȣ����μ�VideoEffect����
	 */
	public native boolean getVideoEffect(int LoginHandle, int Channel,
			VideoEffect Effect);

	/**
	 * ��ȡ�������
	 * 
	 * @Param LoginHandle ��¼���
	 * @Param Channel ͨ���ţ���0��ʼ
	 * @Param VideoCompress �������������ֵ����(���ʡ����ʡ�ͼ������)���μ�VideoCompress����
	 */
	public native boolean getVideoCompress(int LoginHandle, int Channel,
			VideoCompress Compress);
	
	
	
	public native boolean  MakeKeyFrame(int LoginHandle,int nChannel, int nCodeStreamType);

	/**
	 * DeviceCtrlΪ�������
	 * 
	 * @Return ����DeviceCtrlʵ��
	 */
	public static DeviceCtrl Instance() {
		if (_this == null) {
			synchronized (DeviceCtrl.class) {
				if (_this == null)
					_this = new DeviceCtrl();
			}
		}

		return _this;
	}

	// test
	@SuppressWarnings("static-access")
	public static void main(String[] argv) {

		DeviceCtrl dc = DeviceCtrl.Instance();
	    System.out.println(dc.startup()); 
		System.out.println(222);
		if(argv.length!=1) {
			System.out.println("no arg!!!");
			System.exit(1);
		}
		String ip = argv[0];
		System.out.println("IP : "+ip);
		
		int i = dc.login(ip, 8000, "admin", "12345", "");
System.out.println(i);
		System.out.println(dc.setTime(i, 2017, 03, 12, 3, 3, 3));
//
//		// dc.addListener(null);
//		// dc.startListen(0, "192.168.1.108", 8001);
//		//
//		VideoEffect s = new VideoEffect();
//
//		dc.getVideoEffect(0, 1, s);
//
//		System.out.println(s.getHue());
//		System.out.println(s.getContrast());
//
//		VideoCompress b = new VideoCompress();
//		dc.getVideoCompress(0, 1, b);
//		System.out.println(b.getPictureSize());

	}

}
