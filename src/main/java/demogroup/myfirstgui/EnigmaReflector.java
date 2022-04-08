package demogroup.myfirstgui;

/* This 'EnigmaReflector' class emulates a reflector that would be used in a real Enigma Machine. */

public class EnigmaReflector {
    protected int[] forwardPath;

    // Public constructor of EnigmaReflector.
    public EnigmaReflector(String forwardPathString)
    {
        this.forwardPath = getWiring(forwardPathString);
    }

    public static EnigmaReflector create(String name) {
        /* This create() method makes it simpler to generate a EnigmaReflector instance... */
        /* ... based on its type. */

        switch (name) {
            case "B":
                return new EnigmaReflector("YRUHQSLDPXNGOKMIEBFZCWVJAT");
            case "C":
                return new EnigmaReflector("FVPJIAOYEDRZXWGCTKUQSBNMHL");
            default:
                return new EnigmaReflector("ZYXWVUTSRQPONMLKJIHGFEDCBA");
        }
    }

    // Coverts the String 'forwardPathString' given in the constructor, into an integer array, to make it easier to work with.
    protected static int[] getWiring(String forwardPathString) {
        char[] charWiring = forwardPathString.toCharArray();
        int[] wiring = new int[charWiring.length];

        for (int i = 0; i < charWiring.length; i++)
        {
            wiring[i] = charWiring[i] - 65;
        }

        return wiring;
    }

    // This function passes the inputted character into the EnigmaReflector,
    public int forward(int c) {
        return this.forwardPath[c];
    }

}
