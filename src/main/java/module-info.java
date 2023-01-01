module com.example.semester_project {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.semester_project to javafx.fxml;
    exports com.example.semester_project;
}