import java.util.ArrayList;
public interface bilVerkstad <T extends Car> {

    void taInBil(T bilTyp);

    void taUtBil(T bilTyp);

    ArrayList<T> getBilarIVerkstad();

}