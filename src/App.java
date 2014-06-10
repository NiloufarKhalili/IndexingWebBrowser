import java.io.IOException;
import java.net.URL;

public class App {

	public static void main(String[] args) throws IOException {
		URL url = new URL("file:///Users/niloufarkhalili/Desktop/test.html");
		WebDoc web = new WebDoc(url);
		web.readFile();
		System.out.println(web.getContenwords());
		System.out.println(web.getContentkeywords());

	}

}
