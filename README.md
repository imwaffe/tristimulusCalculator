#TristimulusCalculator
Simple JAVA tool for calculating XYZ tristimulus values based on CIE 2° Standard Observer.<br>
This is intended for educational purpose only and should not be used for anything else.

##Usage
Once run, the user will be prompted to choice whether to create a random SPD or use an existing one.
The tool then outputs a string containing the three X, Y and Z values.<br>
SPDs are saved in CSV files inside **/data/spectrums/** folder.<br>
Transfer functions (x̅,y̅,z̅) are located in **/data/cie.csv**.

###SPD files structure
SPD are stored as CSV files. Each line is a sample, the first value is the wavelength (in nanometers),
while the second one is the intensity value at that given wavelength.<br>
Basically the same applies to the **/data/cie.csv** files, with the only difference being the
presence of four columns: the second, third and fourth columns corresponds with, respectively,
x̅, y̅, and z̅ values for each wavelength.<br>
The transfer functions are sampled between 390nm and 830nm in 1nm steps, so those are the actual
upper and lower bounds of the SPD that the tool is able to process.

##Dependencies
xchart (org.knowm.xchart) library is used in order to plot the SPD graph.

##To do
- Modify the RandomGenerator in SPD class in order to create random beta distributions, instead
of completely random values.
- Add a method to the TripletCalculator class that returns X, Y and Z values as double values