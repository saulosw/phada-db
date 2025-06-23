module br.com.saulo.phadadb {
    requires javafx.controls;
    requires javafx.fxml;

    opens br.com.saulo.phadadb to javafx.fxml;
    exports br.com.saulo.phadadb;
}
