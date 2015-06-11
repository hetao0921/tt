package com.fxdigital.EcDevice.ctrl;

import java.util.LinkedList;
import java.util.List;

import com.sqlite.dao.CenterNetWorkDao;
import com.sqlite.impl.CenterNetWorkImpl;
import com.sqlite.pojo.CenterNetWork;

public class tt {
	private List<String> GetVideoPath(String FromCenterId,String DestCenterId)
    {
		CenterNetWorkDao netDao = new CenterNetWorkImpl() ;
		List<CenterNetWork> list = netDao.getAllCenterNetWork();
		
        List<String> path1 = new LinkedList<String>();
        path1.add(FromCenterId);

        List<String> path2 = new LinkedList<String>();
        path2.add(DestCenterId);
       
        if (FromCenterId.equals(DestCenterId))
        {
            return path1;
        }

       
        String ParentId1 = FromCenterId;
        String ParentId2 = DestCenterId;
      

        for (int i = 0; i < 16; i++)
        {
        	for(CenterNetWork cw:list)
            {
                if (cw.getCenterId().equals(ParentId1))
                {
                    path1.add(cw.getNetWorkNodeID());
                    ParentId1 = cw.getNetWorkNodeID();
                    if (DestCenterId.equals(ParentId1))
                    {
                        return path1;
                    }
                }

                if (cw.getCenterId().equals(ParentId2))
                {
                    path2.add(cw.getNetWorkNodeID());
                    ParentId2 = cw.getNetWorkNodeID();
                    if (FromCenterId.equals(ParentId2))
                    {
                        return path2;
                    }
                }
            }
        }

        
        int n = 0;
        Boolean bFlag = false;

        List<String> path3 = new LinkedList<String>();
        for (int m = 0; m < path1.size(); m++)
        {
        	String s1 = path1.get(m);
            path3.add(s1);
            for (n = 0; n < path2.size(); n++)
            {
            	String s2 = path2.get(n);
                if (s1.equals(s2))
                {
                    bFlag = true;
                    break;
                }
            }
            if (bFlag) break;
        }
        if (bFlag)
        {
            for (int i = n -1; i >= 0; i--)
            {
            	String s2 = path2.get(n);
                 path3.add(s2);
            }
            return path3;
        }
        else
        {
            path1.add(DestCenterId);
            return path1;
        }
    }

    public String GetVideoPathUrl(String FromCenterId,String DestCenterId)
    {
        List<String> path;
        path = GetVideoPath(DestCenterId,DestCenterId);
        String s = "";
        for (int i = path.size() - 1; i >= 0; i--)
        {
        	String s2 = path.get(i);
            s = s + s2;
            if (i != 0)
            {
                s = s + ",";
            }
        }
                    
        return s;
    }

	
	
}
