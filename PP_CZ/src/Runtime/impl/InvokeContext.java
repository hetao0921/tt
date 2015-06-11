package Runtime.impl;

import Runtime.ReturnDo;

/**
 * by zzw
 * ÓÃÀŽŽæŽ¢ÔËÐÐºóµ÷ÓÃµÄž÷ÖÖ»·Ÿ³
 * 
 * **/ 
public class InvokeContext {
    private String ObjUrl;
    private ReturnDo AsyncResult;
    private Object Context;
	public String getObjUrl() {
		return ObjUrl;
	}
	public void setObjUrl(String objUrl) {
		ObjUrl = objUrl;
	}
	public ReturnDo getAsyncResult() {
		return AsyncResult;
	}
	public void setAsyncResult(ReturnDo asyncResult) {
		AsyncResult = asyncResult;
	}
	public Object getContext() {
		return Context;
	}
	public void setContext(Object context) {
		Context = context;
	}
    
    
}
