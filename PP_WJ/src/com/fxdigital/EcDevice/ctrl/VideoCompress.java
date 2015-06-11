package com.fxdigital.EcDevice.ctrl;

public class VideoCompress 
{
    public static final int FRAMERATE     = 5; // ����
	public static final int CONST_BITRATE = 6; // ������	
	public static final int VAR_BITRATE   = 7; // ������

	// ͼ���С����
	public static final int PIC_QCIF  = 1;
	public static final int PIC_CIF   = 2;
	public static final int PIC_DCIF  = 3;
	public static final int PIC_4CIF  = 4;
	public static final int PIC_D1    = 5;
	public static final int PIC_VGA   = 6;
	public static final int PIC_SVGA  = 7;
	public static final int PIC_720P  = 8;
	public static final int PIC_720I  = 9;
	public static final int PIC_1080P = 10;
	public static final int PIC_1080I = 11;

	// �������Ͷ���
	public static final int CODEC_MPEG4 = 1;
	public static final int CODEC_H264  = 2;
	public static final int CODEC_MPEG2 = 3;

	

	public int     Framerate;      // ����
	public int     Bitrate;        // ����
	public int     Quality;        // ͼ������
	public boolean ConstBitrate;   // �Ƿ�Ϊ�����ʡ��������
	public int     PictureSize;    // ͼ���С
	public int     Codec;          // ��������
	public int getCodec() {
		return Codec;
	}

	public void setCodec(int codec) {
		Codec = codec;
	}
	
	public int IFrameInterval;
	
	public int getIFrameInterval() {
		return IFrameInterval;
	}
	
	public void setIFrameInterval(int value) {
		IFrameInterval = value;
	}

	public boolean StdCodec;       // �Ƿ�Ϊ��׼���롢����˽��

	public int getPictureSize()
	{
		return PictureSize;
	}

	public void setPictureSize(int Value)
	{
		PictureSize = Value;
	}

	public boolean getStdCodec()
	{
		return StdCodec;
	}

	public void setStdCodec(boolean Value)
	{
		StdCodec = Value;
	}

	public boolean getConstBitrate()
	{
		return ConstBitrate;
	}

	public void setConstBitrate(boolean Value)
	{
		ConstBitrate = Value;
	}


	public int getFramerate()
	{
		return Framerate;
	}

	public int getBitrate()
	{
		return Bitrate;
	}

	public int getQuality()
	{
		return Quality;
	}

	public void setFramerate(int Value)
	{
		Framerate = Value;
	}

	public void setBitrate(int Value)
	{
		Bitrate = Value;
	}

	public void setQuality(int Value)
	{
		Quality = Value;
	}
	

}
