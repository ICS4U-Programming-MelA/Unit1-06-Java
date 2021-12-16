// Copyright (c) 2021 Mel Aguoth All rights reserved.
//
// Created By: Mel Aguoth
// Date: December 10-12, 2021
// Calculates the mean, median, and mode of numbers taken from three files.

// Import modules.
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Statistics {
  public static double calcMean(Integer[] numberArray) {

    // Declare constants.
    double meanDivisor = numberArray.length;

    // Declare variables.
    int sum = 0;

    // Calculate and return the mean.
    for (int numberCounter = 0; numberCounter < numberArray.length; ++numberCounter) {
      sum += numberArray[numberCounter];
    }
    double mean = sum / meanDivisor;
    return mean;
  }

  public static double calcMedian(Integer[] numberArray) {

    // Declare variables.
    double median = 0;
    Integer[] medianArray = new Integer[numberArray.length];

    // Sort the array.
    for (int medianCounter = 0; medianCounter < numberArray.length; ++medianCounter) {
      medianArray[medianCounter] = numberArray[medianCounter];
    }
    for (int outerSortingCounter = 0; outerSortingCounter < numberArray.length;
         ++outerSortingCounter) {
      for (int innerSortingCounter = outerSortingCounter + 1;
           innerSortingCounter < numberArray.length; ++innerSortingCounter) {
        int holdingSpace = 0;
        if (medianArray[outerSortingCounter] == medianArray[innerSortingCounter]) {
          holdingSpace = medianArray[outerSortingCounter];
          medianArray[outerSortingCounter] = medianArray[innerSortingCounter];
          medianArray[innerSortingCounter] = holdingSpace;
        }
      }
    }

    // Calculate the median.
    final int medianDividend = numberArray.length;
    if (medianDividend % 2 != 0) {
      median = medianDividend / 2;
    } else {
      median = (double) (numberArray[(medianDividend - 1) / 2]
              + numberArray[medianDividend / 2]) / 2;
    }

    // Return the median.
    return median;
  }

  public static Integer[] calcMode(Integer[] numberArray) {

    // Declare variables.
    ArrayList<Integer> modes = new ArrayList<>();
    ArrayList<Integer> modesTemp = new ArrayList<>();
    int maximumRepetition = 0;

    // Find the mode(s).
    for (int outerCounter = 0; outerCounter < numberArray.length; ++outerCounter) {
      int repetitionCounter = 0;
      for (int innerCounter = 0; innerCounter < numberArray.length; ++innerCounter) {
        if (numberArray[innerCounter] == numberArray[outerCounter]) {
          ++repetitionCounter;
        } else if (repetitionCounter > maximumRepetition) {
          maximumRepetition = repetitionCounter;
          modesTemp.clear();
          modesTemp.add(numberArray[outerCounter]);
        }
        if (repetitionCounter == maximumRepetition) {
          modesTemp.add(numberArray[outerCounter]);
        }
      }
    }

    // Remove duplicates from the mode list.
    for (int numberInstance : modesTemp) {
      if (!modes.contains(numberInstance)) {
        modes.add(numberInstance);
      }
    }

    // Return the mode(s).
    return modes.toArray(new Integer[modes.size()]);
  }

  public static void main(String[] args) throws Exception {

    // Declare the list.
    ArrayList<String> userNumberList = new ArrayList<>();

    // Introduce the program.
    System.out.print("This program calculates the mean, median and mode of numbers it"
                   + " takes from textfiles.");
    Scanner input = new Scanner(System.in);

    // Get the user's specified textfile.
    System.out.print("\n" + "Enter the textfile you wish to use (set1, set2, or set3): ");
    String userSetChoice = input.next();
    input.close();
    try {

      // Add the number's from the user's chosen textfile to the list.
      File file = new File(userSetChoice);
      Scanner sc = new Scanner(file);
      while (sc.hasNextLine()) {
        userNumberList.add(sc.nextLine());
      }

      // Transfer the numbers from the list to an array.
      Integer[] userNumberArray = new Integer[userNumberList.size()];
      for (int firstSetCounter = 0; firstSetCounter < userNumberList.size(); ++firstSetCounter) {
        userNumberArray[firstSetCounter] = Integer.parseInt(userNumberList.get(firstSetCounter));
      }

      // Display the number array to the user.
      System.out.print(Arrays.toString(userNumberArray));
      System.out.print("\n");

      // Call calcMean, calcMedian, and calcMode.
      Statistics obj = new Statistics();
      double meanSet1 = obj.calcMean(userNumberArray);
      double medianSet1 = obj.calcMedian(userNumberArray);
      Integer[] modeSet1 = obj.calcMode(userNumberArray);

      // Display the mean, median, and mode.
      System.out.print("\n" + "The mean is: " + meanSet1);
      System.out.print("\n" + "The median is: " + medianSet1);
      System.out.print("\n" + "The mode(s) is/are: " + Arrays.toString(modeSet1));
      System.out.print("\n");

    // If the user's textfile isn't valid, display as such to the user.
    } catch (FileNotFoundException e1) {
      System.out.print("\n" + "That isn't a valid textfile. Please try again.");
      System.out.print("\n");
    }
  }
}
