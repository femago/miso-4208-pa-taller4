package uniandes.miso4208;

import uniandes.miso4208.domain.Crawler;
import uniandes.miso4208.domain.StoreApp;
import uniandes.miso4208.domain.procesor.PrintApp;
import uniandes.miso4208.domain.procesor.RetrieveAppDetail;
import uniandes.miso4208.domain.procesor.SaveAppToFile;
import uniandes.miso4208.output.AppsReportWriter;

import javax.swing.*;
import java.io.IOException;
import java.util.Arrays;

/**
 * Hello world!
 */
public class App {
    public static final String APP_STORE_URL = "https://play.google.com/store/apps/";

    public static void main(String[] args) throws IOException {
        String lista = JOptionPane.showInputDialog("Ingrese el complemento de la URL que desea analizar\n" +
                APP_STORE_URL);
        String url = APP_STORE_URL + lista;

        showConfirm(url);
        AppsReportWriter.getInstance().saveRow(Arrays.asList("URL", url));
        AppsReportWriter.getInstance().saveRow(StoreApp.headers());

        Crawler crawler = new Crawler(url);
        crawler.processApps(RetrieveAppDetail.class, SaveAppToFile.class);

        AppsReportWriter.getInstance().close();
        JOptionPane.showMessageDialog(null, "Proceso Terminado.");
    }

    private static void showConfirm(String url) {
        int result = JOptionPane.showConfirmDialog(null,
                "Esta seguro que desea explorar esta URL?\n" + url, null, JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.NO_OPTION) {
            System.exit(0);
        }
    }
}
