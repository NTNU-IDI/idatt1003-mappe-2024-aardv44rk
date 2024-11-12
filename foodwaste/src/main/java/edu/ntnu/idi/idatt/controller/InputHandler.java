package edu.ntnu.idi.idatt.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner = new Scanner(System.in);

    public double getValidDouble(String string) {
        double value = 0;
        boolean valid = false;

        while (!valid) {
            System.out.println(string);
            String input = scanner.nextLine();

            try {
                value = Double.parseDouble(input);
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number");
            }
        }
        return value; 
    }

    public int getValidInt(String string) {
        int value = 0;
        boolean valid = false;

        while (!valid) {
            System.out.println(string);
            String input = scanner.nextLine();

            try {
                value = Integer.parseInt(input);
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer");
            }
        } 

        return value; 
    }

    public double getValidFloat(String string) { // maybe not needed
        double value = 0;

        return value; 
    }

    public Date getValidDate(String string) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false);

        Date date = null;
        Boolean valid = false;

        while (!valid) {
            System.out.println(string);
            String input = scanner.nextLine();

            try {
                sdf.parse(input);
                valid = true;    
            } catch (ParseException e) {
                System.out.println("Please enter a date on the format dd-MM-yyyy. :)");
            }
        }
        return date;
    }
}
