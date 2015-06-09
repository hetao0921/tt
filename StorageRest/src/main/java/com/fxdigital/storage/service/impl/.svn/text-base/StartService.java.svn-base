package com.fxdigital.storage.service.impl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxdigital.storage.base.FxrstpCtrl;
import com.fxdigital.storage.service.RecordService;
import com.fxdigital.storage.service.parameters.RstpPara;
import com.fxdigital.syncclient.bean.NvmpRecordBase;
import com.fxdigital.syncclient.bean.NvmpRecordFile;
import com.fxdigital.syncclient.bean.NvmpRecordMark;
import com.fxdigital.syncclient.bean.NvmpRecordMarkParameter;
import com.fxdigital.syncclient.dao.NvmpRecordBaseDao;
import com.fxdigital.syncclient.dao.NvmpRecordFileDao;
import com.fxdigital.syncclient.dao.NvmpRecordMarkDao;
import com.fxdigital.syncclient.dao.NvmpRecordMarkParameterDao;
import com.fxdigital.syncclient.util.VideoConverter;

@Service
public class StartService implements RecordService {

	private static final Logger logger = Logger.getLogger(StartService.class);

	@Autowired
	private FxrstpCtrl fxrstpCtrl;

	@Autowired
	private NvmpRecordBaseDao nvmpRecordBaseDao;

	@Autowired
	private NvmpRecordFileDao nvmpRecordFileDao;

	@Autowired
	private NvmpRecordMarkDao nvmpRecordMarkDao;

	@Autowired
	private RstpPara rstpPara;

	@Autowired
	private NvmpRecordMarkParameterDao nvmpRecordMarkParameterDao;
	
	@Autowired
	private SetCapacityService setCapacityService;

	/**
	 * 开始录像任务
	 * 
	 * @param operateQueue
	 */
	public void start(
			ConcurrentLinkedQueue<ConcurrentMap<String, String>> operateQueue) {
		for (ConcurrentMap<String, String> operateMap : operateQueue) {
			operateMap = operateQueue.poll();
			logger.info("operateMap start " + operateMap);

			String rstpurl = operateMap.get("rstpurl");
			logger.info("rstpurl " + rstpurl + " " + rstpPara.getRstpurl());
			String markUUid = operateMap.get("markUUid");
			// 保存相关的设置参数
			// 当前是否已经点播
			RstpPara rstpParaTemp = getCurrentRecord(rstpPara, rstpurl);
			if (null!=rstpParaTemp) {
				rstpParaTemp.setStart(new AtomicInteger(rstpParaTemp.getStart()
						.incrementAndGet()));
				saveNvmpRecordMark(rstpParaTemp.getBaseUUId(), markUUid,
						operateMap.get("mark"));
				// String json="{name:'Wallace',age:12}";
				String json = operateMap.get("mark");
				saveNvmpRecordMarkParameters(markUUid, json);
			} else {
				// 调用底层播放视频
				logger.info("开始调用底层视频播放");
				String fileName = VideoConverter.getFileName();

				int handler =-1;
				try{
				fxrstpCtrl.Init();
//				handler=1;
				 handler = fxrstpCtrl.StartRecord(rstpurl, fileName);
				 logger.info("rstpurl "+rstpurl+ " fileName: "+fileName+" handler: "+handler);
				}catch(Exception e){
					logger.info("调用底层播放视频出错"+rstpurl+" 错误原因:"+e);
				}
				logger.info("结束调用底层视频播放 "+(handler>0) );
//				fxrstpCtrl.Init();
//				int handler = fxrstpCtrl.StartRecord(rstpurl, fileName);
				if (handler > 0) {
					UUID uuid = UUID.randomUUID();
					String baseUUid = uuid.toString();
					RstpPara rstNewpPara=new RstpPara();
					ConcurrentLinkedQueue<RstpPara> rstpParaQueue = rstpPara
							.getRstpParaQueue();
					rstNewpPara.setRstpurl(rstpurl);
					rstNewpPara.setBaseUUId(baseUUid);
					rstNewpPara.setStart(new AtomicInteger(1));
					rstNewpPara.setHandler(new AtomicInteger(handler));
					rstpParaQueue.add(rstNewpPara);
					saveNvmpRecordBase(baseUUid, rstpurl);
					saveNvmpRecordFile(baseUUid, fileName, handler);
					saveNvmpRecordMark(baseUUid, markUUid,
							operateMap.get("mark"));
					String json = operateMap.get("mark");
					logger.info("mark "+json);
					saveNvmpRecordMarkParameters(markUUid, json);
				}
			}
            
			System.out.println("rstpPara " + rstpPara.getStart());
		}
		//调用底层播放之前先判断当前视频容量是否够用，如果够用，则不做任何事，如果不够用，则删除时间最远的
		setCapacityService.getEnoughCapacity();
	}

