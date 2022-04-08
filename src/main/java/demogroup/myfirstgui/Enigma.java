package demogroup.myfirstgui;

public class Enigma {
    // 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25
    // A B C D E F G H I J K  L  M  N  O  P  Q  R  S  T  U  V  W  X  Y  Z
    public EnigmaRotor leftRotor;
    public EnigmaRotor middleRotor;
    public EnigmaRotor rightRotor;

    public EnigmaReflector reflector;
    public EnigmaPlugBoard plugboard;


    public Enigma(String[] rotors, String reflector, int[] rotorPositions, int[] ringSettings, String plugBoardConnections) {
        this.leftRotor = EnigmaRotor.create(rotors[0], rotorPositions[0], ringSettings[0]);
        this.middleRotor = EnigmaRotor.create(rotors[1], rotorPositions[1], ringSettings[1]);
        this.rightRotor = EnigmaRotor.create(rotors[2], rotorPositions[2], ringSettings[2]);
        this.reflector = EnigmaReflector.create(reflector);
        this.plugboard = new EnigmaPlugBoard(plugBoardConnections);
    }

    public void rotate() {
        // If middle rotor notch - double-stepping
        if (middleRotor.isAtNotch()) {
            middleRotor.turnover();
            leftRotor.turnover();
        }
        // If left-rotor notch
        else if (rightRotor.isAtNotch()) {
            middleRotor.turnover();
        }

        // Increment right-most rotor
        rightRotor.turnover();
    }

    public int encrypt(int c) {

        rotate();

        // Plug board in
        c = this.plugboard.forward(c);

        // Right to left
        int c1 = rightRotor.forward(c);
        int c2 = middleRotor.forward(c1);
        int c3 = leftRotor.forward(c2);

        // Reflector
        int c4 = reflector.forward(c3);

        // Left to right
        int c5 = leftRotor.backward(c4);
        int c6 = middleRotor.backward(c5);
        int c7 = rightRotor.backward(c6);

        // Plug board out
        c7 = plugboard.forward(c7);


        return c7;
    }

    public char encrypt(char c) {
        return (char)(this.encrypt(c - 65) + 65);
    }

    public String encrypt(String input) {
        char[] inputArr = input.toCharArray();
        char[] output = new char[inputArr.length];
        for (int i = 0; i < inputArr.length; i++) {
            output[i] = this.encrypt(inputArr[i]);
        }
        return new String(output);
    }

    public int[] getRotorPositions()
    {
        return new int[]{leftRotor.getPosition(), middleRotor.getPosition(), rightRotor.getPosition()};
    }
}
