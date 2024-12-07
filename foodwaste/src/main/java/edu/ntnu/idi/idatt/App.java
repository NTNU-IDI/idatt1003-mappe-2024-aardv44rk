package edu.ntnu.idi.idatt;

import edu.ntnu.idi.idatt.controllers.CookbookController;
import edu.ntnu.idi.idatt.controllers.StorageController;
import edu.ntnu.idi.idatt.model.Cookbook;
import edu.ntnu.idi.idatt.model.FoodStorage;
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
    Cookbook cookbook = new Cookbook();
    FoodStorage storage = new FoodStorage();
    StorageController storageController = new StorageController(storage);
    CookbookController cookbookController = new CookbookController(cookbook, storageController);
    new MainMenu(storageController, cookbookController).display();
  }
}
