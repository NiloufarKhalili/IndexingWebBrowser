import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Set;
import java.util.TreeSet;

public class WebDoc {
	private Set<String> contectwords;
	private Set<String> contentkeywords;
	private URL url;

	public WebDoc(URL url) {
		contectwords = new TreeSet<String>();
		contentkeywords = new TreeSet<String>();
		setUrl(url);
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public URL getUrl() {
		return url;
	}

	public Set<String> getContenwords() {
		return contectwords;
	}

	public Set<String> getContentkeywords() {
		return contentkeywords;
	}

	public void readFile() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				url.openStream()));
		String line = null;
		try {
			while ((line = br.readLine()) != null) {
				String keys = null;
				if (line.matches("<meta name\\s?=\\s?\"keywords\"\\scontent\\s?=\\s?\".*\"/?>")) {
					keys = line;
					setKeywords(keys);
				}
				removeHtmlTags(line);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		try {
			br.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	void setKeywords(String keys) {
		if (keys.toLowerCase().startsWith("<meta ")) {
			if (keys.toLowerCase()
					.matches(
							"<meta name\\s?=\\s?\"keywords\"\\scontent\\s?=\\s?\".*\"/?>")) {
				keys = keys
						.replaceAll(
								"<meta name\\s?=\\s?\"keywords\"\\scontent\\s?=\\s?\"(.*)\"/?>",
								"$1");
				String[] kwords = keys.split(",");
				for (String element : kwords) {
					contentkeywords.add(element.trim());
				}
			}
		}

	}

	public void removeHtmlTags(String str) {
		str = str.trim();
		String content = str.replaceAll("<[^>]*>", "");
		content = content.replaceAll("[0-9]", "");
		content = content.replaceAll(",", "");
		content = content.replaceAll("\\.", "");
		content = content.replaceAll("\\$", "");
		addWords(content.trim());
	}

	public void addWords(String content) {
		String[] words = content.split("\\s+");
		for (String element : words) {
			contectwords.add(element.trim());
		}

	}

}
