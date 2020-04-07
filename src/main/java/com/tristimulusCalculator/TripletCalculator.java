package com.tristimulusCalculator;

public class TripletCalculator {
    private double X=0, Y=0, Z=0;

    public TripletCalculator(SPD spd, CIEIterator standardObserver) {
        int iterations=0;
        while(standardObserver.hasNext()){
            CIE cie = (CIE) standardObserver.next();
            X+=cie.xdash*spd.getValue(cie.wavelength);
            Y+=cie.ydash*spd.getValue(cie.wavelength);
            Z+=cie.zdash*spd.getValue(cie.wavelength);
            iterations++;
        }
        X/=iterations;
        Y/=iterations;
        Z/=iterations;
    }

    public String toString(){
        return "X: "+X+",\t Y: "+Y+",\t Z: "+Z;
    }
}
