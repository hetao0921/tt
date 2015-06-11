package NVMP.IpMatrixManage.Implement;


public class TimeCount extends Thread {

	IpMatrixManageRun imr;

	public TimeCount(IpMatrixManageRun im) {
		imr = im;
		this.start();
	}

	@Override
	public void run() { 
		while (true) {
			try {
				Thread.sleep(3 * 60 * 1000);
//				imr.onClosePlay();

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}
