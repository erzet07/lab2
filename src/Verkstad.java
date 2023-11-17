import java.util.ArrayList;
import java.util.LinkedList;

public class Verkstad <T extends Car>  implements bilVerkstad<T>{
    private int maxAntalBilar = 5;
    private ArrayList<T> bilLista = new ArrayList<>();


    public void setMaxAntalBilar(int antal) {
        this.maxAntalBilar = antal;
    }


    public int getMaxAntalBilar() {
        return maxAntalBilar;
    }
    public ArrayList<T> getBilarIVerkstad(){
        return this.bilLista;
    }
    public void taInBil(T bilTyp) {
        if (maxAntalBilar > bilLista.size()) {
            bilLista.add(bilTyp);
        }
    }
    public void taUtBil(T bilTyp) {
        bilLista.remove(bilTyp);
    }


}

