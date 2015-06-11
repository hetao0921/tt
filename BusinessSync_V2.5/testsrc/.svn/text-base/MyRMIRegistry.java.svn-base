import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * 
 * @author fucz
 * @version 2014-7-16
 */
public class MyRMIRegistry {

	public MyRMIRegistry() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int RegistryRMI(int i) {
		System.out.println("开始获得监听服务.....,i=" + i);
		int j = i;
		// Object obj = null;
		try {
			Registry registry = LocateRegistry.getRegistry(j);
			registry.list();
		} catch (RemoteException remoteexception) {
			remoteexception.printStackTrace();
			System.out.println("获得监听的服务出错.......");
			Registry registry1 = null;
			do {
				if (registry1 != null)
					break;
				try {
					registry1 = LocateRegistry.createRegistry(j);
					registry1.list();
					System.out.println("服务创建成功......");
				} catch (RemoteException remoteexception1) {
					remoteexception1.printStackTrace();
					break;
					// throw remoteexception1;
				}
			} while (true);
		}
		System.out.println("服务监听结束.......");
		return j;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyRMIRegistry myRMI = new MyRMIRegistry();
		myRMI.RegistryRMI(1093);
	}

}
