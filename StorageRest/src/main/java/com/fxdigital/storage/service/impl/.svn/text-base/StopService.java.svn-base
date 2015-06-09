package com.fxdigital.storage.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import com.fxdigital.storage.app.impl.NIOReactor;
import com.fxdigital.storage.base.FxrstpCtrl;
import com.fxdigital.storage.service.RecordService;
import com.fxdigital.storage.service.parameters.RstpPara;
import com.fxdigital.syncclient.bean.NvmpRecordFile;
import com.fxdigital.syncclient.dao.NvmpRecordBaseDao;
import com.fxdigital.syncclient.dao.NvmpRecordFileDao;
import com.fxdigital.syncclient.dao.NvmpRecordMarkDao;

@Service
@DependsOn("nvmpRecordFileDao")
public class StopService implements RecordService {

	private static final Logger logger = Logger.getLogger(StopService.class);

	@Autowired
	private HintTask hintTask;
	
	@Autowired
	private FxrstpCtrl fxrstpCtrl;

	@Autowired
	private RstpPara rstpPara;

	@Autowired
	private NvmpRecordMarkDao nvmpRecordMarkDao;

	@Autowired
	private NvmpRecordBaseDao nvmpRecordBaseDao;

	@Autowired
	private NvmpRecordFileDao nvmpRecordFileDao;
	
	private NIOReactor hintReactor=null;
	
	public StopService() {
		logger.info("加载StopService");
	}

	/**
	 * 停止录像业务
	 * 
	 * @param operateQueue
	 */
	public void stop(
			ConcurrentLinkedQueue<ConcurrentMap<String, String>> operateQueue) {
		for (ConcurrentMap<String, String> operateMap : operateQueue) {
			this.hintReactor=hintReactor;
			operateMap = operateQueue.poll();
			logger.info("operateMap stop " + operateMap);
			
			String markUUid = operateMap.get("markUUid");
			int stopHandler = -1;
			RstpPara rstpParaStop = null;	
			int result = nvmpRecordMarkDao.updateEndTime(markUUid,
					new Timestamp(System.currentTimeMillis()));
			if (result > 0) {
				String baseUUID = isStop(markUUid);
				// 当前视频需要关闭
				if (null != baseUUID) {
					//获取当前需要停止的rstp信息
					rstpParaStop = getStopPara(baseUUID);
					//得到所有文件信息
					List<HashMap<String, String>> list = nvmpRecordFileDao
							.getNvmpRecordFileList(baseUUID);
					Map<String, String> fileMap = new HashMap<String, String>();
					for (HashMap<String, String> tempMap : list) {
						if (null == tempMap.get("nvmpEndTime")
								|| ("").equals(tempMap.get("nvmpEndTime"))) {
							fileMap = tempMap;
						}
					}
					if (null != fileMap) {
						int handler = Integer.valueOf(fileMap
								.get("nvmpFileHandler"));
						int fileId = Integer.valueOf(fileMap.get("nvmpFileId"));
						String filePath=fileMap.get("nvmpFileName");
						boolean stopFlag = fxrstpCtrl.StopRecord(handler);
						if (stopFlag) {
							// 关闭视频对应文件
							stopFileRecord(fileId);
							//进行文件转换
//							String convertResult=VideoConverter.Convert(filePath);
//							logger.info("转换文件返回信息:"+convertResult);
//							nvmpRecordFileDao.updateHintFlag(fileId);
							logger.info("开始进行文件转换 ,文件名： "+filePath+"文件ID ："+fileId);
							hintTask.init(filePath,fileId);
							hintTask.getAppServer().hintEvent(hintTask);
							logger.info("结束进行文件转换 ,文件名： "+filePath+"文件ID ："+fileId);
							// 关闭base
							stopBaseRecord(baseUUID);
							logger.info("停止前还有录像列表："
									+ rstpPara.getRstpParaQueue() + "   "
									+ rstpParaStop);
							rstpPara.getRstpParaQueue().remove(rstpParaStop);
							logger.info("停止后还有录像列表："
									+ rstpPara.getRstpParaQueue());
						} else {
							logger.info("当前文件ID" + fileId + "关闭失败");
						}
					} else {
						logger.info("当前录像视频已经关闭");
					}

				} else {
					logger.info("当前录像视频还有录像");
				}
			}

		}

	}

