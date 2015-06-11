package app.singlepad;

import org.misc.log.LogUtil;

/**
 * 
 * @author hxht
 * @version 2014-9-17
 */
public abstract class AbstractSinglePadBean implements ISinglePadBean{
	
	
	public boolean equals(Object o){
		if(o.getClass() == this.getClass()){
			AbstractSinglePadBean bean = (AbstractSinglePadBean) o;
			try{
				if(getSinglePadID().equals(bean.getSinglePadID())
						&& getSinglePadIP().equals(bean.getSinglePadIP())
						&& getSinglePadName().equals(bean.getSinglePadName())
						&& getSinglePadPort().equals(bean.getSinglePadPort())
						&& getSinglePadRtsp().equals(bean.getSinglePadRtsp())
						&& getSinglePadType().equals(bean.getSinglePadType())
						)
					return true;
				else
					return false;
			}catch(Exception e){
				e.printStackTrace();
				LogUtil.error("单兵系统：单兵数据错误！");
				return false;
			}
		}else{
			return false;
		}
	}
	
	public int hashCode(){
		return getSinglePadID().hashCode()
				+ getSinglePadIP().hashCode()
				+ getSinglePadName().hashCode()
				+ getSinglePadPort().hashCode()
				+ getSinglePadRtsp().hashCode()
				+ getSinglePadType().hashCode()
				;
	}
	
}
