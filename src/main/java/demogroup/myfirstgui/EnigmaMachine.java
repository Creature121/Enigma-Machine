package demogroup.myfirstgui;

public class EnigmaMachine
{

    public EnigmaRotor leftEnigmaRotor;
    public EnigmaRotor middleEnigmaRotor;
    public EnigmaRotor rightEnigmaRotor;

    public EnigmaReflector enigmaReflector;
    public EnigmaPlugBoard enigmaPlugBoard;


    public EnigmaMachine(String[] rotors, String reflector, int[] rotorPositions, int[] ringSettings, String plugBoardConnections)
    {
        this.leftEnigmaRotor = EnigmaRotor.create(rotors[0], rotorPositions[0], ringSettings[0]);
        this.middleEnigmaRotor = EnigmaRotor.create(rotors[1], rotorPositions[1], ringSettings[1]);
        this.rightEnigmaRotor = EnigmaRotor.create(rotors[2], rotorPositions[2], ringSettings[2]);
        this.enigmaReflector = EnigmaReflector.create(reflector);
        this.enigmaPlugBoard = new EnigmaPlugBoard(plugBoardConnections);
    }

    public void turnOverRotors()
    {
        // If middle rotor notch - double-stepping
        if (middleEnigmaRotor.isAtNotch())
        {
            middleEnigmaRotor.turnover();
            leftEnigmaRotor.turnover();
        }
        // If left-rotor notch
        else if (rightEnigmaRotor.isAtNotch())
        {
            middleEnigmaRotor.turnover();
        }

        // Increment right-most rotor
        rightEnigmaRotor.turnover();
    }

    public int encrypt(int inputCharacter)
    {

        turnOverRotors();

        // Plug board in.
        int characterAfterPlugBoardForward = this.enigmaPlugBoard.forward(inputCharacter);

        // Right to left rotors.
        int characterAfterRRotorForward = rightEnigmaRotor.forward(characterAfterPlugBoardForward);
        int characterAfterMRotorForward = middleEnigmaRotor.forward(characterAfterRRotorForward);
        int characterAfterLRotorForward = leftEnigmaRotor.forward(characterAfterMRotorForward);

        // Bounce off of EnigmaReflector.
        int characterAfterReflector = enigmaReflector.forward(characterAfterLRotorForward);

        // Left to right rotors.
        int characterAfterLRotorBackward = leftEnigmaRotor.backward(characterAfterReflector);
        int characterAfterMRotorBackward = middleEnigmaRotor.backward(characterAfterLRotorBackward);
        int characterAfterRRotorBackward = rightEnigmaRotor.backward(characterAfterMRotorBackward);

        // Plug board out.
        int characterAfterPlugBoardBackward = enigmaPlugBoard.forward(characterAfterRRotorBackward);


        return characterAfterPlugBoardBackward;
    }

    //
    public char encrypt(char c)
    {
        return (char)(this.encrypt(c - 65) + 65);
    }

    public String encrypt(String input)
    {
        char[] inputArr = input.toCharArray();
        char[] output = new char[inputArr.length];
        for (int i = 0; i < inputArr.length; i++) {
            output[i] = this.encrypt(inputArr[i]);
        }
        return new String(output);
    }

    public int[] getRotorPositions()
    {
        return new int[]{leftEnigmaRotor.getPosition(), middleEnigmaRotor.getPosition(), rightEnigmaRotor.getPosition()};
    }
}
