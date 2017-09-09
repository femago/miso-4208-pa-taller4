/*
 * @(#)RetrieveAppDetail.java
 *
 * Copyright (c) 2017 Southwest Airlines, Co.
 * 2702 Love Field Drive, Dallas, TX 75235, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Southwest Airlines, Co.
 */
package uniandes.miso4208.domain.procesor;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import uniandes.miso4208.domain.StoreApp;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.logging.Logger;

public class RetrieveAppDetail implements Consumer<StoreApp> {

    static Logger logger = Logger.getLogger(RetrieveAppDetail.class.getName());
    private Document doc;

    @Override
    public void accept(StoreApp storeApp) {
        logger.info("Processing App: " + storeApp.getId());
        doc = loadAppDoc(storeApp);
        mapValues(storeApp);

    }

    private void mapValues(StoreApp storeApp) {
        storeApp.setName(doc.select(".id-app-title").text());
        storeApp.setDescription(doc.select("[itemprop=description]").text());
        storeApp.setRatingsCount(doc.select(".reviews-num").text());
        storeApp.setAvgRating(doc.select(".score").text());
        processComments(storeApp, doc.select(".recent-change"));
        storeApp.setFiveStartCounts(doc.select(".rating-bar-container.five .bar-number").text());
        storeApp.setFourStartCounts(doc.select(".rating-bar-container.four .bar-number").text());
    }

    private void processComments(StoreApp storeApp, Elements select) {
        storeApp.setChanges(
                select.stream().map(e -> e.text()).reduce((t, u) -> t + "/n" + u).
                        get());
    }

    private Document loadAppDoc(StoreApp storeApp) {
        try {
            return Jsoup.connect("https://play.google.com/store/apps/details?id=" + storeApp.getId()).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
