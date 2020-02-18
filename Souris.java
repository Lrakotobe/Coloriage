import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.*;



public class Souris extends Application {

  private ComboBox <String> cbColor = new ComboBox <String> ();
  private ComboBox <String> cbShape = new ComboBox <String> ();
    int t= 30;
  private Spinner <Integer> cbSize = new Spinner<Integer>(1,100,t);
  Button saveBtn = new Button("Save");
  private Color pinceau=Color.GREEN;

  public static void main (String[] args){
    launch (args);
  }

  @Override 
  public void start (Stage primaryStage){ 
    primaryStage.setTitle("Hello World!");
    cbColor.getItems().addAll("vert", "bleu", "orange", "rouge", "rose");
    cbColor.setValue("vert");
    cbShape.getItems().addAll("rond", "carre");
    cbShape.setValue("rond");
    Canvas canvas= new Canvas(600, 787);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    gc.setFill(Color.GREEN);

    Image image= new Image("image.png");
    ImageView imageView= new ImageView();
    imageView.setImage(image);
    imageView.setFitWidth(600);
    imageView.setFitHeight(787);

    canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, 
        new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {            
              Color c;
	      int value= (Integer) cbSize.getValue();
              switch (cbColor.getValue ()){
	          case "vert" : gc.setFill(Color.GREEN);
		      break;  
	          case "bleu" : gc.setFill(Color.BLUE);
		      break;
	          case "rouge" :  gc.setFill(Color.RED);
		      break;
            case "rose" :  gc.setFill(Color.PINK);
          break;
	          default : gc.setFill(Color.ORANGE);
	      }
	      switch (cbShape.getValue ()){
	      case "rond" : gc.fillOval(e.getX(), e.getY(), value, value);
		      break;
	      default : gc.fillRect(e.getX(), e.getY(), value, value);

	      }
	    }
        });
  

    Group root = new Group();
    HBox hbox= new HBox();
    hbox.getChildren().addAll(cbShape, cbColor, cbSize, saveBtn);
    BorderPane borderPane = new BorderPane();
    borderPane.setTop(hbox);  
    Pane pane= new Pane();
    pane.getChildren().add(imageView);
    pane.getChildren().add(canvas);
    borderPane.setCenter(pane);    
    root.getChildren().add(borderPane);
  


    
    
    primaryStage.setScene(new Scene(root, 600, 787));
    primaryStage.show();
  }
}

