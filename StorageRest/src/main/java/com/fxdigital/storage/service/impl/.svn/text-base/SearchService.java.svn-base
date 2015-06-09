package com.fxdigital.storage.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxdigital.search.bean.NvmpRecordBaseSearch;
import com.fxdigital.search.bean.NvmpRecordFileSearch;
import com.fxdigital.search.bean.NvmpRecordMarkSearch;
import com.fxdigital.search.bean.NvmpStorageInfo;
import com.fxdigital.storage.service.RecordService;
import com.fxdigital.syncclient.bean.NvmpRecordBase;
import com.fxdigital.syncclient.bean.NvmpRecordFile;
import com.fxdigital.syncclient.bean.NvmpRecordMark;
import com.fxdigital.syncclient.bean.NvmpRecordMarkParameter;
import com.fxdigital.syncclient.bean.NvmpRecordStorageInfo;
import com.fxdigital.syncclient.dao.NvmpRecordBaseDao;
import com.fxdigital.syncclient.dao.NvmpRecordFileDao;
import com.fxdigital.syncclient.dao.NvmpRecordMarkDao;
import com.fxdigital.syncclient.dao.NvmpRecordMarkParameterDao;
import com.fxdigital.syncclient.dao.NvmpRecordStorageInfoDao;

/**
 * 把请求数据存入nvmp_record_mark表
 * 
 * @author hxht
 */
@Service
public class SearchService implements RecordService {

	private static final Logger logger = Logger.getLogger(SearchService.class);

	@Autowired
	private StartService startService;

	@Autowired
	private NvmpRecordMarkParameterDao nvmpRecordMarkParameterDao;

	@Autowired
	private NvmpRecordMarkDao nvmpRecordMarkDao;

	@Autowired
	private NvmpRecordBaseDao nvmpRecordBaseDao;

	@Autowired
	private NvmpRecordFileDao nvmpRecordFileDao;

	@Autowired
	private NvmpRecordStorageInfoDao nvmpRecordStorageInfoDao;

	Timestamp mintime = null;
	Timestamp maxtime = null;

	/**
	 * 查询一条录像数据
	 * 
	 * @param name
	 * @return
	 */
	public List<NvmpRecordBaseSearch> search(String jsonpara) {
		logger.info("search" + jsonpara);
		// jsonpara =
		// "{'mark':{'a':1,'b':2,'c':3},'mintime':'2015-04-17 09:30:21.0','maxtime':'2015-04-17 09:37:21.0'}";
		JSONObject jsonObj = JSONObject.fromObject(jsonpara);

		mintime = startService.analyTime(jsonObj.get("mintime"));
		maxtime = startService.analyTime(jsonObj.get("maxtime"));

		List<NvmpRecordBaseSearch> tt = null;
		List<NvmpRecordMarkSearch> markResultList = null;
		List<Object> fileList = new ArrayList<Object>();
		List<Object> markList1 = new ArrayList<Object>();
		List<Object> baseList = new ArrayList<Object>();
		Map<String, Object> baseMap = new HashMap<String, Object>();
		List<Object> markParaList = new ArrayList<Object>();
		List<Map<String, Object>> markList = nvmpRecordMarkParameterDao
				.getnvmpRecordMarkParameterList(jsonObj);
		List<Object> basefileList = null;
		if (markList != null && markList.size() > 0) {
			for (Map m : markList) {
				String nvmpBaseUUId = (String) m.get("nvmp_base_uuid");
				String nvmpMarkUUId = (String) m.get("nvmp_mark_uuid");
				String fileSql = "from NvmpRecordFile where nvmpBaseUuid =? order by nvmpStartTime asc";
				String filePara = nvmpBaseUUId;
				List<Object> tempfileList = nvmpRecordFileDao.queryObjectList(
						fileSql, filePara);
				for (Object obj : tempfileList) {
					if(!fileList.contains(obj)){
						fileList.add(obj);
					}
					
				}
				String markSql = "from NvmpRecordMark where nvmpMarkUuid =?";
				String markPara = nvmpMarkUUId;
				List<Object> tempmarkList = nvmpRecordMarkDao.queryObjectList(
						markSql, markPara);
				if (null != tempmarkList && tempmarkList.size() > 0) {
					markList1.add(tempmarkList.get(0));
					NvmpRecordMark nvmpRecordMark = (NvmpRecordMark) markList1
							.get(0);
				}

				if (null == baseMap.get(nvmpBaseUUId)
						|| ("").equals(baseMap.get(nvmpBaseUUId))) {
					String baseSql = "from NvmpRecordBase where nvmpBaseUuid =?";
					String basePara = nvmpBaseUUId;
					List<Object> tempbaseList = nvmpRecordMarkDao
							.queryObjectList(baseSql, basePara);
					if (null != tempbaseList && tempbaseList.size() > 0) {
						baseList.add(tempbaseList.get(0));
						baseMap.put(nvmpBaseUUId, tempbaseList.get(0));
					}

				}

				String markParaSql = "from NvmpRecordMarkParameter where nvmpMarkUuid =?";
				List<Object> tempmarkParaList = nvmpRecordMarkParameterDao
						.queryObjectList(markParaSql, nvmpMarkUUId);
				for (Object obj : tempmarkParaList) {
					markParaList.add(obj);
				}

				if (null != baseList && baseList.size() > 0) {
					NvmpRecordBase nvmpRecordBase = (NvmpRecordBase) baseList
							.get(0);
					System.out.println(nvmpRecordBase.getNvmpRtspUrl());
					System.out.println("fileList:" + baseList);
				}

				// System.out.println("getList()之后的baselist:"+getList(baseList,fileList));

			}
			markResultList = getMarkSearch(markList1, markParaList);
			tt = getList(baseList, fileList, markResultList);
		}
		return tt;
	}

