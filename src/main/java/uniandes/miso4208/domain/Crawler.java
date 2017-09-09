/*
 * @(#)Crawler.java
 *
 * Copyright (c) 2017 Southwest Airlines, Co.
 * 2702 Love Field Drive, Dallas, TX 75235, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Southwest Airlines, Co.
 */
package uniandes.miso4208.domain;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Crawler {
    private final String url;
    private Document doc;

    public Crawler(String url) {
        this.url = url;
    }

    private void load() {
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void processApps(Class<? extends Consumer<StoreApp>>... commands) {
        load();
        Set container = Collections.synchronizedSet(new HashSet());
        Elements appCards = doc.select(".card.apps .card-content[data-docid]");
        appCards.stream()
                .map(e -> new StoreApp(e.attr("data-docid")))
                .distinct()
                .forEach(app -> {
                    Stream.of(commands).forEach(cmdClass -> {
                        Consumer<StoreApp> cmd = buildCmd(cmdClass);
                        cmd.accept(app);
                    });
                });
    }

    private Consumer<StoreApp> buildCmd(Class<? extends Consumer<StoreApp>> cmdClass) {
        try {
            return cmdClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
