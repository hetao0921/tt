package fxdigital.mqcore.exchange.impl;

public interface IConvert {
	public String oldConvertToNew(boolean onoff,String xml);
	public String newConvertToOld(boolean onoff,String xml);

}
