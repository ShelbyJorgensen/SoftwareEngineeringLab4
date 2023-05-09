package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainController {
	
	@FXML
	private Label sID;
	@FXML
	private TextField unID;
	@FXML
	private TextField passID;
	
	private static final String SQL_INSERT = "INSERT INTO registration(id, email, password) VALUES(?,?,?)";
	
	public void Login(ActionEvent event) throws Exception, SQLException {	
		try {
			//Establishing connection
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafxlab", "root", "CS380");
			
			// Creating PreparedStatement Object
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT);
			// Setting values for Each Parameter
			preparedStatement.setInt(1,1);
			preparedStatement.setString(2, unID.getText());
			preparedStatement.setString(3, passID.getText());
			preparedStatement.executeUpdate();
						
			if (true) {
				sID.setText("Login Success");
				Stage primaryStage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
				Scene scene = new Scene(root, 500, 500);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			}
		} catch(SQLException e) {
			System.out.println("Error while connecting to the database");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
