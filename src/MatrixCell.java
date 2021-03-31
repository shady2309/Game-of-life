import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class MatrixCell {

    Cell[][] tab;  //macierz z jedna komorka
    List list;     //lista z macierzami
    int helperW = -1;
    int helperH = -1;
    PixelReader pixelReader;

    public List<Cell[][]> makeMatrixList(Image image) {   //wyszukiwanie sasiadow
        list = new ArrayList();
        pixelReader = image.getPixelReader();

        //z pominieciem granic, dlatego od 1
        for (int hi = 1; hi < image.getHeight()-1; hi++) {
            for (int wi = 1; wi < image.getWidth()-1; wi++) {

                tab = new Cell[3][];       //wypelnienie 3x3
                for (int i = 0; i < 3; i++) {
                    tab[i] = new Cell[3];
                    for (int j = 0; j < 3; j++) {
                        tab[i][j] = new Cell();
                    }
                }
                for (int i = 0; i < tab[0].length; i++) {
                    for (int j = 0; j < tab.length; j++) {

                        if (pixelReader.getColor(wi + helperW, hi + helperH).equals(Color.BLACK)) {
                            tab[i][j].setColor(Color.BLACK);
                        } else
                            tab[i][j].setColor(Color.WHITE);

                        helperW++;
                    }
                    helperW = -1;
                    helperH++;
                }
                helperH = -1;

                tab[1][1].setWidth(wi);
                tab[1][1].setHeight(hi);

                list.add(tab);
            }
        }
        return list;
    }

    public Color lifeCheck(List<Cell[][]> list, int b) {    //sprawdzanie zycia

        int counter = 0;         //ile zywych sasiadow
        Color mainCell = null;   //kolor badanej komorki

        //sprawdzenie macierzy, sasiadow jednego piksela
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (list.get(b)[i][j].getColor().equals(Color.WHITE) && i ==1  && j==1 ) {   //jesli biala to martwa
                    mainCell = Color.WHITE;
                    continue;
                }
                if (list.get(b)[i][j].getColor().equals(Color.BLACK) && i ==1  && j==1) {    //jesli czarna to zywa
                    mainCell = Color.BLACK;
                    continue;
                }

                if (list.get(b)[i][j].getColor().equals(Color.BLACK)) {  //sasiedzi, zwiekszamy licznik zywych
                   counter++;
                }

            }
        }

        if (mainCell.equals(Color.WHITE) && counter == 3) {
            //Każda martwa komórka (white), posiadająca
            //trzech żywych sąsiadów (counter==3),
            //rodzi się (zmienia swój kolor na czarny).
           mainCell = Color.BLACK; // ozywa
        }

        if(mainCell.equals(Color.BLACK)){       //jesli badana komorka jest zywa
            if(counter == 2 || counter == 3) {
                //Każda żywa komórka posiadająca dwóch lub
                //trzech żywych sąsiadów (counter =2 / =3),
                //pozostaje żywą (czarna),
                mainCell = Color.BLACK;         //pozostaje zywa

            }
            if(counter > 3) {
                //Każda żywa komórka posiadająca
                //więcej niż 3 sąsiadów umiera
                mainCell = Color.WHITE;        //zmiana koloru na bialy

            }
            if(counter < 2) {
                //Każda żywa komórka posiadająca
                //mniej niż dwóch sąsiadów umiera
                mainCell = Color.WHITE;        //bialy kolor smierci
            }
        }
        return mainCell;
    }

    public Image lifeGame(Image image,List<Cell[][]> list ){

        WritableImage wImage = new WritableImage(
                (int) image.getWidth(),
                (int) image.getHeight());
        PixelWriter pixelWriter = wImage.getPixelWriter();

        Color a;  //badany kolor srodkowej komorki
        int help = 0; //do przechodzenia listy, licznik

        for (int height = 1; height < image.getHeight() - 1; height++) {
            for (int width = 1; width < image.getWidth() - 1; width++) {

                    a = lifeCheck(list,help);
                    pixelWriter.setColor(width,height,a);  //zmiany na obrazku wImage od razu

                help +=1;
            }
        }
        return wImage;
    }
}
