module br.com.saulo.phadadb {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires java.sql;
    requires com.zaxxer.hikari;
    
    requires bcrypt;
    requires de.jensd.fx.glyphs.fontawesome;

    opens br.com.saulo.phadadb to javafx.fxml, javafx.graphics;
    opens br.com.saulo.phadadb.controller to javafx.fxml;

    exports br.com.saulo.phadadb;
}