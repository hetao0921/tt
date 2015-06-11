package NVMP.IpMatrixManage.Implement;

public class MatrixResult {
	private String Address;
	private int port;
	private String user;
	private String passwd;
	private int outmodel;
	private int matrixType;
	
	public MatrixResult(String _Address,int _port, String _user,String _passwd,int _outmodel,int _matrixType){
		Address = _Address;
		port = _port;
		user = _user;
		passwd = _passwd;
		outmodel = _outmodel;
		matrixType = _matrixType;
	}

	public String getAddress() {
		return Address;
	}

	public int getPort() {
		return port;
	}

	public String getUser() {
		return user;
	}

	public String getPasswd() {
		return passwd;
	}

	public int getOutmodel() {
		return outmodel;
	}

	public int getMatrixType() {
		return matrixType;
	}
	
	
	
}
