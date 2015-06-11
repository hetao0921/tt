package corenet.exchange;

public interface IConnectRule {
	 boolean isAllowLogin(String sessionID,String sessionIP);
}
