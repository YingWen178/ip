package jojo;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);

        // Circular profile picture
        // I used AI to help me with the Circle clipping logic for the ImageView.
        // The tool (GitHub Copilot) suggested using a Circle clip to ensure a
        // perfect circular crop regardless of the source image aspect ratio.
        // For the new "menacing" JoJo theme, I also asked ChatGPT for advice on
        // enhancing the CSS for a more dramatic look, which led to the use of
        // linear gradients and stronger drop shadows in style.css.
        Circle clip = new Circle(25, 25, 25);
        displayPicture.setClip(clip);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        // Space between image and text when flipped
        setSpacing(10);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.getStyleClass().add("user-label");
        db.setSpacing(10);
        return db;
    }

    public static DialogBox getJoJoDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.dialog.getStyleClass().add("jojo-label");
        return db;
    }

    public static DialogBox getErrorDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.dialog.getStyleClass().add("error-label");
        return db;
    }
}
