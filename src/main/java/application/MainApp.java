package application;
	
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Text-Editor Main Application
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	/**
	 * Called at start of application
	 * @param primaryStage the primary, main stage
	 */
	@Override
	public void start(Stage primaryStage) {
		
		this.primaryStage = primaryStage;
		
		this.primaryStage.setTitle("TextProApp");
		String iconFile = Objects.requireNonNull(MainApp.class.getClassLoader()
				.getResource("view/text_editor.png")).toExternalForm();
		this.primaryStage.getIcons().add(new Image(iconFile));
		
		try {
			// Load root layout from fxml

			URL fxmlFile = Objects.requireNonNull(MainApp.class.getClassLoader()
					.getResource("view/RootLayout.fxml"));
			FXMLLoader loader = new FXMLLoader(fxmlFile);

			rootLayout = loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            
            // min height and width calculated from components in TextAppLayout
            primaryStage.setMinHeight(430);
            primaryStage.setMinWidth(334);
            primaryStage.show();
          
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		showTextProApp();
	}
	
	/**
     * Shows the main TextApplication scene
     */
    private void showTextProApp() {
        try {
            // Load the fxml file and set into the center of the main layout

			URL fxmlFile = Objects.requireNonNull(MainApp.class.getClassLoader()
					.getResource("view/TextAppLayout.fxml"));
			FXMLLoader loader = new FXMLLoader(fxmlFile);
            
            HBox textProPage = loader.load();
            rootLayout.setCenter(textProPage);
            
            // Connect controller and main app
            TextProController controller = loader.getController();
            controller.setMainApp(this);
            
        } catch (IOException e) {
            // Exception gets thrown if the fxml file could not be loaded
            e.printStackTrace();
        }
    }

    
    /**
     * Shows dialog for user input error
     * @param inErr - message to dispaly
     */
    public void showInputErrorDialog(String inErr) {
    	Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Input Error");
		alert.setContentText(inErr);

		alert.showAndWait();
    }
    
    /**
     * Displays dialog that allows user to select local text file to display in TextArea
     * @param ta - reference to TextArea to display loaded text file
     */
    public void showLoadFileDialog(AutoSpellingTextArea ta) {
    	try {
    		// Load the fxml file and create a new stage for the popup

			URL fxmlFile = Objects.requireNonNull(MainApp.class.getClassLoader()
					.getResource("view/LoadFileLayout.fxml"));
			FXMLLoader loader = new FXMLLoader(fxmlFile);

			VBox page = loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Load File");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set reference to stage in controller
			LoadFileDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			
			// give controller reference to text area to load file into
			controller.setTextArea(ta);

			// Show the dialog and wait until the user closes it
		    dialogStage.showAndWait();

    	} catch (IOException e) {
    		// Exception gets thrown if the fxml file could not be loaded
    		e.printStackTrace();
    	}
    }


	/**
	 * Edit distance dialog
	 * @param selectedText the input selected text
	 */
	public void showEditDistanceDialog(String selectedText) {
    	try {
    		// Load the fxml file and create a new stage for the popup

			URL fxmlFile = Objects.requireNonNull(MainApp.class.getClassLoader()
					.getResource("view/EditDistanceLayout.fxml"));
			FXMLLoader loader = new FXMLLoader(fxmlFile);

			VBox page = loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Calculate Edit Distance");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set reference to stage in controller
			EditDistanceDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setMainApp(this);
			controller.setField(selectedText);
			
			// give controller reference to scene (cursor)

			// Show the dialog and wait until the user closes it
		    dialogStage.showAndWait();

    	} catch (IOException e) {
    		// Exception gets thrown if the fxml file could not be loaded
    		e.printStackTrace();
    	}
    	
    }
    
    public void showEDResult(List<String> path) {
        // intialize alert/dialog to display edit distance result
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Result");
    	alert.setHeaderText("Word Path : ");
    	alert.initModality(Modality.NONE);
    	alert.setResizable(true);
    	
    	// create layout for content
    	VBox box = new VBox();
    	HBox midBox = new HBox();
    	box.setPadding(new Insets(35,0,35,0));
    	box.setSpacing(35);
    	midBox.setSpacing(15);
    	
    	Label pathLabel = new Label();
    	Label numStepsLabel = new Label("Number of steps : ");
    	Label numSteps = new Label();
    	Font font = new Font(14);
    	pathLabel.setFont(font);
    	numStepsLabel.setFont(font);    	
    	numSteps.setFont(Font.font(font.getFamily(), FontWeight.BOLD, 14));
    	
    	midBox.getChildren().add(numStepsLabel);
    	midBox.getChildren().add(numSteps);
    	midBox.setAlignment(Pos.CENTER);
    	
    	box.getChildren().add(pathLabel);
    	box.getChildren().add(midBox);
    	box.setAlignment(Pos.CENTER);
    	alert.getDialogPane().setPrefWidth(300);
    	
    	// check for path
    	if(path != null) {
    		numSteps.setText(Integer.toString(path.size()-1));
	    	pathLabel.setText(String.join(" -> ", path));
	    	
	    	Text text = new Text(pathLabel.getText());
	    	text.setFont(font);
	    	if(text.getLayoutBounds().getWidth() > 200) {
		    	alert.getDialogPane().setPrefWidth(text.getLayoutBounds().getWidth()+100);
	    	}
    	} else { // no path found
    		pathLabel.setText("No Path Found.");
    		numSteps.setText("N/A");
    	}
    	
    	// set content and styling
    	alert.getDialogPane().setContent(box);
        String styleSheet = Objects.requireNonNull(MainApp.class.getClassLoader()
				.getResource("view/application.css")).toExternalForm();
    	alert.getDialogPane().getStylesheets().add(styleSheet);
    	alert.getDialogPane().getStyleClass().add("myDialog");
    	alert.showAndWait();
    }

	/**
	 * Show Markov text generator dialog
	 * @param mtg supplied generator
	 */
	public void showMarkovDialog(textgen.MarkovTextGenerator mtg) {
    	try {
    		// Load the fxml file and create a new stage for the popup

			URL fxmlFile = Objects.requireNonNull(MainApp.class.getClassLoader()
					.getResource("view/MarkovLayout.fxml"));
			FXMLLoader loader = new FXMLLoader(fxmlFile);

			BorderPane page = loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Markov Text Generator");
			//dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set reference to stage in controller
			//BUG -- when first displayed results don't show up until resize window
			MarkovController controller = loader.getController();
			//controller.setDialogStage(dialogStage);
			controller.setMainApp(this);
			controller.setMTG(mtg);

			// Show the dialog and wait until the user closes it
		    dialogStage.showAndWait();

    	} catch (IOException e) {
    		// Exception gets thrown if the fxml file could not be loaded
    		e.printStackTrace();
    	}

    }

	/**
	 * Show the provided parameter stage
	 * @param loadStage the parameter stage
	 * @param text the text to display
	 */
	public void showLoadStage(Stage loadStage, String text) {
    	loadStage.initModality(Modality.APPLICATION_MODAL);
    	loadStage.initOwner(primaryStage);
        VBox loadVBox = new VBox(20);
        loadVBox.setAlignment(Pos.CENTER);
        Text tNode = new Text(text);
        tNode.setFont(new Font(16));
        loadVBox.getChildren().add(new HBox());
        loadVBox.getChildren().add(tNode);
        loadVBox.getChildren().add(new HBox());
        Scene loadScene = new Scene(loadVBox, 300, 200);
        loadStage.setScene(loadScene);
        loadStage.show();
    }

	/**
	 * Main application entry
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Return this application main stage
	 * @return the primary stage
	 */
	public Stage getStage() {
		return this.primaryStage;
	}
	
}
