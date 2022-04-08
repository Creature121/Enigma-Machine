package demogroup.myfirstgui;

import java.util.HashSet;
import java.util.Set;

/* This 'EnigmaPlugBoard' class emulates a plug board that would be used in a real Enigma Machine. */

public class EnigmaPlugBoard {
    private final int[] wiring;

    // Public constructor for EnigmaPlugBoard.
    public EnigmaPlugBoard(String connections)
    {
        this.wiring = decodePlugboard(connections);
    }

    // Passes the inputted character through the EnigmaPlugBoard.
    public int forward(int c) {
        return this.wiring[c];
    }

    // In case the user isn't using any of the EnigmaPlugBoard settings.
    private static int[] identityPlugboard() {
        int[] mapping = new int[26];
        for (int i = 0; i < 26; i++) {
            mapping[i] = i;
        }
        return mapping;
    }

    public static Set<Integer> getUnpluggedCharacters(String plugBoard)
    {
        // To store which character isn't linked to any character.
        Set<Integer> unpluggedCharacters = new HashSet<>();

        for (int i = 0; i < 26; i++)
        {
            unpluggedCharacters.add(i);
        }

        if (plugBoard.equals(""))
        {
            return unpluggedCharacters;
        }

        String[] pairings = plugBoard.split("[^a-zA-Z]");

        // Validate and create the mapping.
        for (String pair : pairings)
        {
            int firstCharacter = pair.charAt(0) - 65;
            int secondCharacter = pair.charAt(1) - 65;

            // Then remove them from the 'unpluggedCharacters' array, since they are actually plugged.
            unpluggedCharacters.remove(firstCharacter);
            unpluggedCharacters.remove(secondCharacter);
        }

        return unpluggedCharacters;
    }

    public static int[] decodePlugboard(String plugboard)
    {
        // In case the user haven't plugged any characters in the plugboard.
        if (plugboard == null || plugboard.equals(""))
        {
            return identityPlugboard();
        }

        String[] pairings = plugboard.split("[^a-zA-Z]");

        Set<Integer> pluggedCharacters = new HashSet<>(); // To store the plugged (i.e., linked) characters,
        int[] mapping = identityPlugboard(); // We will be editing this  'mapping' as we go forward.

        for (String pair : pairings)
        {
            if (pair.length() != 2)
                return identityPlugboard();

            int firstCharacter = pair.charAt(0) - 65;
            int secondCharacter = pair.charAt(1) - 65;

            // In case any character is "plugged" to itself (which isn't possible), just return the identityPlugBoard.
            if (pluggedCharacters.contains(firstCharacter) || pluggedCharacters.contains(secondCharacter))
            {
                return identityPlugboard();
            }

            pluggedCharacters.add(firstCharacter);
            pluggedCharacters.add(secondCharacter);

            mapping[firstCharacter] = secondCharacter;
            mapping[secondCharacter] = firstCharacter;
        }

        return mapping;
    }
}
