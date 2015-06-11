package app;

import corenet.netbase.NIOReactor;
import corenet.netbase.Interface.ITimer;

/**
 * 
 * @author hxht
 * @version 2014-9-23
 */
public class Test implements Runnable{
	
	private ITimer time;
	private int i;
	
	public Test(int i){
		this.i = i;
		NIOReactor r = (NIOReactor) NIOReactor.defaultDispatcher();
		time = r.newTimer(this);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		List<Test> ts = new ArrayList<Test>();
//		for(int i=0;i<10;i++){
//			Test t = new Test(i);
//			t.schedule();
//			ts.add(t);
//		}
//		for(Test t : ts){
//			new Thread(t.new Cancel(t)).start();
//		}
		
//		Map<ISinglePadBean,String> container = new HashMap<ISinglePadBean,String>();
//		DefaultSinglePadBeanImpl bean = new DefaultSinglePadBeanImpl();
//		bean.setSinglePadID("id");
//		bean.setSinglePadIP("ip");
//		bean.setSinglePadName("name");
//		bean.setSinglePadPort(5050);
//		bean.setSinglePadRtsp("rtsp");
//		container.put(bean, "hello");
//		
//		DefaultSinglePadBeanImpl bean2 = new DefaultSinglePadBeanImpl();
//		bean2.setSinglePadID("id");
//		bean2.setSinglePadIP("ip");
//		bean2.setSinglePadName("name");
//		bean2.setSinglePadPort(5050);
//		bean2.setSinglePadRtsp("rtsp");
//		
//		System.out.println(bean.equals(bean2));
//		System.out.println(container.get(bean2));
		
//		int y=0;
//		if(1==2 & ++y>=0);
//		System.out.println(y);
		
		ok: for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.println("i=" + i + ",j=" + j);
				if (j == 5)
					break ok;
			}
		}
		
	}
	
	class Cancel implements Runnable{
		
		private Test t;
		
		public Cancel(Test t){
			this.t = t;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			t.cancel();
		}
		
	}
	
	public void schedule(){
		time.schedule(100);
	}
	
	public void cancel(){
		time.cancel();
	}

	@Override
	public void run() {
		System.out.println(i);
		schedule();
	}
	
}
