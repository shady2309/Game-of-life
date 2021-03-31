import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class MainWindow implements Initializable {

    @FXML
    public AnchorPane pane;
    @FXML
    public Canvas canvas;

    private String imagePath = "board.bmp";
    private Image image;
    GraphicsContext gc;
    InitialSetting initialSetting = new InitialSetting();
    MatrixCell matrixCell;
    WritableImage wImage;
    PixelWriter pixelWriter;
    private boolean startStop;   //flaga do wątku


    public void click() {              //reakcja na kliknięcie myszką - kolorowanie piksela na czarny
        wImage = new WritableImage(
                (int) image.getWidth(),
                (int) image.getHeight());
        PixelWriter pixelWriter = wImage.getPixelWriter();

        canvas.setOnMouseClicked(event -> {
            double x = event.getX(), y = event.getY();
            pixelWriter.setColor((int) x, (int) y, Color.BLACK);
            System.out.println("click");             //sprawdzenie reakcji

            image = wImage;  //zastapienie pierwotnego obrazka
            gc.drawImage(image, 0, 0);
        });
    }

    public void unchanging(){                 // niezmienne
        image=initialSetting.unChanging(image);
        gc.drawImage(image,0,0);
    }

    public void glider(){                     // glider
        image=initialSetting.glider(image);
        gc.drawImage(image,0,0);
    }

    public void oscillator(){                 // oscylator
        image=initialSetting.oscillator(image);
        gc.drawImage(image,0,0);
    }
    public void random(){                    // losowe
        image=initialSetting.random(image);
        gc.drawImage(image,0,0);
    }


    public void start() {          // rozpoczecie gry
        startStop = true;          // flaga watku

        Thread thread = new Thread(() -> {  //stworzenie watku
            List<Cell[][]> list = null;
            matrixCell = new MatrixCell();

            while (startStop) {   //petla nieskoczona do kliku stop
                list = matrixCell.makeMatrixList(image);  //analiza obrazka
                System.out.println("good, I'm working");   //czy dziala
                image = matrixCell.lifeGame(image, list);

                gc.drawImage(image, 0, 0);
            }
        });
        thread.start();  //odpalenie watku
    }

    public void stop() {           // zatrzymanie gry (nieskonczonej petli <wątku>)
        startStop = false;  //flaga watku na false
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        image = new Image((imagePath));
        gc = canvas.getGraphicsContext2D();
        gc.drawImage(image, 0, 0);
    }

    public void reset() {
        gc.clearRect(0, 0, 800, 800);
        image = new Image(imagePath);
        wImage = new WritableImage(
                (int) image.getWidth(),
                (int) image.getHeight());
        pixelWriter = wImage.getPixelWriter();
        gc.drawImage(image, 0, 0);

    }
}



