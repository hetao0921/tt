package NVMP.VideoControlDomain;

/**
 * 就是用来返回一个结果的。
 * */
public class Result {
	private int num;
	private String uuid;
	private String strFlag;

	public Result(int num, String uuid, String Flag) {
		this.num = num;
		this.uuid = uuid;
		this.strFlag = Flag;
	}

	public int getNum() { 
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getStrFlag() {
		return strFlag;
	}

	public void setStrFlag(String strFlag) {
		this.strFlag = strFlag;
	}

}
