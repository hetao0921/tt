
public class TestPoint {
	
	public static void main(String[] args) {
		String versions="{}";
		String[] ver = versions.split("}");
		System.out.println("ver length"+ver.length);
		for (int i = 0; i < ver.length; i++) {
			String[] cenver = ver[i].substring(1).split(":");
			System.out.println(cenver.length);
			}
	}

}
