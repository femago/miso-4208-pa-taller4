/*
 * @(#)FileWriter.java
 *
 * Copyright (c) 2017 Southwest Airlines, Co.
 * 2702 Love Field Drive, Dallas, TX 75235, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Southwest Airlines, Co.
 */
package uniandes.miso4208.output;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileLinesWriter {
   private static FileLinesWriter instance;

   private final PrintWriter writer;

   private FileLinesWriter(){
      try {
         writer = new PrintWriter(new FileWriter("out.csv"));
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   public static FileLinesWriter getInstance() {
      return instance == null ? instance = new FileLinesWriter() : instance;
   }

   public synchronized void writeLine(String line){
      writer.println(line);
   }

   public void closeFile(){
      writer.close();
   }
}
