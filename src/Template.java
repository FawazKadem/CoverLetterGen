import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;

public class Template {
    private XWPFDocument doc;
    private HashSet<String> variables;

    public Template (String filepath) throws IOException {
        this(docFromPath(filepath));
    }

    public Template (XWPFDocument doc) throws IOException {
        this.doc = doc;
        this.variables = new HashSet<>();
        populateVariables();

    }

    private static XWPFDocument docFromPath(String filepath) throws IOException {
        Path path = Paths.get(filepath);
        byte[] byteData = Files.readAllBytes(path);
        return new XWPFDocument(new ByteArrayInputStream(byteData));
    }

    private void populateVariables() {
        XWPFWordExtractor reader = new XWPFWordExtractor(this.doc);
        String docText = reader.getText();

        int leftIdx = 0;
        int rightIdx = 0;

        while(leftIdx != -1 && rightIdx != -1){
            leftIdx = docText.indexOf("<<", rightIdx);

            if (leftIdx != -1) {
                rightIdx = docText.indexOf(">>", leftIdx);
                if (rightIdx != -1) {
                    variables.add(docText.substring(leftIdx, rightIdx + 2));
                }
            }
        }

        for (String s : variables) {
            System.out.println(s);
        }


    }
}
