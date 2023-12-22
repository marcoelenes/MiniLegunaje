package Codigo;

public class Entero extends Variables{
    private int valor;

    public Entero (String nombre, int valor){
        super(nombre);
        this.valor = valor;
    }

    public int getValor(){

        return valor;
    }
    public void setValor(int valor){
        this.valor=valor;
    }


}
