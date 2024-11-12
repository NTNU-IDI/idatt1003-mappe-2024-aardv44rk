package edu.ntnu.idi.idatt.controller;

import edu.ntnu.idi.idatt.view.UI;

public class App {
    public static void main(String[] args) throws Exception {
        UI ui = new UI();
        ui.init();
        ui.start();
    }
}
