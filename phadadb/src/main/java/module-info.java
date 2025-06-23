module br.com.saulo.phadadb {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens br.com.saulo.phadadb to javafx.fxml, javafx.graphics;
    opens br.com.saulo.phadadb.controller to javafx.fxml;

    exports br.com.saulo.phadadb;
}