	/**
	 * 将表合并 参数表合并到mark表
	 * 
	 * @param markList
	 * @param markParaList
	 * @return
	 */
	public List<NvmpRecordMarkSearch> getMarkSearch(List<Object> markList,
			List<Object> markParaList) {
		List<NvmpRecordMarkSearch> nvmpRecordMarkSearchList = new ArrayList<NvmpRecordMarkSearch>();
		if (null != markList && markList.size() > 0) {
			for (int i = 0; i < markList.size(); i++) {
				NvmpRecordMark nvmpRecordMark = (NvmpRecordMark) markList
						.get(i);
				if (null != nvmpRecordMark.getNvmpEndTime()) {
					NvmpRecordMarkSearch nvmpRecordBaseSearch = getRecordMarkSearch(
							nvmpRecordMark, markParaList);
					nvmpRecordMarkSearchList.add(nvmpRecordBaseSearch);
				}

			}
			logger.info("getMarkSearch nvmpRecordMarkSearchList "
					+ nvmpRecordMarkSearchList.size()+" "+nvmpRecordMarkSearchList);
		}
		return nvmpRecordMarkSearchList;
	}

	/**
	 * 
	 * @param nvmpRecordMark
	 * @param markParaList
	 * @return
	 */
	public NvmpRecordMarkSearch getRecordMarkSearch(
			NvmpRecordMark nvmpRecordMark, List<Object> markParaList) {
		NvmpRecordMarkSearch nvmpRecordMarkSearch = new NvmpRecordMarkSearch();
		nvmpRecordMarkSearch.setNvmpBaseUuid(nvmpRecordMark.getNvmpBaseUuid());
		nvmpRecordMarkSearch.setNvmpEndTime(null == nvmpRecordMark
				.getNvmpEndTime() ? "" : nvmpRecordMark.getNvmpEndTime()
				.toString());
		nvmpRecordMarkSearch.setNvmpMarkName(nvmpRecordMark.getNvmpMarkName());
		nvmpRecordMarkSearch.setNvmpMarkUuid(nvmpRecordMark.getNvmpMarkUuid());
		nvmpRecordMarkSearch.setNvmpStartTime(null == nvmpRecordMark
				.getNvmpStartTime() ? "" : nvmpRecordMark.getNvmpStartTime()
				.toString());
		List<NvmpRecordMarkParameter> nvmpRecordMarkParameterList = new ArrayList<NvmpRecordMarkParameter>();
		for (Object item : markParaList) {
			NvmpRecordMarkParameter nvmpRecordMarkParameter = (NvmpRecordMarkParameter) item;
			if (nvmpRecordMark.getNvmpMarkUuid().equals(
					nvmpRecordMarkParameter.getNvmpMarkUuid())) {
				nvmpRecordMarkParameterList.add(nvmpRecordMarkParameter);
			}
		}
		nvmpRecordMarkSearch
				.setNvmpRecordMarkParameterList(nvmpRecordMarkParameterList);
		return nvmpRecordMarkSearch;
	}

