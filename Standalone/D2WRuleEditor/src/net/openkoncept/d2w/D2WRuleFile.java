package net.openkoncept.d2w;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Use this class to create instance of a d2w rule file.
 * 
 * @author ijazfx
 */
public class D2WRuleFile {

	private List<D2WRule> lines = new ArrayList<D2WRule>();

	public D2WRuleFile() {

	}

	public D2WRuleFile load(String filePath) throws IOException {
		if (filePath == null)
			return null;
		File file = new File(filePath);
		return load(file);
	}

	public D2WRuleFile load(File file) throws IOException {
		if (file == null)
			return null;
		FileInputStream ins = new FileInputStream(file);
		return load(ins);
	}

	public D2WRuleFile load(URI uri) throws MalformedURLException, IOException {
		if (uri == null)
			return null;
		return load(uri.toURL().openStream());
	}

	public D2WRuleFile load(InputStream ins) throws IOException {
		if (ins == null)
			return null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
		lines.clear();
		String line = reader.readLine();
		while (line != null) {
			if (!line.startsWith("(") && !line.endsWith(")")) {
				D2WRule rule = D2WRule.parse(line.trim());
			}
			line = reader.readLine();
		}
		reader.close();
		return this;
	}

	public void save(String filePath) {

	}

	public static void main(String[] args) {
		D2WRuleFile ruleFile = new D2WRuleFile();
		try {
			ruleFile.load(args[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
