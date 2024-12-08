package APIs;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class LabAssign6B_Hamilton {

    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://www.nicekicks.com/sneaker-release-dates/").get();
        //System.out.println(doc);
        
        Elements divs = doc.select("div[class=release-date__wrapper]");
        //System.out.println(divs);
        
        String releaseInfo, picture;
        for (Element div : divs) {
            releaseInfo = div.getElementsByClass("block-release-info").text();
            System.out.println(releaseInfo);
            
            // Picture
            picture = div.getElementsByAttribute("href").toString();
            picture = picture.substring(picture.indexOf("https:"), picture.indexOf("/\""));
            System.out.println(picture);
            System.out.println("");
        }
}
    
