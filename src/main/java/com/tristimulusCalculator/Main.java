/*
* CEI XYZ tristimulus calculator exercise
* Luca Armellin
* mat. 894527
* */

package com.tristimulusCalculator;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static final String SDP_PATH="data/spectrums/";

    public static void main(String[] args) throws IOException {
        int step=0; //Sampling step size in nanometers
        Scanner scanner = new Scanner(System.in);
        Scanner inputSPD = null;    //Input SPD

        int choice=0;
        boolean invalidChoice=true;
        while(invalidChoice || choice!=1 && choice!=2){
            try {
                System.out.println("Make a choice:\n\t[1] Generate random SPD\n\t[2] Load existing SPD");
                choice = scanner.nextInt();
                invalidChoice=false;
            } catch (java.util.InputMismatchException e) {
                scanner.nextLine();
            }
        }
        scanner.nextLine();
        if(choice == 1){
            System.out.print("\nnew SPD filename: ");
            String SPDfilename=scanner.nextLine();
            SPD.RandomGenerator(SDP_PATH+SPDfilename);
            inputSPD=new Scanner(new File(SDP_PATH+SPDfilename+".csv"));
        } else{
            String[] filenames=new File(SDP_PATH).list();
            System.out.println("\nAvailable SPDs:");
            for (String filename : filenames) {
                System.out.println("\t"+filename);
            }
            invalidChoice=true;
            while(invalidChoice) {
                try {
                    System.out.print("SPD filename: ");
                    String SPDfilename = scanner.nextLine();
                    inputSPD=new Scanner(new File(SDP_PATH+SPDfilename));
                    invalidChoice=false;
                } catch (FileNotFoundException e){
                    System.out.print("File not found, ");
                }
            }
        }

        invalidChoice=true;
        while(invalidChoice || step<5) {
            try {
                System.out.print("\nSampling step [>=5nm]: ");
                step = scanner.nextInt();
                invalidChoice=false;
            } catch (java.util.InputMismatchException e) {
                scanner.nextLine();
            }
        }


        /*
        * Create CIEIterator based on transfer functions in data/cie.csv file and SPD object containing the input SPD values.
        * These two objects are passed to a TripletCalculator which calculates tristimulus values
        * */
        CIEIterator CIEObserver = new CIEIterator(new File("data/cie.csv"),step);
        SPD spd=new SPD(inputSPD,step);
        TripletCalculator triplet=new TripletCalculator(spd, CIEObserver);
        System.out.println("\nTriplet:\n\t"+triplet.toString());

        /*
        * Plot the SPD
        * */
        XYChart chart;
        ArrayList<Double> yval=spd.getValues();
        ArrayList<Float> xval=spd.getWavelengths();
        chart= QuickChart.getChart("Input SPD", "wavelength [nm]", "intensity", "spd",xval,yval);
        new SwingWrapper(chart).displayChart();

    }
}
