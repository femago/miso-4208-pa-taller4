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
import uniandes.miso4208.output.FileLinesWriter;

import java.lang.reflect.Field;
import java.util.function.Consumer;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class SaveAppToFile implements Consumer<StoreApp> {
   Logger logger = Logger.getLogger(SaveAppToFile.class.getName());

   @Override
   public void accept(StoreApp storeApp) {
      String line = Stream.of(storeApp.getClass().getDeclaredFields())
            .skip(1)
            .map(field -> {
               field.setAccessible(true);
               return fieldValue(field, storeApp);
            })
            .reduce((t, u) -> t + ";" + u).
                  get().toString();

      FileLinesWriter.getInstance().writeLine(line);
   }

   private Object fieldValue(Field field, StoreApp storeApp) {
      try {
         return field.get(storeApp).toString();
      } catch (final IllegalAccessException e) {
         logger.info("Error with object " + storeApp.getId() + " field: " + field.getName());
         return "";
      }
   }
}
