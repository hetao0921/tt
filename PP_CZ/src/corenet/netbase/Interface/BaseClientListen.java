package corenet.netbase.Interface;

import corenet.netbase.BaseSession;

public interface BaseClientListen {
	public  void OnNewConnection(BaseSession Session, boolean Success);
}
 