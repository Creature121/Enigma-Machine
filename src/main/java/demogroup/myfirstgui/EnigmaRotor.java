package demogroup.myfirstgui;

/* This 'EnigmaRotor' class emulates a rotor that would be used in a real Enigma Machine. */

public class EnigmaRotor
{
    protected String name;          // i.e., The EnigmaRotor type.
    protected int[] forwardPath;    // The mapping when the character is going forward, toward the enigmaReflector.
    protected int[] backwardPath;   // The mapping when the character is going backward, after bouncing off of the enigmaReflector.
    protected int rotorPosition;    // The rotor's current position.
    protected int notchPosition;    // The position of the rotor's notch.
    protected int ringSetting;      // i.e., The rotor's offset.

    // Public construtor for the EnigmaRotor
    public EnigmaRotor(String name, String mapping, int rotorPosition, int notchPosition, int ringSetting)
    {
        this.name = name;
        this.forwardPath = mappingToForwardPath(mapping);
        this.backwardPath = mappingToBackwardPath(this.forwardPath);
        this.rotorPosition = rotorPosition;
        this.notchPosition = notchPosition;
        this.ringSetting = ringSetting;
    }

    public static EnigmaRotor create(String name, int rotorPosition, int ringSetting)
    {
        /* This create() method makes it simpler to generate a EnigmaRotor instance... */
        /* ...since based on the rotor type, most of its settings are fixed. */

        switch (name)
        {
            case "I":
                return new EnigmaRotor("I", "EKMFLGDQVZNTOWYHXUSPAIBRCJ", rotorPosition, 16, ringSetting);

            case "II":
                return new EnigmaRotor("II", "AJDKSIRUXBLHWTMCQGZNPYFVOE", rotorPosition, 4, ringSetting);

            case "III":
                return new EnigmaRotor("III", "BDFHJLCPRTXVZNYEIWGAKMUSQO", rotorPosition, 21, ringSetting);

            case "IV":
                return new EnigmaRotor("IV", "ESOVPZJAYQUIRHXLNFTGKDCMWB", rotorPosition, 9, ringSetting);

            case "V":
                return new EnigmaRotor("V", "VZBRGITYUPSDNHLXAWMJQOFECK", rotorPosition, 25, ringSetting);

            case "VI":
                return new EnigmaRotor("VI", "JPGVOUMFYQBENHZRDKASXLICTW", rotorPosition, 0, ringSetting) {

                    // This override is necessary because Type VI is a later model that had two notch positions.
                    @Override
                    public boolean isAtNotch() {
                        return this.rotorPosition == 12 || this.rotorPosition == 25;
                    }
                };

            case "VII":
                return new EnigmaRotor("VII", "NZJHGRCXMYSWBOUFAIVLPEKQDT", rotorPosition, 0, ringSetting) {

                    // This override is necessary because Type VII is a later model that had two notch positions.
                    @Override
                    public boolean isAtNotch() {
                        return this.rotorPosition == 12 || this.rotorPosition == 25;
                    }
                };

            case "VIII":
                return new EnigmaRotor("VIII", "FKQHTLXOCBJSPDZRAMEWNIUYGV", rotorPosition, 0, ringSetting) {

                    // This override is necessary because Type VIII is a later model that had two notch positions.
                    @Override
                    public boolean isAtNotch() {
                        return this.rotorPosition == 12 || this.rotorPosition == 25;
                    }
                };

            default:
                return new EnigmaRotor("Identity", "ABCDEFGHIJKLMNOPQRSTUVWXYZ", rotorPosition, 0, ringSetting);
        }
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return rotorPosition;
    }

    // Coverts the String 'mapping' given in the constructor, into an integer array, to make it easier to work with.
    protected static int[] mappingToForwardPath(String encoding)
    {
        char[] forwardPass = encoding.toCharArray();
        int[] forwardPassInNumbers = new int[forwardPass.length];

        for (int i = 0; i < forwardPass.length; i++)
        {
            forwardPassInNumbers[i] = forwardPass[i] - 65;
        }

        return forwardPassInNumbers;
    }

    // Uses the EnigmaRotor's 'forwardPath' to generate the 'backwardPath' integer array.
    protected static int[] mappingToBackwardPath(int[] forwardPath)
    {
        int[] backwardPassInNumbers = new int[forwardPath.length];
        for (int i = 0; i < forwardPath.length; i++) {
            int forward = forwardPath[i];
            backwardPassInNumbers[forward] = i;
        }
        return backwardPassInNumbers;
    }

    // The actual formula that is used to emulate the substitution of the input letter to the output letter...
    // ...in a real EnigmaMachine Machine.
    protected static int encipher(int k, int pos, int ring, int[] mapping)
    {
        int shift = pos - ring;                                     // Found this formula online.
        return (mapping[(k + shift + 26) % 26] - shift + 26) % 26;  // Will find the link and paste it here.
    }

    // This is used when the character is passing through the rotor, and is heading towards the enigmaReflector.
    public int forward(int c) {
        return encipher(c, this.rotorPosition, this.ringSetting, this.forwardPath);
    }

    // This is used when the character is passing through the rotor, but was actually reflected by the enigmaReflector.
    public int backward(int c) {
        return encipher(c, this.rotorPosition, this.ringSetting, this.backwardPath);
    }

    // Returns True if the EnigmaRotor is at it's notch position, else returns False.
    public boolean isAtNotch() {
        return this.notchPosition == this.rotorPosition;
    }

    // Method that emulates the turnover of an actual rotor.
    public void turnover() {
        this.rotorPosition = (this.rotorPosition + 1) % 26;
    }


}