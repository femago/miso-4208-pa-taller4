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

import uniandes.miso4208.domain.StoreApp;

import java.util.function.Consumer;
import java.util.logging.Logger;

public class PrintApp implements Consumer<StoreApp> {
    static Logger logger = Logger.getLogger(PrintApp.class.getName());

    @Override
    public void accept(StoreApp storeApp) {
        logger.info(storeApp.toString());
    }

}
