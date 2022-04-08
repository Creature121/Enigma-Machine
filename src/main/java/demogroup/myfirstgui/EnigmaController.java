package demogroup.myfirstgui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.Arrays;

public class EnigmaController {

    /* Static variables that are used in the operation of the "EnigmaRotor Settings" Tab. */
    private static final String[] typesOfRotors = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "Identity"};
    private static final int[] positionsOfRotors = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
    private static final int[] ringSettingsOfRotors = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
    private static final String[] ogType = new String[]{null, null, null};

    /* Static variables that are used in the operation of the "EnigmaPlugBoard Settings" Tab. */
    private static final Button[] btnsToPlug = new Button[2];
    private static int btnIndex = 0;
    private static final ArrayList<ArrayList<Object>> pluggedLetters = new ArrayList<>();
    private static final ArrayList<Object> pairOfLetters = new ArrayList<>();
    private static boolean pairSelected = false;
    private static final String selectedLetterStyle = "-fx-background-color: green; -fx-border-style: dotted; -fx-border-width: 2; -fx-text-fill: white;";
    private static final String pairLetterStyle = "-fx-background-color: red; -fx-border-style: dotted; -fx-border-width: 2; -fx-text-fill: white;";
    private static final String normalLetterStyle = "-fx-background-color: silver; -fx-border-style: dotted; -fx-border-width: 2;";
    private static final String pluggedLetterStyle = "-fx-background-color: grey; -fx-border-style: dotted; -fx-border-width: 2;";

    /* Static variables used in the operation of the "EnigmaReflector Settings" Tab. */
    private static final String[] typesOfReflectors = new String[]{"B", "C", "Identity"};

    // Should I change these variable names, because they aren't actually Scene objects?
    /* The two components of the EnigmaApp that I switch between as screens. */
    @FXML
    private VBox mainScene;
    @FXML
    private TabPane settingsScene;

    /* These components belong to the "mainScene" VBox. */
    @FXML
    private Label leftRotorWindowLabel;
    @FXML
    private Label middleRotorWindowLabel;
    @FXML
    private Label rightRotorWindowLabel;
    @FXML
    private Label leftRotorTypeLabel;
    @FXML
    private Label middleRotorTypeLabel;
    @FXML
    private Label rightRotorTypeLabel;
    @FXML
    private Label leftRotorRingSettingLabel;
    @FXML
    private Label middleRotorRingSettingLabel;
    @FXML
    private Label rightRotorRingSettingLabel;
    @FXML
    private TextField inputTextField;
    @FXML
    private TextField outputTextField;

    /* These components belong to the "EnigmaRotor Settings" Tab. */
    @FXML
    private ComboBox<Object> leftRotorTypeComboBox;
    @FXML
    private ComboBox<Object> leftRotorStartPositionComboBox;
    @FXML
    private ComboBox<Object> leftRotorRingSettingComboBox;
    @FXML
    private ComboBox<Object> middleRotorTypeComboBox;
    @FXML
    private ComboBox<Object> middleRotorStartPositionComboBox;
    @FXML
    private ComboBox<Object> middleRotorRingSettingComboBox;
    @FXML
    private ComboBox<Object> rightRotorTypeComboBox;
    @FXML
    private ComboBox<Object> rightRotorStartPositionComboBox;
    @FXML
    private ComboBox<Object> rightRotorRingSettingComboBox;

    /* These components belong to the "EnigmaPlugBoard Settings" Tab. */
    @FXML
    private Label plugBoardLabel;
    @FXML
    private Button plugBtn;
    @FXML
    private Button unPlugBtn;

    /* This component belongs to the "EnigmaReflector Settings" Tab. */
    @FXML
    private ComboBox<Object> reflectorTypeComboBox;

    /* These functions are linked the 'Settings' Button and the 'Return' Tab, respectively. */
    /* These are the functions that allows us to switch the screens between "mainScene" and "settingsScene". */
    @FXML
    public void goToSettings()
    {
        /* This function switches from the "mainScene" VBox to the "settingsScene" TabPane. */
        /* It does this by turning "mainScene" invisible, and "settingsScene" visible. */
        /* This function is linked to the 'setOnAction' property of the 'Settings' Button that is seen on the "mainScene". */

        mainScene.setVisible(false);
        settingsScene.setVisible(true);
    }
    @FXML
    public void goToMain()
    {
        /* This function switches from the "settingsScene" TabPane to the "mainScene" VBox. */
        /* It does this by turning "settingsScene" invisible, and "mainScene" visible. */
        /* It also has another functionality. */
        /* Basically, if the user has changed any settings in the "settingsScene", */
        /* this function will save those new settings, and also alter the "mainScene" if needed. */
        /* This function is linked to the 'onSelectionChanged' property of the "Return" Tab that is seen on the "settingsScene". */


        leftRotorWindowLabel.setText((String) leftRotorStartPositionComboBox.getValue());           // Here, you can see that if the user changed...
        middleRotorWindowLabel.setText((String) middleRotorStartPositionComboBox.getValue());       // ...the starting positions of the rotors in the "EnigmaRotor Settings" Tab,
        rightRotorWindowLabel.setText((String) rightRotorStartPositionComboBox.getValue());         // these components in the "mainScene" will reflect those changes.

        leftRotorTypeLabel.setText((String) leftRotorTypeComboBox.getValue());                      // Here, you can see that if the user changed...
        middleRotorTypeLabel.setText((String) middleRotorTypeComboBox.getValue());                  // ...the types of the rotors in the "EnigmaRotor Settings" Tab,
        rightRotorTypeLabel.setText((String) rightRotorTypeComboBox.getValue());                    // these components in the "mainScene" will reflect those changes.

        leftRotorRingSettingLabel.setText((String) leftRotorRingSettingComboBox.getValue());        // Here, you can see that if the user changed...
        middleRotorRingSettingLabel.setText((String) middleRotorRingSettingComboBox.getValue());    // ...the ring settings of the rotors in the "EnigmaRotor Settings" Tab,
        rightRotorRingSettingLabel.setText((String) rightRotorRingSettingComboBox.getValue());      // these components in the "mainScene" will reflect those changes.

        settingsScene.setVisible(false);
        mainScene.setVisible(true);

        settingsScene.getSelectionModel().select(0); // Since the "Return" on the "settingsScene" TabPane is an empty Tab...
                                                        // ...this line ensure that the user will not see a blank Tab when he returns back to the "settingsScene".
    }

    /* These functions are used in the operation of the "EnigmaRotor Settings" Tab. */
    /* More specifically, it is to implement a certain functionality to the (x)RotorTypeComboBoxes. */
    /* That certain functionality is: a single rotor type must be used only once. */
    @FXML
    public void rememberType()
    {
        /* This function saves the currently selected types of the 3 rotors...*/
        /* ...whenever the user opens the dropdown menu for sny of the RotorTypeComboBoxes. */
        /* This function is linked to the 'onShowing' property of the...*/
        /* 'leftRotorTypeComboBox', 'middleRotorTypeComboBox' and 'rightRotorTypeComboBox' ComboBoxes.*/

        ogType[0] = String.valueOf(leftRotorTypeComboBox.getSelectionModel().getSelectedItem());
        ogType[1] = String.valueOf(middleRotorTypeComboBox.getSelectionModel().getSelectedItem());
        ogType[2] = String.valueOf(rightRotorTypeComboBox.getSelectionModel().getSelectedItem());
    }
    @FXML
    public void leftRotorTypeCheck()
    {
        /* This function triggers after the user has selected a value in dropdown menu of the 'leftRotorTypeComboBox' ComboBox. */
        /* After the selection, the function checks if any of the other 2 RotorTypeComboBoxes has the same value. */
        /* If true, then this function sets that particular RotorTypeComboBox's value with the former value of the 'leftRotorTypeComboBox' ComboBox. */
        /* This function is linked to the 'onHiding' property of the 'leftRotorTypeComboBox' ComboBox. */

        String leftRotorType = String.valueOf(leftRotorTypeComboBox.getSelectionModel().getSelectedItem());      // Grabs all the current values...
        String middleRotorType = String.valueOf(middleRotorTypeComboBox.getSelectionModel().getSelectedItem());  // ...of all the...
        String rightRotorType = String.valueOf(rightRotorTypeComboBox.getSelectionModel().getSelectedItem());    // ...RotorTypeComboBoxes.

        // If-statement to check if 'middleRotorType' or the 'rightRotorType' is equal to the 'leftRotorType'.
        // If so, set their respective RotorTypeComboBoxes to the former value of the 'leftRotorTypeComboBox' ComboBox.
        if (leftRotorType.equals(middleRotorType))
        {
            middleRotorTypeComboBox.setValue(ogType[0]);

        } else if (leftRotorType.equals(rightRotorType))
        {
            System.out.println("Left -> Middle");
            rightRotorTypeComboBox.setValue(ogType[0]);
        }

        // Reset the 'ogType' String array.
        ogType[0] = null;
        ogType[1] = null;
        ogType[2] = null;
    }
    @FXML
    public void middleRotorTypeCheck()
    {
        /* This function triggers after the user has selected a value in dropdown menu of the 'middleRotorTypeComboBox' ComboBox. */
        /* After the selection, the function checks if any of the other 2 RotorTypeComboBoxes has the same value. */
        /* If true, then this function sets that particular RotorTypeComboBox's value with the former value of the 'middleRotorTypeComboBox' ComboBox. */
        /* This function is linked to the 'onHiding' property of the 'middleRotorTypeComboBox' ComboBox. */

        String leftRotorType = String.valueOf(leftRotorTypeComboBox.getSelectionModel().getSelectedItem());      // Grabs all the current values...
        String middleRotorType = String.valueOf(middleRotorTypeComboBox.getSelectionModel().getSelectedItem());  // ...of all the...
        String rightRotorType = String.valueOf(rightRotorTypeComboBox.getSelectionModel().getSelectedItem());    // ...RotorTypeComboBoxes.

        // If-statement to check if 'leftRotorType' or the 'rightRotorType' is equal to the 'middleRotorType'.
        // If so, set their respective RotorTypeComboBoxes to the former value of the 'middleRotorTypeComboBox' ComboBox.
        if (middleRotorType.equals(leftRotorType))
        {
            leftRotorTypeComboBox.setValue(ogType[1]);
        } else if (middleRotorType.equals(rightRotorType))
        {
            rightRotorTypeComboBox.setValue(ogType[1]);
        }

        // Reset the 'ogType' String array.
        ogType[0] = null;
        ogType[1] = null;
        ogType[2] = null;
    }
    @FXML
    public void rightRotorTypeCheck()
    {
        /* This function triggers after the user has selected a value in dropdown menu of the 'rightRotorTypeComboBox' ComboBox. */
        /* After the selection, the function checks if any of the other 2 RotorTypeComboBoxes has the same value. */
        /* If true, then this function sets that particular RotorTypeComboBox's value with the former value of the 'rightRotorTypeComboBox' ComboBox. */
        /* This function is linked to the 'onHiding' property of the 'rightRotorTypeComboBox' ComboBox. */

        String leftRotorType = String.valueOf(leftRotorTypeComboBox.getSelectionModel().getSelectedItem());         // Grabs all the current values...
        String middleRotorType = String.valueOf(middleRotorTypeComboBox.getSelectionModel().getSelectedItem());     // ...of all the...
        String rightRotorType = String.valueOf(rightRotorTypeComboBox.getSelectionModel().getSelectedItem());       // ...RotorTypeComboBoxes.

        // If-statement to check if 'leftRotorType' or the 'middleRotorType' is equal to the 'rightRotorType'.
        // If so, set their respective RotorTypeComboBoxes to the former value of the 'rightRotorTypeComboBox' ComboBox.
        if (rightRotorType.equals(leftRotorType))
        {
            leftRotorTypeComboBox.setValue(ogType[2]);
        } else if (rightRotorType.equals(middleRotorType))
        {
            middleRotorTypeComboBox.setValue(ogType[2]);
        }

        // Reset the 'ogType' String array.
        ogType[0] = null;
        ogType[1] = null;
        ogType[2] = null;
    }

    /* These functions are used in the operation of the "EnigmaPlugBoard Settings" Tab. */
    @FXML
    public void plugLetters()
    {
        /* This function triggers when you press the 'Plug' button in the "EnigmaPlugBoard Settings" Tab. */
        /* This function is designed to emulate the act of plugging together letters in the plug board of an EnigmaMachine Machine. */
        /* This function takes the pair of currently selected letters, add them to the array of 'pluggedLetters'... */
        /* and generates a String that shows all the plugged letters, and prints that String onto the 'plugBoardLabel' Label. */

        ArrayList<Object> pair = new ArrayList<>(Arrays.asList(btnsToPlug[0], btnsToPlug[1]));    // Since the array 'pluggedLetters' require an array of ArrayList<Object> datatype, this line generates this accordingly.
        pluggedLetters.add(pair);

        // This part uses a StringBuilder and a for-loop to dynamically generate a String to print it onto the 'plugBoardLabel' Label.
        StringBuilder text = new StringBuilder();
        for (ArrayList<Object> i : pluggedLetters)
        {
            Button firstButton = (Button) i.get(0);
            Button secondButton = (Button) i.get(1);

            text.append(String.format(" |%s<->%s| ", firstButton.getText(), secondButton.getText()));
        }
        plugBoardLabel.setText(text.toString());

        // Change the style of the buttons whose letters got added to the 'pluggedLetters' array.
        btnsToPlug[0].setStyle(pluggedLetterStyle);
        btnsToPlug[1].setStyle(pluggedLetterStyle);

        // Resetting the 'btnsToPlug' array and the btnIndex.
        btnsToPlug[0] = btnsToPlug[1] = null;
        btnIndex = 0;

    }
    @FXML
    public void unPlugLetters()
    {
        /* This function triggers when you press the 'Unplug' button in the "EnigmaPlugBoard Settings" Tab. */
        /* This function is designed to emulate the act of unplugging letters in the plug board of an EnigmaMachine Machine. */
        /* This function takes the currently selected pair of plugged letters, remove them from the 'pluggedLetters' array... */
        /* and regenerates the String that shows all the plugged letters, and prints the String onto the 'plugBoardLabel' Label. */

        // Removes the selected pair of plugged letters.
        pluggedLetters.remove(pairOfLetters);

        // Uses a StringBuilder and a for-loop to regenerate the display String, and print it onto the 'plugBoardLabel' Label.
        StringBuilder text = new StringBuilder();
        for (ArrayList<Object> i : pluggedLetters)
        {
            Button firstButton = (Button) i.get(0);
            Button secondButton = (Button) i.get(1);

            text.append(String.format(" |%s<->%s| ", firstButton.getText(), secondButton.getText()));
        }
        plugBoardLabel.setText(text.toString());

        // Set the style of the buttons of the pair of plugged letters back to normal.
        ((Button) (pairOfLetters.get(0))).setStyle(normalLetterStyle);
        ((Button) (pairOfLetters.get(1))).setStyle(normalLetterStyle);

        // Reset the 'pairOfLetters' array and 'pairSelected'.
        pairOfLetters.set(0, null);
        pairOfLetters.set(1, null);
        pairSelected=false;
    }
    @FXML
    public void btnClick(ActionEvent event)
    {
        /* This function triggers whenever one of the 26 buttons (a button for each letter) on the "EnigmaPlugBoard Settings" Tab is pressed. */
        /* This function implements a number of functionalities, such as: */
        /*      - allowing the user to select up to two buttons and highlighting the currently selected buttons. */
        /*      - preventing the user to have more than 2 buttons selected at the same time. */
        /*      - allowing the user to deselect a selected button by clicking it again. */
        /*      - allowing the user to press a single letter of a previously plugged pair, selecting and highlighting both the letters in the pair.*/
        /*      - preventing the user to select more than a single pair of previously plugged letters. */
        /*      - deselecting the pair of plugged letters after clicking on a regular letter. */
        /* This function is linked to the 'onAction' property of all the 26 letter buttons on the "Plygboard Settings" Tab.*/

        Button sourceButton = (Button) event.getSource();       // To identify which button was pressed, and store it in 'sourceButton'.
        String sourceButtonStyle = sourceButton.getStyle();     // Get the style string of the button, which differentiates a button based on whether it was non-selected, selected or plugged letter.


        // If the user had previously selected a pair of plugged letters before, deselect them.
        if (pairSelected)
        {
            Button button1 = (Button) pairOfLetters.get(0);
            Button button2 = (Button) pairOfLetters.get(1);
            button1.setStyle(pluggedLetterStyle);
            button2.setStyle(pluggedLetterStyle);
            pairOfLetters.set(0, null);
            pairOfLetters.set(1, null);
            pairSelected = false;
        }

        // This is where the differentiation of the buttons come in.
        // Throughout this function, the style of the pressed button changes ( through the .setStyle() )...
        // based on whether the user wanted to select, deselect, etc. the letters.
        switch (sourceButtonStyle) {
            case normalLetterStyle:          // If the button pressed is not currently selected.
                if (btnIndex != 2) {              // If the 'btnsToPlug' Button array is not full.
                    sourceButton.setStyle(selectedLetterStyle);
                    btnsToPlug[btnIndex] = sourceButton;
                    btnIndex++;

                } else //If it is full...
                {
                    // Switch out the button in the 0th index with the button in the 1st index...
                    // ...and replace the button in the 1st index with the latest button.
                    btnsToPlug[0].setStyle(normalLetterStyle);
                    sourceButton.setStyle(selectedLetterStyle);
                    btnsToPlug[0] = btnsToPlug[1];
                    btnsToPlug[1] = sourceButton;

                }
                break;
            case selectedLetterStyle:      // If the button pressed is currently selected.
                if (btnsToPlug[0].equals(sourceButton))     // If the pressed button is in the 0th index of 'btnsToPlug'...
                {
                    // Replace the button in the 0th index, with the button in the 1st index.
                    btnsToPlug[0].setStyle(normalLetterStyle);
                    btnsToPlug[0] = btnsToPlug[1];

                } else if (btnsToPlug[1].equals(sourceButton))  // If the pressed button is in the 1st index of 'btnsToPlug'...
                {
                    // Change the style of the button in the 1st index.
                    btnsToPlug[1].setStyle(normalLetterStyle);
                }

                btnsToPlug[1] = null; // Remove the button in the 1st index of 'btnsToPlug'.
                btnIndex--; // Decrement the 'btnIndex', as the 'btnsToPlug' is no longer full.
                break;
            case pluggedLetterStyle: // If the button pressed is one of the plugged pairs.

                //Iterate through array the stores all the plugged pairs.
                for (ArrayList<Object> i : pluggedLetters)
                {
                    Button firstButton = (Button) i.get(0);
                    Button secondButton = (Button) i.get(1);

                    if (firstButton.equals(sourceButton) || secondButton.equals(sourceButton))
                    {
                        if (!(pairSelected)) // If a plugged pair have not been previously selected...
                        {
                            // Select the pressed button, and it's pair, and highlight them.
                            firstButton.setStyle(pairLetterStyle);
                            secondButton.setStyle(pairLetterStyle);
                            pairOfLetters.set(0, firstButton);
                            pairOfLetters.set(1, secondButton);
                            pairSelected = true;

                        } else // If a plugged pair have been previously selected...
                        {
                            // Deselect the previous pair,
                            // replace them with the currently selected button, and it's pair, and highlight them.
                            Button formerFirstButton = (Button) pairOfLetters.get(0);
                            Button formerSecondButton = (Button) pairOfLetters.get(1);

                            formerFirstButton.setStyle(pluggedLetterStyle);
                            formerSecondButton.setStyle(pluggedLetterStyle);
                            firstButton.setStyle(pairLetterStyle);
                            secondButton.setStyle(pairLetterStyle);
                            pairOfLetters.set(0, firstButton);
                            pairOfLetters.set(1, secondButton);
                        }
                    }
                }
                break;
        }


        plugBtn.setDisable(btnIndex != 2);      // This line ensures that the 'Plug' Button is disabled until a pair of non-plugged letters have been selected.
        unPlugBtn.setDisable(!pairSelected);    // This line ensures that the 'Unplug' Button is disabled until a pair of plugged letters have been selected.

    }


    /* This function is used in the "mainScene" VBox. */
    @FXML
    public void encrypt()
    {
        /* This function triggers when you press the 'Encrypt' Button on the "mainScene".*/
        /* This function generates an EnigmaMachine instance, using the values it extracts from the "settingsScene"... */
        /* ...takes the text present in the 'inputTextField', and uses it in the .encrypt() method of the EnigmaMachine instance. */
        /* The .encrypt() method returns a 'cipherText', which is then printed out onto 'outputFieldTextField'. */
        /* Also, since the EnigmaMachine instance is an emulation of the real EnigmaMachine Machine... */
        /* The positions of its rotors will change after running its .encrypt() method.*/
        /* So this function also reflects those changes on the "mainScene" and "settingsScene". */

        // Extracts the types of the 3 rotors and store them in variables.
        String leftRotorType =  (String) (leftRotorTypeComboBox.getValue());
        String middleRotorType =  (String) (middleRotorTypeComboBox.getValue());
        String rightRotorType =  (String) (rightRotorTypeComboBox.getValue());
        String[] rotors = new String[]{leftRotorType, middleRotorType, rightRotorType};

        // Extracts the start positions of the 3 rotors and store them in variables.
        int leftRotorStartPosition =  ( ((String)(leftRotorStartPositionComboBox.getValue())).charAt(0) - 65 );
        int middleRotorStartPosition =  ( (((String)(middleRotorStartPositionComboBox.getValue()))).charAt(0) - 65);
        int rightRotorStartPosition =  ( (((String)(rightRotorStartPositionComboBox.getValue()))).charAt(0) -65 );
        int[] rotorStartPositions = new int[]{leftRotorStartPosition, middleRotorStartPosition, rightRotorStartPosition};

        // Extracts the ring settings of the 3 rotors and store them in variables.
        int leftRotorRingSetting =  Integer.parseInt( ((String) (leftRotorRingSettingComboBox.getValue())).split("-")[1] );
        int middleRotorRingSetting =  Integer.parseInt( ((String) (middleRotorRingSettingComboBox.getValue())).split("-")[1] );
        int rightRotorRingSetting =  Integer.parseInt( ((String) (rightRotorRingSettingComboBox.getValue())).split("-")[1] );
        int[] rotorRingSettings = new int[]{leftRotorRingSetting, middleRotorRingSetting, rightRotorRingSetting};

        // Extract the type of the enigmaReflector and store it in a variable.
        String reflector = (String) (reflectorTypeComboBox.getValue());

        // Generate the 'plugConnection' String using the 'pluggedLetters' array in the correct format that the EnigmaMachine instance requires.
        StringBuilder plugBoardConnectionsBuilder = new StringBuilder();
        for (ArrayList<Object> i : pluggedLetters)
        {
            Button btn1 = (Button) i.get(0);
            Button btn2 = (Button) i.get(1);
            plugBoardConnectionsBuilder.append(String.format("%s%s ", btn1.getText(), btn2.getText()));
        }
        System.out.println(plugBoardConnectionsBuilder.toString().trim());

        // The instantiation of the EnigmaMachine object: enigmaMachine
        EnigmaMachine enigmaMachine = new EnigmaMachine(rotors, reflector, rotorStartPositions,rotorRingSettings,plugBoardConnectionsBuilder.toString().trim());

        // Send the text of 'inputTextField' through the enigmaMachine.encrypt() method...
        // and prints it onto the 'outputTextField'.
        String cipherText = enigmaMachine.encrypt(inputTextField.getText());
        outputTextField.setText(cipherText);

        // This part gets the new positions of the rotors from 'enigmaMachine', and store it in variables.
        int[] newRotorPositions = enigmaMachine.getRotorPositions();
        String leftRotorPosition = String.format("%s", Character.toString(newRotorPositions[0]+65));
        String middleRotorPosition = String.format("%s", Character.toString(newRotorPositions[1]+65));
        String rightRotorPosition = String.format("%s", Character.toString(newRotorPositions[2]+65));

        // Reflect the changed positions on the "mainScene".
        leftRotorWindowLabel.setText(leftRotorPosition);
        middleRotorWindowLabel.setText(middleRotorPosition);
        rightRotorWindowLabel.setText(rightRotorPosition);

        // Reflect the changed positions in the "EnigmaRotor Settings" Tab.
        leftRotorStartPositionComboBox.setValue(leftRotorPosition);
        middleRotorStartPositionComboBox.setValue(middleRotorPosition);
        rightRotorStartPositionComboBox.setValue(rightRotorPosition);

    }

    /* This function basically initialises all the default settings, and setsup the app with those default values. */
    public void initialize()
    {
        /* This function is a common function for all JavaFX applications. */
        /* It runs everytime the application starts up. */
        /* The things this function does: */
        /*      - set the respective values to be shown in all the ComboBoxes available in the application. */
        /*      - sets default values for all the ComboBoxes. */
        /*      - adds EventFilters to control the users input and navigation. */
        /*      - sets up the static variables that are used in the application's various operations. */
        /*      - ensures that the first screen the user see in "mainScene". */

        // Generates the items that need to be in the RotorTypeComboBoxes, RotorPositionComboBoxes and RotorRingSettingComboBoxes in the correct format.
        ObservableList<Object> rotorTypeList = FXCollections.observableArrayList(typesOfRotors);
        ObservableList<Object> rotorPositionList = FXCollections.observableArrayList();
        ObservableList<Object> rotorRingSettingList = FXCollections.observableArrayList();

        // Formatting the options of RotorPositionComboBox.
        for (int i : positionsOfRotors)
        {
            rotorPositionList.add(String.format("%s", Character.toString(i+65)));
        }
        
        // Formatting the options of RotorPositionComboBox.
        for (int i : ringSettingsOfRotors)
        {
            String numberPart;
            if (i < 10)
            {
                numberPart = String.format("0%d", (i+1));
            } else
            {
                numberPart = Integer.toString(i);
            }
            rotorRingSettingList.add(String.format("%s-%s", Character.toString(i+65), numberPart));
        }

        // The options for 'reflectorTypeComboBox' doesn't need to be formatted, so we just add it into the 'reflectorTypeList' array.
        ObservableList<Object> reflectorTypeList = FXCollections.observableArrayList(typesOfReflectors);


        //Implement mutually exclusive dropdowns.
        // Adding the options to the RotorTypeComboBoxes, and setting their defaults.
        leftRotorTypeComboBox.setItems(rotorTypeList);
        middleRotorTypeComboBox.setItems(rotorTypeList);
        rightRotorTypeComboBox.setItems(rotorTypeList);

        leftRotorTypeComboBox.setValue(rotorTypeList.get(0));
        middleRotorTypeComboBox.setValue(rotorTypeList.get(1));
        rightRotorTypeComboBox.setValue(rotorTypeList.get(2));


        // Adding the options to the RotorStartPositionComboBoxes, and setting their defaults.
        leftRotorStartPositionComboBox.setItems(rotorPositionList);
        middleRotorStartPositionComboBox.setItems(rotorPositionList);
        rightRotorStartPositionComboBox.setItems(rotorPositionList);

        leftRotorStartPositionComboBox.setValue(rotorPositionList.get(0));
        middleRotorStartPositionComboBox.setValue(rotorPositionList.get(0));
        rightRotorStartPositionComboBox.setValue(rotorPositionList.get(0));


        // Adding the options to the RotorRingSettingComboBoxes, and setting their defaults.
        leftRotorRingSettingComboBox.setItems(rotorRingSettingList);
        middleRotorRingSettingComboBox.setItems(rotorRingSettingList);
        rightRotorRingSettingComboBox.setItems(rotorRingSettingList);

        leftRotorRingSettingComboBox.setValue(rotorRingSettingList.get(0));
        middleRotorRingSettingComboBox.setValue(rotorRingSettingList.get(0));
        rightRotorRingSettingComboBox.setValue(rotorRingSettingList.get(0));

        // Adding the options to 'reflectorTypeComboBox', and setting its default.
        reflectorTypeComboBox.setItems(reflectorTypeList);
        reflectorTypeComboBox.setValue(reflectorTypeList.get(0));


        // Prevents user from using the arrow keys to switch between Tabs...
        // ...on the 'settingScene' TabPane (ending up hitting the 'Return' tab, making the navigation weird.).
        settingsScene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            event.consume();
        });

        // Set the prompt text of the inputTextField
        inputTextField.setPromptText("Letters will be switched to uppercase, and spaces with 'X'");

        // Prevents the user from inputting anything other than capital letters (using a TextFormatter).
        inputTextField.setTextFormatter(new TextFormatter<String> (change -> {

            String input = change.getText().replace(".", "").replace(" ", "X").replace("[!@#$%&*()_+=|<>?{}\\[\\]~-\\.]", "").toUpperCase();
            change.setText(input);
            return change;

        }));

        // Disables the 'Plug' and 'Unplug' Buttons on the 'EnigmaPlugBoard Settings' Tab.
        plugBtn.setDisable(true);
        unPlugBtn.setDisable(true);

        // Adding nulls to the 'pairOfLetters' array, so that it works as intended.
        pairOfLetters.add(null);
        pairOfLetters.add(null);

        goToMain(); // Triggers the goToMain() method to set up and display the "mainScene".
    }


}