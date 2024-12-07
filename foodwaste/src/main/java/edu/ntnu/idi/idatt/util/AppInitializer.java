package edu.ntnu.idi.idatt.util;

import edu.ntnu.idi.idatt.controllers.CookbookController;
import edu.ntnu.idi.idatt.controllers.StorageController;
import edu.ntnu.idi.idatt.model.Cookbook;
import edu.ntnu.idi.idatt.model.FoodStorage;
import edu.ntnu.idi.idatt.view.MainMenu;

/**
 * This class initializes the application and all objects needed to run the application.
 */
public class AppInitializer {

  /**
   * Initializes the application and all objects needed to run the application.
   */
  public MainMenu init() {
    FoodStorage foodStorage = new FoodStorage();
    Cookbook cookbook = new Cookbook();
    StorageController storageController = new StorageController(foodStorage);
    CookbookController cookbookController = new CookbookController(cookbook, storageController);
    

    return new MainMenu(storageController, cookbookController);
  }

  /**
   * Starts the application by displaying the main menu.
   *
   * @param mainMenu the main menu of the application
   */
  public void start(MainMenu mainMenu) {
   
    mainMenu.display();
  }
}
