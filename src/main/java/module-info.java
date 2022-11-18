module com.example.lab8 {
    requires javafx.controls;
    requires javafx.fxml;
            
                        requires org.kordamp.bootstrapfx.core;
            
    opens com.example.lab8 to javafx.fxml;
    exports com.example.lab8;
}