package com.tristimulusCalculator;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class SPD {
    private float stepSize;
    private final TreeMap<Float, Double> values = new TreeMap<>();



    public SPD(File SPDfile, int stepSize) throws FileNotFoundException {
        this(new Scanner(SPDfile),stepSize);
    }

    public SPD(Scanner spd, int stepSize){
        this.stepSize=stepSize;
        String[] items;
        while(spd.hasNext()) {
            do {
                items = spd.nextLine().split(",");
            } while (Float.parseFloat(items[0]) % stepSize != 0 && spd.hasNext());
            values.put(Float.parseFloat(items[0]), Double.parseDouble(items[1]));
        }
    }

    public ArrayList<Float> getWavelengths(){
        ArrayList<Float> vals=new ArrayList<>();
        for(Float val : values.keySet())
            vals.add(val);
        return vals;
    }

    public ArrayList<Double> getValues(){
        ArrayList<Double> vals=new ArrayList<>();
        for(Double val : values.values())
            vals.add(val);
        return vals;
    }


    public Double getValue(int wavelength){
        return this.getValue((float) wavelength);
    }

    public Double getValue(float wavelength){
        try {
            Double val = values.get(wavelength);
            if (val == null)
                return (double) 0;
            return values.get(wavelength);
        }catch(NullPointerException e){
            return (double) 0;
        }
    }



    public static void RandomGenerator(String filename) throws IOException {
        RandomGenerator(390,830,1, filename);
    }

    public static void RandomGenerator(int start, int end, float max, String filename) throws IOException {
        RandomGenerator(start,end,max,(float)1,filename);
    }

    public static void RandomGenerator(int start, int end, float max, float stepSize, String filename) throws IOException {
        Random random = new Random();
        Writer fileWriter=new FileWriter(filename+".csv",false);
        float currentWL=start;
        String line;
        while(currentWL<=end){
            line = (new DecimalFormat("###.#").format(currentWL))+","+(random.nextDouble()*max)+"\n";
            fileWriter.append(line);
            currentWL+=stepSize;
        }
        fileWriter.close();
    }
}
