package edu.ntnu.idi.idatt;

import edu.ntnu.idi.idatt.util.AppInitializer;
import edu.ntnu.idi.idatt.view.MainMenu;

/**
 * The main class of the application. Runs the UserInterface, 
 * which is the main view of the application.
 *
 * @author aardv44rk
 * @since December 4th 2024
 * @version 2.0
 */
public class App {
  /**
   * The main method of the application.
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    AppInitializer appInitializer = new AppInitializer();
    MainMenu mainMenu = appInitializer.init();
    appInitializer.start(mainMenu);
  }
}
