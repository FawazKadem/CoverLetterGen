import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class Generator {
    private Template template;
    private HashSet<String> variables;
    private HashMap<String, String> varToReplacement;


    public Generator (Template template) {
        this.template = template;
    }


    //private XWPFDocument replaceText()




    public static void main(String[] args) {
        try {
            Template tempTest = new Template("temp.docx");
            Generator genTest = new Generator(tempTest);

        } catch (IOException e) {
            e.printStackTrace();
        }



        System.out.println("we out here");
    }
}
