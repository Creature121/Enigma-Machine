module demogroup.myfirstgui {
    requires javafx.controls;
    requires javafx.fxml;


    opens demogroup.myfirstgui to javafx.fxml;
    exports demogroup.myfirstgui;
}