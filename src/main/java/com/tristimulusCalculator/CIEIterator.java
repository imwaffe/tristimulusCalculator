/*
* Iterates through transfer functions coefficients at stepSize nanometers steps.
* */


package com.tristimulusCalculator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class CIEIterator implements Iterator {
    private Scanner cie;
    private int stepSize;

    public CIEIterator(File cieFile, int stepSize) throws FileNotFoundException {
        cie=new Scanner(cieFile);
        this.stepSize=stepSize;
    }

    @Override
    public boolean hasNext() {
        return cie.hasNextLine();
    }

    @Override
    public Object next() {
        CIE output=new CIE();
        String[] items;

        do {
            String line = cie.nextLine();
            items = line.split(",");
            output.wavelength = Integer.parseInt(items[0]);
        }while(output.wavelength%stepSize!=0 && this.hasNext());
        output.xdash=Double.parseDouble(items[1]);
        output.ydash=Double.parseDouble(items[2]);
        output.zdash=Double.parseDouble(items[3]);

        return output;
    }

    public int getStepSize() {
        return stepSize;
    }
}
