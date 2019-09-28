package Logica;
/**
 * laita42
 * @author Pablo Ampie
 */
public class Letra {
    private String letra;
    private int posibilidadAcierto;
    
    Letra(String pLetra){
        letra=pLetra;
        posibilidadAcierto=1;
    }

    public String getLetra() {
        return letra;
    }
    public void setLetra(String letra) {
        this.letra = letra;
    }

    public int getPosibilidadAcierto() {
        return posibilidadAcierto;
    }

    public void setPosibilidadAcierto(int posibilidadAcierto) {
        this.posibilidadAcierto = posibilidadAcierto;
    }
    
   public void sumarPosibilidad(){
        posibilidadAcierto++;
    }
    public void restarPosibilidad(){
        posibilidadAcierto--;
    }
}