	/**
	 * 判断当前是否为新录像
	 * 
	 * @param rstpPara
	 * @param rstpurl
	 * @return
	 */
	public RstpPara getCurrentRecord(RstpPara rstpPara, String rstpurl) {
		RstpPara rstpParaResult = null;
		for (RstpPara rstpParaTemp : rstpPara.getRstpParaQueue()) {
			if (rstpurl.equals(rstpParaTemp.getRstpurl())) {
				rstpParaResult = rstpParaTemp;
			}
		}
		return rstpParaResult;
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
	 * 保存录像信息到数据库
	 * 
	 * @param uuid
	 * @param rstpurl
	 */
	public void saveNvmpRecordBase(String uuid, String rstpurl) {
		// 保存录像信息到数据库
		NvmpRecordBase nvmpRecordBase = new NvmpRecordBase();
		nvmpRecordBase.setNvmpBaseUuid(uuid);
		nvmpRecordBase.setNvmpIsRecording(true);
		nvmpRecordBase.setNvmpRtspUrl(rstpurl);
		nvmpRecordBase.setNvmpStartTime(new Timestamp(System
				.currentTimeMillis()));
		nvmpRecordBaseDao.saveNvmpRecordBase(nvmpRecordBase);
	}

	/**
	 * 保存文件信息到数据库
	 * 
	 * @param uuid
	 * @param fileName
	 * @param handler
	 */
	public void saveNvmpRecordFile(String uuid, String fileName, int handler) {
		// 保存文件信息到数据库
		NvmpRecordFile nvmpRecordFile = new NvmpRecordFile();
		nvmpRecordFile.setNvmpBaseUuid(uuid);
		nvmpRecordFile.setNvmpStartTime(new Timestamp(System
				.currentTimeMillis()));
		nvmpRecordFile.setNvmpFileHandler(handler);
		nvmpRecordFile.setNvmpFileName(fileName);
		nvmpRecordFile.setNvmpFileHint(false);
		nvmpRecordFileDao.saveNvmpRecordFile(nvmpRecordFile);
	}

	/**
	 * 保存标记信息
	 * 
	 * @param baseUUid
	 * @param markUUid
	 * @param paraStr
	 */
	public void saveNvmpRecordMark(String baseUUid, String markUUid,
			String paraStr) {
		// 保存标志信息到数据库
		NvmpRecordMark nvmpRecordMark = new NvmpRecordMark();
		nvmpRecordMark.setNvmpBaseUuid(baseUUid);
		nvmpRecordMark.setNvmpStartTime(new Timestamp(System
				.currentTimeMillis()));
		nvmpRecordMark.setNvmpMarkUuid(markUUid);
		nvmpRecordMark.setNvmpMarkName("markName");
		nvmpRecordMarkDao.insert(nvmpRecordMark);

	}

	/**
	 * 保存标记参数信息
	 * 
	 * @param markUUid
	 * @param paraStr
	 */
	public void saveNvmpRecordMarkParameters(String markUUid, String paraStr) {


		List<String> list = analyJsonStr(paraStr);
		
		for(int i=0;i<=list.size()-1;i+=2){
			NvmpRecordMarkParameter nvmpRecordMarkParameter = new NvmpRecordMarkParameter();
			String parameterKey = list.get(i);
			String parameterValue = list.get(i+1);
			nvmpRecordMarkParameter.setNvmpMarkUuid(markUUid);
			nvmpRecordMarkParameter.setParameterKey(parameterKey);
			nvmpRecordMarkParameter.setParameterValue(parameterValue);
			nvmpRecordMarkParameterDao.insert(nvmpRecordMarkParameter);
		}
	}

	/**
	 * 解析字符串paraStr
	 * @param paraStr
	 * @return
	 */
	public List<String> analyJsonStr(String paraStr) {
		
		System.out.println("JSON字符串：" + paraStr);
		JSONObject jsonObj = JSONObject.fromObject(paraStr);
		List<String> list = new ArrayList<String>();
//		JSONObject jsonObj1 = (JSONObject) jsonObj.get("mark");
		for (Iterator iter = jsonObj.keys(); iter.hasNext();) {		
			String parameter_key = (String) iter.next();
			list.add(parameter_key);
			String parameter_value = jsonObj.getString(parameter_key);
			list.add(parameter_value);
//			 System.out.println(parameter_key+":"+parameter_value);
			logger.info("list "+list);
				
		}		
		return list;
	}
	
	/**
	 * 时间解析
	 * @param object
	 * @return
	 */
	public Timestamp analyTime(Object object){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  format.setLenient(false);
		  Timestamp ts = null;
		  //要转换字符串 str_test
		  try {
		    ts= new Timestamp(format.parse((String) object).getTime());
		   System.out.println(ts.toString());
		  } catch (ParseException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  }
		  return ts;
	}

}
