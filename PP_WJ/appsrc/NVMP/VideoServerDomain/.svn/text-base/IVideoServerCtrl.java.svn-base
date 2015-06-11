package NVMP.VideoServerDomain;

import org.misc.log.LogUtil;

import NVMP.AppService.Remoting;

@Remoting
public class IVideoServerCtrl {
	/**
	 * 告知控制的句柄为多少。
	 * 
	 * @param context
	 * */
	public void onServerStart(String nInstance, String context) {
	}

	/**
	 * 告知通道数量为多少。
	 * */
	public void onGetChannelNumbers(Integer nums,String context) {
	} 

	/**
	 * 告知通道呈现的坐标值和内容。
	 * */
	public void onGetChannelName(Integer posX, Integer getnPosY, String szName,Integer channel,String context) {
	}

	/**
	 * 告知串行端口的参数信息。
	 * */
	public void onGetSerialPortParam(Integer getnAddress, Integer getnBaudRate,
			Integer getnDataBit, Integer getnParity, Integer getnPTZProtocol,
			Integer getnStopBit,Integer channel,String context) {
	}

	/**
	 * 告知图像参数信息。
	 * */
	public void onGetVideoEffect(Integer m_nBirghtness, Integer m_nContrast,
			Integer m_nHue, Integer m_nSaturation,Integer channel,String context) {
	}

	/**
	 * 告知编码参数
	 * */
	public void onGetVideoCompress(Integer m_nBitrate, Integer m_nCodecType,
			Integer m_nFramerate, Integer m_nQuality, Integer m_nResolution,Integer channel,String context) {
	}

	//获得osd值

	public void onGetOSD(Integer posX, Integer posY, Boolean showOSD, Boolean showTime,
			String oSDContent, Integer nChannel, String context) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	
	
	////////////////////////////告知解码卡的信息////////////////////////////
	////////////////////////////gjj 2012.10.19/////////////////////////////
	/**
	 * 获取硬件初始化信息
	 */
	public void onGetHardwareInitInfo(Integer _succeedDecodeChannelCount,
			Integer _succeedInitDspCount, Integer _succeedDisplayChannelCount,
			Integer _decodeCardCount,String context) {
		LogUtil.info("高江江高江江：这里是onGetHardwareInitInfo()");
	}
	
	/**
	 * 
	 * @param nDisplayIndex  电视索引 ,即显示通道索引 (索引从0开始)
	 * @param nAreaIndex     显示通道中的分屏索引(即，对应当前电视的分屏索引，如9分屏)(索引从0开始)
	 * @return               返回当前显示通道所分配的解码器索引(索引从0开始)
	 */
	public void onGetDisplayRelatedDecodeIndex(Integer DisplayRelatedDecodeIndex,String context){
		LogUtil.info("高江江高江江：这里是onGetDisplayRelatedDecodeIndex()");
	}
	
	/**
	 * 
	 * @param nDecodeIndex  解码器索引(索引从0开始)
	 * @return  -1：代表getIsDecode()接口调用失败；
	 *           0：代表当前解码器没有解码；
	 *           1：代表当前解码器正在解码；
	 */
	public void onGetIsDecode(Integer IsDecode,String context){
		LogUtil.info("高江江高江江：这里是onGetIsDecode()");
	}
	
	/**
	 * 
	 * @param nDisplayIndex  显示通道索引(即，对应电视索引)(索引从0开始)
	 * @param nAreaIndex     显示通道中的分屏索引(即，对应当前电视的分屏索引，如9分屏)(索引从0开始)
	 * @return   -1：代表getIsTvAreaOut()接口调用失败；
	 *            0：代表当前解码通道的当前显示分区，没有显示输出
	 *            1：代表当前解码通道的当前显示分区，正在显示输出
	 */
	public void onGetIsTvAreaOut(Integer IsTvAreaOut,String context){
		LogUtil.info("高江江高江江：这里是onGetIsTvAreaOut()");
	}
	
	/**
	 * 
	 * @param nDisplayIndex  显示通道索引(即，对应电视索引)(索引从0开始)
	 * @return  -1：代表getIsTvOut()接口调用失败；
	 *           0：代表当前解码通道的当前显示分区，没有显示输出
	 *           1：代表当前解码通道的当前显示分区，正在显示输出
	 */
	public void onGetIsTvOut(Integer IsTvOut,String context){
		LogUtil.info("高江江高江江：这里是onGetIsTvOut()");
	}
	
	/**
	 * 
	 * @param nDisplayIndex  解码通道索引(即，对应电视索引)(索引从0开始)
	 * @return   -1:代表getTvAreaCount()接口调用失败；
	 *              其它数字代表当前显示通道的分屏数
	 */
	public void onGetTvSplitCount(Integer TvSplitCount,String context){
		LogUtil.info("高江江高江江：这里是onGetTvSplitCount()");
	}
	
	/**
	 * 
	 * @param nDisplayIndex  显示通道索引(索引从0开始)
	 * @param nArea          显示通道中的分屏索引(即，对应当前电视的分屏索引，如9分屏)(索引从0开始)
	 * @param vSrc           显示通道对应的通道源信息
	 * @return
	 */
	public void onGetVedioSource(String _sourceIp, Integer _sourcePort,
			Integer _sourceChannel, String context) {
		LogUtil.info("高江江高江江：这里是onGetVedioSource()");
	}
	
	
	
	
	/**
	 * 获取显示通道对应的信息
	 * @param nIsDisplayChannelPlay
	 * @param splitNum
	 * @param area0
	 * @param context
	 */
	public void onGetDisplayChannelInfo(Integer nIsDisplayChannelPlay,
			Integer splitNum, String area0,String context ) {
		LogUtil.info("高江江高江江：这里是onGetDisplayChannelInfo()");
	}
}
