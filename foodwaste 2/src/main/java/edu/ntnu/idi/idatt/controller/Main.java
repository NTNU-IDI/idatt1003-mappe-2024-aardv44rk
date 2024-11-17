package edu.ntnu.idi.idatt.controller;

import edu.ntnu.idi.idatt.view.UserInterface;

/**
 * The main class of the program, runs UI start and init.
 *
 * @author @aardv44rk
 */
public class Main {
  
  /**
   * Main function of the program.
   *
   * @param args Unused?
   */
  public static void main(String[] args) {
    UserInterface userInterface = new UserInterface();
    userInterface.init();
    userInterface.start();
  }
}
