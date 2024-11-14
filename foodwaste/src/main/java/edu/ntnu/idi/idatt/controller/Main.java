package edu.ntnu.idi.idatt.controller;

// import edu.ntnu.idi.idatt.model.*;
import edu.ntnu.idi.idatt.view.Ui;

public class Main {
  
  /** 
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    Ui ui = new Ui();
    ui.init();
    ui.start();
  }
}
