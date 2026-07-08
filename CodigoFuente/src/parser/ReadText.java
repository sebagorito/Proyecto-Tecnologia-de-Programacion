package parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReadText {
	protected String fileName;

    public ReadText (String archiveName) {
        this.fileName = archiveName;
    }

    public void writeFile(String text) throws IOException {
        BufferedWriter file;
        file = new BufferedWriter(new FileWriter(fileName));
        file.write(text);
        file.close();
    }

    public String readFile() throws IOException {
        String readLine;
        String readText ="";
        BufferedReader file;
        file = new BufferedReader(new FileReader(fileName));
        while((readLine = file.readLine()) != null) {
            readText += readLine +'\n';
        }
        file.close();
        return readText;
    }

}