	/**
	 * 当前录像时间是否在检索时间段内 三种情况： 开始时间在范围内 开始，结束均在范围内 结束时间在范围内 开始与开始相等或者结束与结束相等
	 * 
	 * @param nvmpStartTime
	 * @param nvmpEndTime
	 * @param mintime
	 * @param maxtime
	 * @return
	 */
	public boolean isInTime(Timestamp nvmpStartTime, Timestamp nvmpEndTime,
			Timestamp mintime, Timestamp maxtime) {
		boolean flag = false;
		if (mintime.before(nvmpStartTime) && maxtime.after(nvmpStartTime)) {
			flag = true;
		}
		if (nvmpEndTime != null) {
			if (mintime.before(nvmpStartTime) && maxtime.after(nvmpEndTime)) {
				flag = true;
			}
			if (mintime.before(nvmpEndTime) && maxtime.after(nvmpEndTime)) {
				flag = true;
			}
			if (nvmpStartTime.equals(mintime) || nvmpEndTime.equals(maxtime)) {
				flag = true;
			}
		}

		return flag;
	}

	/**
	 * 
	 * @param base
	 * @param fileList
	 * @return
	 */
	public NvmpRecordBaseSearch getRecordBaseSearch(NvmpRecordBase base,
			List<Object> fileList, List<NvmpRecordMarkSearch> markList) {
		NvmpRecordBaseSearch recordBaseSearch = new NvmpRecordBaseSearch();
		recordBaseSearch.setNvmpBaseUuid(base.getNvmpBaseUuid());
		recordBaseSearch.setNvmpIsRecording(base.getNvmpIsRecording());
		recordBaseSearch.setNvmpRtspUrl(base.getNvmpRtspUrl());
		recordBaseSearch.setNvmpStartTime(null == base.getNvmpStartTime() ? ""
				: base.getNvmpStartTime().toString());

		recordBaseSearch.setNvmpStopTime(null == base.getNvmpStopTime() ? ""
				: base.getNvmpStopTime().toString());
		List<NvmpRecordFileSearch> recordFileSearchList = new ArrayList<NvmpRecordFileSearch>();
		List<NvmpRecordMarkSearch> recordMarkSearchList = new ArrayList<NvmpRecordMarkSearch>();
		for (Object item : fileList) {
			NvmpRecordFile fileRecord = (NvmpRecordFile) item;
			NvmpRecordFileSearch nvmpRecordFileSearch = new NvmpRecordFileSearch();
			Timestamp nvmpStartTime = fileRecord.getNvmpStartTime();
			Timestamp nvmpEndTime = fileRecord.getNvmpEndTime();
			boolean flag = isInTime(nvmpStartTime, nvmpEndTime, mintime,
					maxtime);
			nvmpRecordFileSearch.setNvmpBaseUuid(fileRecord.getNvmpBaseUuid());
			nvmpRecordFileSearch.setNvmpChild(fileRecord.getNvmpChild());
			nvmpRecordFileSearch.setNvmpEndTime(null == fileRecord
					.getNvmpEndTime() ? "" : fileRecord.getNvmpEndTime()
					.toString());
			nvmpRecordFileSearch.setNvmpFileHandler(fileRecord
					.getNvmpFileHandler());
			nvmpRecordFileSearch.setNvmpFileId(fileRecord.getNvmpFileId());
			nvmpRecordFileSearch.setNvmpFileName(fileRecord.getNvmpFileName());
			nvmpRecordFileSearch.setNvmpStartTime(null == fileRecord
					.getNvmpStartTime() ? "" : fileRecord.getNvmpStartTime()
					.toString());
			if (recordBaseSearch.getNvmpBaseUuid().equals(
					fileRecord.getNvmpBaseUuid())
					&& flag) {
				recordFileSearchList.add(nvmpRecordFileSearch);
			}
		}
		for (Object item : markList) {
			NvmpRecordMarkSearch markRecord = (NvmpRecordMarkSearch) item;
			if (recordBaseSearch.getNvmpBaseUuid().equals(
					markRecord.getNvmpBaseUuid())) {
				recordMarkSearchList.add(markRecord);
			}
		}
		if (null != recordFileSearchList && recordFileSearchList.size() > 0) {
			recordBaseSearch.setNvmpRecordFileSearchList(recordFileSearchList);
			recordBaseSearch.setNvmpRecordMarkSearchList(recordMarkSearchList);
			NvmpStorageInfo nvmpStorageInfo = getNvmpStorageInfo();
			recordBaseSearch.setNvmpStorageInfo(nvmpStorageInfo);
		}

		return recordBaseSearch;
	}

