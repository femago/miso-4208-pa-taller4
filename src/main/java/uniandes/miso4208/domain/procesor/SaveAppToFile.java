/*
 * @(#)SaveAppToFile.java
 *
 * Copyright (c) 2017 Southwest Airlines, Co.
 * 2702 Love Field Drive, Dallas, TX 75235, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Southwest Airlines, Co.
 */
package uniandes.miso4208.domain.procesor;

import uniandes.miso4208.domain.StoreApp;
import uniandes.miso4208.output.AppsReportWriter;

import java.util.function.Consumer;
import java.util.logging.Logger;

public class SaveAppToFile implements Consumer<StoreApp> {
    Logger logger = Logger.getLogger(SaveAppToFile.class.getName());

    @Override
    public void accept(StoreApp storeApp) {
        AppsReportWriter.getInstance().saveRow(storeApp.asRecord());
    }
}
