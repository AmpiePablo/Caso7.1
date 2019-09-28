package Logica;

/**
 *
 * @author Pablo Ampie
 */
public class Numero {
    private int numero;
    private int posibilidad;
    
    Numero(int pNumero){
      numero=pNumero;
      posibilidad=1;
    }

    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public int getPosibilidad() {
        return posibilidad;
    }
    public void setPosibilidad(int posibilidad) {
        this.posibilidad = posibilidad;
    }
    
    public void sumarPosibilidad(){
        posibilidad++;
    }
    public void restarPosibilidad(){
        posibilidad--;
    }

    @Override
    public String toString() {
        return "Numero{" + "numero=" + numero + ", posibilidad=" + posibilidad + '}';
    }
    
    
    
}