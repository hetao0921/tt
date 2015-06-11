package fxdigital.mqcore.exchange.rpc;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;


/**
 * 邮件
 * 
 * @author fucz
 * @version 2014-6-12
 */
public class Mail implements IMessage{
	
	private String content;//邮件内容
	private List<String> path = new ArrayList<String>();//邮件路径（默认为空）
	private String srcMailboxID;//源邮箱ID
	private String preMailboxID;//上一个邮箱ID
	private String nextMailboxID;//下一个邮箱ID
	private String destMailboxID;//目的邮箱ID（可为空）
	private String contentType;//邮件内容的类型
	private String sendMode;//发送模式
	private boolean isRecordPath = false;//是否记录邮件路径（默认不记录）
	private boolean isIpMail = false;//是否是IP邮件
	
	public Mail(){
	}
	
	public Mail(String content){
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSrcMailboxID() {
		return srcMailboxID;
	}
	public void setSrcMailboxID(String srcMailboxID) {
		this.srcMailboxID = srcMailboxID;
	}
	public String getDestMailboxID() {
		return destMailboxID;
	}
	public void setDestMailboxID(String destMailboxID) {
		this.destMailboxID = destMailboxID;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getSendMode() {
		return sendMode;
	}
	public void setSendMode(String sendMode) {
		this.sendMode = sendMode;
	}
	public List<String> getPath() {
		return path;
	}
	public void setPath(List<String> path) {
		this.path = path;
	}
	public boolean isInPath(String mailboxID){
		if(this.path.contains(mailboxID)){
			return true;
		}else{
			return false;
		}
	}
	public boolean extendPath(String mailboxID){
		if(!this.path.contains(mailboxID)){
			return this.path.add(mailboxID);
		}else{
			return true;
		}
	}
	public boolean isRecordPath() {
		return isRecordPath;
	}
	public void setRecordPath(boolean isRecordPath) {
		this.isRecordPath = isRecordPath;
	}
	public String getPreMailboxID() {
		return preMailboxID;
	}
	public void setPreMailboxID(String preMailboxID) {
		this.preMailboxID = preMailboxID;
	}
	
	public String getNextMailboxID() {
		return nextMailboxID;
	}
	public void setNextMailboxID(String nextMailboxID) {
		this.nextMailboxID = nextMailboxID;
	}
	
	public String toString(){
		return JSON.toJSONString(this);
	}

	public boolean isIpMail() {
		return isIpMail;
	}

	public void setIpMail(boolean isIpMail) {
		this.isIpMail = isIpMail;
	}
	
}