	/**
	 * 获取当前需要停止的rstp信息
	 * @param baseUUID
	 * @return
	 */
	public RstpPara getStopPara(String baseUUID){
		RstpPara rstpParaStop=null;
		List<HashMap<String, String>> baseList = nvmpRecordBaseDao
				.getnvmpRecordBaseList(baseUUID);
        if(null!=baseList&&baseList.size()>0){
        	String rstpurl = baseList.get(0).get("nvmpRtspUrl");
        	for (RstpPara rstpParaTemp : rstpPara.getRstpParaQueue()) {
				if (rstpurl.equals(rstpParaTemp.getRstpurl())) {
					rstpParaStop = rstpParaTemp;
				}
			}
        }
        return rstpParaStop;
	} 
	
	
	/**
	 * 判断关闭后当前录像是否停止 如果当前录像停止，则返回录像的baseUUID 如果当前录像未停止，则返回null
	 * 
	 * @param markUUid
	 * @return
	 */
	public String isStop(String markUUid) {
		String result = null;
		List<HashMap<String, String>> nvmpRecordMarkList = nvmpRecordMarkDao
				.getnvmpRecordMarkList(markUUid);
		if (null != nvmpRecordMarkList && nvmpRecordMarkList.size() > 0) {
			HashMap<String, String> nvmpRecordMarkMap = nvmpRecordMarkList
					.get(0);
			String baseUUId = nvmpRecordMarkMap.get("nvmpBaseUuid");
			List<HashMap<String, String>> nvmpMarkList = nvmpRecordMarkDao
					.getnvmpMarkList(baseUUId);
			boolean flag = true;
			for (HashMap<String, String> map : nvmpMarkList) {
				// 调用判断录像停止函数
				String markUUID = map.get("nvmpMarkUuid");
				if (!isRecordOver(markUUID)) {
					flag = false;
				}
			}
			if (flag == true) {
				result = baseUUId;
			}

		}
		return result;
	}

	/**
	 * 判断录像是否停止,判断逻辑:end_time是否为空;
	 * 
	 * @param recordID
	 * @return
	 */
	public boolean isRecordOver(String recordID) {
		HashMap<String, String> temMap = null;
		boolean flag = false;
		List<HashMap<String, String>> list = nvmpRecordMarkDao
				.getnvmpRecordMarkList(recordID);
		if (list != null) {
			temMap = list.get(0);
			logger.info("temMap.get(nvmpEndTime)" + temMap.get("nvmpEndTime"));
			if (null != temMap.get("nvmpEndTime")
					&& !("").equals(temMap.get("nvmpEndTime"))) {
				flag = true;
			} else {
				flag = false;
			}
		}
		return flag;
	}

	/**
	 * 停止录像主表
	 * 
	 * @param baseUUId
	 * @return
	 */
	public int stopBaseRecord(String baseUUId) {
		Timestamp nowTime = new Timestamp(System.currentTimeMillis());
		return nvmpRecordBaseDao.updateBaseRecord(baseUUId, nowTime);
	}

	/**
	 * 停止录像文件
	 * 
	 * @param fileId
	 * @return
	 */
	public int stopFileRecord(int fileId) {
		Timestamp nowTime = new Timestamp(System.currentTimeMillis());
		return nvmpRecordFileDao.updateEndTime(fileId, nowTime);
	}

	/**
	 * 初始化 服务启动时，关闭上一次服务器关闭时未正常关闭的录像
	 * 
	 * @return
	 */
	public int initStopFile() {
		logger.info("开始进行文件初始化"+nvmpRecordFileDao);
		int result=-1;
		List<Object> stopFileList = nvmpRecordFileDao.queryAllObject();
		logger.info("stopFileList"+stopFileList.size());
		for (Object object : stopFileList) {
			NvmpRecordFile nvmpRecordFile = (NvmpRecordFile) object;
			Timestamp fileEndTime = nvmpRecordFile.getNvmpEndTime();
			if (null == fileEndTime ) {
				int fileId = nvmpRecordFile.getNvmpFileId();
				int fileHandler = nvmpRecordFile.getNvmpFileHandler();
				String fileName = nvmpRecordFile.getNvmpFileName();
				
				boolean stopFlag = fxrstpCtrl.StopRecord(fileHandler);
				if (stopFlag) {
					// 停止成功
					// 修改损坏的文件
					boolean repairFlag = fxrstpCtrl.RepairFile(fileName);
					logger.info("初始化时修复文件"+fileName+"结果： "+repairFlag);
					stopFileRecord(fileId);
					
//					String convertResult=VideoConverter.Convert(fileName);
//					logger.info("转换文件返回信息:"+convertResult);
//					nvmpRecordFileDao.updateHintFlag(fileId);
					
					hintTask.init(fileName,fileId);
					hintTask.getAppServer().hintEvent(hintTask);
					
					
					// 停止base
					List<HashMap<String, String>> baseList = nvmpRecordFileDao
							.getNvmpRecordFileBaseID(fileId);
					String baseUUID = null;
					List<HashMap<String, String>> markList = null;
					if (null != baseList && baseList.size() > 0) {
						baseUUID = baseList.get(0).get("nvmpbaseuuid");
					}
					if (null != baseUUID) {
						nvmpRecordBaseDao.updateBaseRecord(baseUUID,
								new Timestamp(System.currentTimeMillis()));
						markList = nvmpRecordMarkDao.getnvmpMarkList(baseUUID);
					}
					// 停止mark
					if(null!=markList&&markList.size()>0){
						for (HashMap<String, String> markMap : markList) {
							String markUUID = markMap.get("nvmpmarkuuid");
							nvmpRecordMarkDao.updateEndTime(markUUID, new Timestamp(System.currentTimeMillis()));
						}
					}
					result=1;
				}else{
					logger.info("初始化时关闭文件"+fileName+"失败");
				}

			}

		}
		logger.info("结束文件初始化操作"+result);
		return result;
	}
}
