package uniandes.miso4208;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import uniandes.miso4208.domain.Crawler;
import uniandes.miso4208.domain.procesor.PrintApp;
import uniandes.miso4208.domain.procesor.RetrieveAppDetail;
import uniandes.miso4208.domain.procesor.SaveAppToFile;
import uniandes.miso4208.output.FileLinesWriter;

import java.io.IOException;

/**
 * Hello world!
 */
public class App {
   public static void main(String[] args) throws IOException {
      Crawler crawler = new Crawler("https://play.google.com/store/apps/category/BEAUTY/collection/topselling_new_paid");
      crawler.load();
      crawler.processApps(RetrieveAppDetail.class, PrintApp.class, SaveAppToFile.class);
      FileLinesWriter.getInstance().closeFile();
   }
}
