package com.fxdigital.EcDevice.ctrl;
 
public interface IDevNotify
{
    /**
	 *	����֪ͨ
	 * 
	 *	@Param LoginHandle ��¼���
	 *	@Param AlarmType �������ͣ��μ�AlarmType����
	 *	@Param Value     ����ֵ��0 ���� 1 ���� 2 ���� ����ֵ��δ����
	 *	@Param Context   �û������ģ���login��¼�豸ʱ���� 
	 */
	void handleAlarm(int LoginHandle, int AlarmType, int Value, Object Context);

    /**
	 *	�����豸֪ͨ��ͨ����long�ɹ�������ĳЩ�豸�����ṩ�������߹��?��,
	 *	ʹ�ó����ṩ���豸�����߹��?��
	 * 
	 *	@Param DeviceIp  �豸Ip
	 *	@Param AlarmType �������ͣ��μ�AlarmType����
	 *	@Param Connected ���ӳɹ����Ͽ�
	 *	@Param Context   �û������ģ���login��¼�豸ʱ���� 
	 */
    void handleConnect(int LoginHandle, boolean Connected, Object Context);
}