	/**
	 * 获取存储服务器信息
	 * 
	 * @return
	 */
	public NvmpStorageInfo getNvmpStorageInfo() {
		NvmpStorageInfo nvmpStorageInfo = new NvmpStorageInfo();
		List<Object> nvmpRecordFileList = nvmpRecordStorageInfoDao
				.queryAllObject();
		for (Object object : nvmpRecordFileList) {
			NvmpRecordStorageInfo nvmpRecordStorageInfo = (NvmpRecordStorageInfo) object;
			nvmpStorageInfo.setNvmpPlayRtspUrl(nvmpRecordStorageInfo
					.getStoragertsp());
			nvmpStorageInfo.setStorageIP(nvmpRecordStorageInfo.getStorageip());
			nvmpStorageInfo.setStoragePort(nvmpRecordStorageInfo
					.getStorageport());
		}

		return nvmpStorageInfo;
	}

	/**
	 * 整理数据,将主表和从表合并
	 * 
	 * @param baseList
	 *            主表数据 录像表
	 * @param fileList
	 *            从表数据 文件表
	 * @return
	 */
	public List<NvmpRecordBaseSearch> getList(List<Object> baseList,
			List<Object> fileList, List<NvmpRecordMarkSearch> markList) {
		System.out.println(baseList);
		List<NvmpRecordBaseSearch> NvmpRecordBaseSearchList = new ArrayList();
		if (null != baseList && baseList.size() > 0) {
			for (int i = 0; i < baseList.size(); i++) {
				NvmpRecordBase nvmpRecordBase = (NvmpRecordBase) baseList
						.get(i);
				if (null != nvmpRecordBase.getNvmpStopTime()) {
					NvmpRecordBaseSearch nvmpRecordBaseSearch = getRecordBaseSearch(
							nvmpRecordBase, fileList, markList);
					// 如果没有符合条件的fileList，则整个的集合都不返回
					if (null != nvmpRecordBaseSearch
							.getNvmpRecordFileSearchList()
							&& nvmpRecordBaseSearch
									.getNvmpRecordFileSearchList().size() > 0) {
						NvmpRecordBaseSearchList.add(nvmpRecordBaseSearch);
					}

				}
			}
			logger.info("getList NvmpRecordBaseSearchList "
					+ NvmpRecordBaseSearchList.size()+" "+NvmpRecordBaseSearchList);
		}
	
		return NvmpRecordBaseSearchList;

	}

	/**
	 * 保存一条Mark数据
	 * 
	 * @param nvmpRecordMark
	 */
	public void saveMark(String baseUUid, String markUUid, String paraStr) {
		logger.info("saveMark:" + baseUUid + "," + markUUid);
		startService.saveNvmpRecordMark(baseUUid, markUUid, paraStr);

	}

}
