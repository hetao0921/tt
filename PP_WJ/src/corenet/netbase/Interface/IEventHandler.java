package corenet.netbase.Interface;
import java.nio.channels.SelectionKey;

public interface IEventHandler {
	void handleIO(SelectionKey selectionKey, Object userContext);
	void handleError(Exception e, Object userContext);
}
