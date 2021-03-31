import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.awt.desktop.AboutEvent;
import java.util.concurrent.ThreadLocalRandom;

//poczatkowe ustawienia
public class InitialSetting {

    public Image setBeginning(Image image, int choice) {
        WritableImage wImage = new WritableImage(
                (int) image.getWidth(),
                (int) image.getHeight());
        PixelWriter pixelWriter = wImage.getPixelWriter();

        //obrazek 800x800
        int test_pixel_X = 399;
        int test_pixel_Y = 399;

        int randomNum;

                if (choice == 1) {  //niezmienne

                    pixelWriter.setColor(test_pixel_X + 1 , test_pixel_Y , Color.BLACK);
                    pixelWriter.setColor(test_pixel_X + 2 , test_pixel_Y , Color.BLACK);

                    pixelWriter.setColor(test_pixel_X , test_pixel_Y + 1, Color.BLACK);
                    pixelWriter.setColor(test_pixel_X + 3 , test_pixel_Y + 1 , Color.BLACK);

                    pixelWriter.setColor(test_pixel_X + 1 , test_pixel_Y + 2 , Color.BLACK);
                    pixelWriter.setColor(test_pixel_X + 2 , test_pixel_Y + 2 , Color.BLACK);
                }


                if (choice == 2) {    //glider

                    pixelWriter.setColor(test_pixel_X + 1 , test_pixel_Y , Color.BLACK);
                    pixelWriter.setColor(test_pixel_X + 2 , test_pixel_Y , Color.BLACK);

                    pixelWriter.setColor(test_pixel_X , test_pixel_Y + 1 , Color.BLACK);
                    pixelWriter.setColor(test_pixel_X + 1 , test_pixel_Y + 1 , Color.BLACK);
                    pixelWriter.setColor(test_pixel_X + 2 , test_pixel_Y + 2 , Color.BLACK);
                }

                if (choice == 3) {   //oscylator
                    pixelWriter.setColor(test_pixel_X, test_pixel_Y , Color.BLACK);
                    pixelWriter.setColor(test_pixel_X , test_pixel_Y + 1 , Color.BLACK);
                    pixelWriter.setColor(test_pixel_X , test_pixel_Y + 2 , Color.BLACK);
                }

                if (choice == 4) {   //random
                    for (int height = 1; height < image.getHeight() - 1; height++) {

                        for (int width = 1; width < image.getWidth() - 1; width++) {
                            randomNum = ThreadLocalRandom.current().nextInt(0, 3 + 1);
                            if (randomNum == 0)
                                pixelWriter.setColor(width, height, Color.BLACK);
                            else
                                pixelWriter.setColor(width, height, Color.WHITE);
                        }
                    }
                }
        return wImage;
    }

    public Image unChanging(Image image) {
        return setBeginning(image, 1);
    }

    public Image glider(Image image) {
        return setBeginning(image, 2);
    }

    public Image oscillator(Image image) {
        return setBeginning(image, 3);
    }

    public Image random(Image image) {
        return setBeginning(image, 4);
    }
}
