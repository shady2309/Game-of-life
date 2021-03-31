import javafx.scene.paint.Color;

//komorka
public class Cell {
    Color color;   //kolorki czy zyje, biala niezywa, czarna zywa

    //wspolrzedne komorki
    int width;
    int height;

    public Cell() {
    }

    //gettery, settery

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
