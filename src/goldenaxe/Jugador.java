/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package goldenaxe;

/**
 *
 * @author MEDAC
 */
public class Jugador {
    //Atributos
    protected int vida, fuerza;
    protected boolean estaVivo;
    protected String nombre;
    protected Carta [] carta;
    
    //Constructores
    public Jugador(){
        carta = new Carta[5];
        carta[0] = new Carta("Obtienes un punto de vida",1,0);
        carta[1] = new Carta("Te quitan un punto de vida",-1,0);
        carta[2] = new Carta("Obtienes un punto de fuerza",0,1);
        carta[3] = new Carta("Te quitan un punto de fuerza",0,-1);
        carta[4] = new Carta("Obtienes dos puntos de vida",2,0);
    }
    
    //Métodos
    
    public Carta getCarta(int num){
        return carta[num];
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public boolean getEstaVivo() {
        return estaVivo;
    }

    public void setEstaVivo(boolean estaVivo) {
        this.estaVivo = estaVivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
}
