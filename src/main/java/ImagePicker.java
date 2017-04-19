import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;
import javax.swing.text.AttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;
//import org.json.JSONObject;

public class ImagePicker

{
    private static final Logger logger = Logger.getLogger(ImagePicker.class.getName());
    public static void main(String args[]) throws Exception

    {
        logger.info("Started Running the code");
        String websiite = args[0];
        if(args == null || args.length == 0){
            System.out.println("Pass the Url to run the program");
            System.exit(0);
        }

        URL url = new URL(websiite);
        URLConnection connection = url.openConnection();
        logger.info("Url connection is sucessfull");
        InputStream is = connection.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        HTMLEditorKit htmlKit = new HTMLEditorKit();  // --- Create a HTML document ---
        HTMLDocument htmlDoc = (HTMLDocument) htmlKit.createDefaultDocument();  // --- Create a HTML document ---
        HTMLEditorKit.Parser parser = new ParserDelegator(); //Parse an HTML file into text while preserving carriage returns
        HTMLEditorKit.ParserCallback callback = htmlDoc.getReader(0);
        parser.parse(br, callback, true);

       // JSONObject obj = new JSONObject();
        for (HTMLDocument.Iterator iterator = htmlDoc.getIterator(HTML.Tag.IMG); iterator.isValid(); iterator.next()) {
            AttributeSet attributes = iterator.getAttributes();
            String Imsrcname = (String) attributes.getAttribute(HTML.Attribute.SRC);

            if (Imsrcname != null && (Imsrcname.endsWith(".jpg") || (Imsrcname.endsWith(".png")) || (Imsrcname.endsWith(".jpeg")) || (Imsrcname.endsWith(".bmp")) || (Imsrcname.endsWith(".ico"))))
                logger.info("Downloading Images of all format");

            {
/*                try {
                    downloadImage(websiite, Imsrcname,obj);

                    logger.info("Passing the url to download "+websiite);
                    logger.info("Passing the image name to download "+ Imsrcname);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }*/
            }
        }
        FileWriter file1 = new FileWriter("ImageDetails.json");
        PrintWriter out = new PrintWriter("ImageDetails.json");
        //out.println(obj.toString());
        out.close();
    }

/*    private static void downloadImage(String url, String Imsrcname,JSONObject obj ) throws IOException {
        BufferedImage image = null;

        try {
            if (!(Imsrcname.startsWith("http"))) {

                url = url + Imsrcname;
            } else {
                url = Imsrcname;
            }

            Imsrcname = Imsrcname.substring(Imsrcname.lastIndexOf("/") + 1);
            String imageFormat = null;
            imageFormat = Imsrcname.substring(Imsrcname.lastIndexOf(".") + 1);
            String TgtimgPath = null;
            TgtimgPath =  Imsrcname;  // --- final path to save
            URL imageUrl = new URL(url);
            image = ImageIO.read(imageUrl);      // -- load the image
            if (image != null) {
                File file = new File(TgtimgPath);
                ImageIO.write(image, imageFormat, file);
                obj.put( Imsrcname,file.length());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }*/
}
