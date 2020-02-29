import java.awt.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

public class AddressBookPane extends BorderPane {
    //Declare text fields
    protected TextField personName = new TextField();
    protected TextField streetName = new TextField();
    protected TextField cityName = new TextField();
    protected TextField stateName = new TextField();
    protected TextField zipCode = new TextField();

    //Declare buttons
    protected Button addButton = new Button("Add");
    protected Button firstButton = new Button("First");
    protected Button nextButton = new Button("Next");
    protected Button prevButton = new Button("Previous");
    protected Button lastButton = new Button("Last");
    protected Button updateButton = new Button("Update");
    private FlowPane paneForInfo = new FlowPane(5.0D, 5.0D);
    private HBox paneForButtons = new HBox(5.0D);

    public AddressBookPane() {
        //Set text field position
        this.personName.setPrefColumnCount(23);
        this.streetName.setPrefColumnCount(23);
        this.cityName.setPrefColumnCount(8);
        this.stateName.setPrefColumnCount(2);
        this.zipCode.setPrefColumnCount(4);
        this.paneForInfo.setPadding(new Insets(15.0D, 15.0D, 5.0D, 15.0D));
        //Add all the buttons and the text fields to the pane
        this.paneForInfo.getChildren().addAll(new Node[]{new Label("Name*"), personName, new Label("Street*"), streetName, new Label("City*   "), cityName, new Label("State"), stateName, new Label("Zip"), zipCode});
        this.paneForButtons.getChildren().addAll(new Node[]{addButton, firstButton, nextButton, prevButton, lastButton, updateButton});
        this.paneForButtons.setAlignment(Pos.CENTER);
        this.setCenter(this.paneForInfo);
        this.setBottom(this.paneForButtons);
    }
}
