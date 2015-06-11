package corenet.exchange;

public class SystemTest {

	public static void main(String[] args) {
		long start = 0;
		long end = 0;
		for (int i = 0; i < 1000000; i++) {

			if (i == 0) {
				start = System.nanoTime();
				continue;
			}
			if(i==999999){
				end =  System.nanoTime();
				continue;
			}

			System.nanoTime();
		}
		
		System.out.println(start);
		System.out.println(end);
System.out.println((end-start)/(1000*1000));
		
	}
}
