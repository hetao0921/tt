package com.fxdigital.storage.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxdigital.storage.app.impl.AppServer;
import com.fxdigital.storage.base.FxrstpCtrl;
import com.fxdigital.storage.service.parameters.RstpPara;
import com.fxdigital.syncclient.bean.NvmpRecordFile;
import com.fxdigital.syncclient.dao.NvmpRecordBaseDao;
import com.fxdigital.syncclient.dao.NvmpRecordFileDao;
import com.fxdigital.syncclient.util.VideoConverter;

@Service
public class OnTimeTask implements Runnable{

	private static final Logger logger = Logger.getLogger(OnTimeTask.class);
	
	private AppServer appServer;
	
	@Autowired
	private NvmpRecordBaseDao nvmpRecordBaseDao;

	@Autowired
	private NvmpRecordFileDao nvmpRecordFileDao;

	@Autowired
	private FxrstpCtrl fxrstpCtrl;

	@Autowired
	private RstpPara rstpPara;
	
	@Autowired
	private HintTask hintTask;
	
	@Autowired
	private SetCapacityService setCapacityService;

	public boolean isOnTheTime() {
		Date d = new Date();
		SimpleDateFormat myFmt = new SimpleDateFormat("mmss");
		String mmss = myFmt.format(d);
		return "0000".equals(mmss);
	}

	public void regApp(AppServer appServer){
		this.appServer=appServer;	
	}

	// 整点时处理
	public int onTheTimeAction() {
		logger.info("整点处理文件，当前整点时间 "
				+ new Timestamp(System.currentTimeMillis()));
		List<Object> nvmpRecordFileList = nvmpRecordFileDao.queryAllObject();
		for (Object object : nvmpRecordFileList) {
			NvmpRecordFile nvmpRecordFile = (NvmpRecordFile) object;
			Timestamp fileEndTime = nvmpRecordFile.getNvmpEndTime();
			int currentFileID = nvmpRecordFile.getNvmpFileId();
			// 当前没有停止的录像
			if (null == fileEndTime) {
				String nvmpBaseUUId = nvmpRecordFile.getNvmpBaseUuid();
				int olderHandler = nvmpRecordFile.getNvmpFileHandler();
				String oldFileName=nvmpRecordFile.getNvmpFileName();
				logger.info("当前整点需要换文件的录像nvmpBaseUUId为 " + nvmpBaseUUId);
				HashMap<String, String> nvmpRecordBaseMap = startNewRecord(nvmpBaseUUId);
				logger.info("开始一个新录像文件"+nvmpRecordBaseMap);
				if (null != nvmpRecordBaseMap) {
					// 开始一个新录像文件
					logger.info("开始一个新录像文件");
					String rtspURL = nvmpRecordBaseMap.get("nvmprtspurl");
					String fileName = VideoConverter.getFileName();
					logger.info("开始一个新录像rtspURL流为： " + rtspURL+" 名称为： "+fileName);
					fxrstpCtrl.Init();
					int newHandler = fxrstpCtrl.StartRecord(rtspURL, fileName);
					if (newHandler > 0) {
						saveNewRecordFile(nvmpBaseUUId, fileName, newHandler);
					} else {
						logger.info("播放" + rtspURL + "失败");
						//return -1;
					}
					// 结束旧的录像文件
					// int olderHandler = putrRtpPara(rtspURL, newHandler);
					logger.info("结束旧的录像文件，句柄为: "+olderHandler+"文件名为： "+oldFileName);
					boolean stopFlag = stopOldRecord(olderHandler);
					if (stopFlag) {
						updateOldRecord(currentFileID);
						
//						String convertResult=VideoConverter.Convert(oldFileName);
//						logger.info("转换文件返回信息:"+convertResult);
//						nvmpRecordFileDao.updateHintFlag(currentFileID);
						
						
						hintTask.init(oldFileName,currentFileID);
						hintTask.getAppServer().hintEvent(hintTask);
						
						
					} else {
						logger.info("停止" + rtspURL + "失败");
						//return -2;
					}
				} else {
					logger.info("当前文件没有对应的录像");
				}
			}
		}
		//调用底层播放之前先判断当前视频容量是否够用，如果够用，则不做任何事，如果不够用，则删除时间最远的
		setCapacityService.getEnoughCapacity();
		return 0;
	}
	


	// 更新rstpurl对应的handler
	public int putrRtpPara(String rtspURL, int newHandler) {
		int handler = -1;
		ConcurrentLinkedQueue<RstpPara> rstpParaQueue = rstpPara
				.getRstpParaQueue();
		for (RstpPara rstpPara : rstpParaQueue) {
			if (rtspURL.equals(rstpPara.getRstpurl())) {
				handler = rstpPara.getHandler().get();
				rstpPara.setHandler(new AtomicInteger(newHandler));
			}
		}
		return handler;
	}

	/*
	 * 
	 */
	public HashMap<String, String> startNewRecord(String nvmpBaseId) {
		// 开始新录像
		List<HashMap<String, String>> nvmpRecordBaseList = nvmpRecordBaseDao
				.getnvmpRecordBaseList(nvmpBaseId);
		HashMap<String, String> nvmpRecordBaseMap = null;
		if (null != nvmpRecordBaseList && nvmpRecordBaseList.size() > 0) {
			nvmpRecordBaseMap = nvmpRecordBaseList.get(0);
		}

		return nvmpRecordBaseMap;
	}

	// 保存新的录像文件信息
	public void saveNewRecordFile(String nvmpBaseUUId, String fileName,
			int newHandler) {
		NvmpRecordFile newRecordFile = new NvmpRecordFile();
		newRecordFile.setNvmpBaseUuid(nvmpBaseUUId);
		newRecordFile.setNvmpFileName(fileName);
		newRecordFile
				.setNvmpStartTime(new Timestamp(System.currentTimeMillis()));
		newRecordFile.setNvmpFileHandler(newHandler);
		newRecordFile.setNvmpFileHint(false);
		nvmpRecordFileDao.save(newRecordFile);
	}

	public boolean stopOldRecord(int handler) {
		// 结束当前录像
		boolean stopFlag = fxrstpCtrl.StopRecord(handler);
		return stopFlag;
	}

	public int updateOldRecord(int currentFileID) {
		Timestamp nowTime = new Timestamp(System.currentTimeMillis());
		return nvmpRecordFileDao.updateEndTime(currentFileID, nowTime);
	}

	public static void main(String[] args) {
		OnTimeTask onTimeService = new OnTimeTask();
		System.out.println(onTimeService.isOnTheTime());
	}



	@Override
	public void run() {
		logger.info("整点处理文件，当前整点时间 "
				+ new Timestamp(System.currentTimeMillis()));
		int returnFlag=onTheTimeAction();
		logger.info("整点处理文件返回状态 ："+returnFlag);
		if(returnFlag>=0){
			appServer.initTimeTask();
		}
		
		
	}

}
