package com.fxdigital.ipmatrix;

import java.util.ArrayList;
 
public class DisplayChannelInfo{
	private ArrayList<SplitArea> arrArea;//默认约定最多16分屏
	private int splitNum;           //代表当前bnc显示通道的分屏数，分屏数为几，则数组的前几个有效；
	private int nIsDisplayChannelPlay; //当前显示通道是否正在输出	


	public int getSplitNum() {
		return splitNum;
	}
	public void setSplitNum(int splitNum) {
		this.splitNum = splitNum;
	}
	public int getnIsDisplayChannelPlay() {
		return nIsDisplayChannelPlay;
	}
	public void setnIsDisplayChannelPlay(int nIsDisplayChannelPlay) {
		this.nIsDisplayChannelPlay = nIsDisplayChannelPlay;
	}
	
	
	public ArrayList<SplitArea> getArrArea() {
		if(arrArea==null)
			arrArea = new ArrayList<SplitArea>();
		return arrArea;
	}
	public void setArrArea(ArrayList<SplitArea> arrArea) {
		this.arrArea = arrArea;
	}
	public void setSplitArea(int index,int nDecoderIndex,int nIsDecode,int nIsAreaPlay,String sourceIp,int sourcePort,int sourceChannel){
		SplitArea sa = new SplitArea();
		sa.setnIndex(index);
		sa.setnDecoderIndex(nDecoderIndex);
		sa.setnIsDecode(nIsDecode);
		sa.setnIsAreaPlay(nIsAreaPlay);
		sa.setSourceIp(sourceIp);
		sa.setSourcePort(sourcePort);
		sa.setSourceChannel(sourceChannel);
		
		getArrArea().add(sa);
	}
}
