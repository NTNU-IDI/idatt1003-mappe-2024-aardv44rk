package edu.ntnu.idi.idatt.controller;

// import edu.ntnu.idi.idatt.model.*;
import edu.ntnu.idi.idatt.view.*;

public class Main {
  
  /** 
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    UI ui = new UI();
    ui.init();
    // ui.start();
    ui.readCookbook();
  }
}
