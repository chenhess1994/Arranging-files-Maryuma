
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
//
/**
 * Start the  screen of the Application.
 * @Hen_Hess 
 *
 */
public class App_Ziping extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	/**
	 *
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
				Parent parent = FXMLLoader.load(getClass().getResource("/view/Window.fxml"));
		        Scene scene = new Scene(parent);
		        primaryStage.setTitle("לקוחות - מריומה עיצובים | אמנות");
		        primaryStage.setScene(scene);
		        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("img/logo.png")));
		        primaryStage.setResizable(false);
		        primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERR at App.Start");
		}
	}

}