package mavenweb;

public class TestEscape {
	public static void main(String[] args) {
		String json ="'dasda'";//\'dasda\'
		json=json.replace("'", "\'");
		System.out.println(json);
	}

}
