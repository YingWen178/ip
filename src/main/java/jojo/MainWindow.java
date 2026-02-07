package jojo;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private JoJo jojo;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image jojoImage = new Image(this.getClass().getResourceAsStream("/images/JoJo.png"));

    /**
     * Initializes the controller. Binds the scroll pane to the dialog container's height
     * and adds a welcome message from JoJo.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.prefWidthProperty().bind(scrollPane.widthProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getJoJoDialog("YARE YARE DAZE... I am JoJo. What do you want?", jojoImage)
        );
    }

    /**
     * Injects the JoJo instance.
     *
     * @param j The JoJo instance to be injected.
     */
    public void setJoJo(JoJo j) {
        jojo = j;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing JoJo's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = jojo.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getJoJoDialog(response, jojoImage)
        );
        userInput.clear();

        if (jojo.isExit(input)) {
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}
