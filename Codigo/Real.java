package Codigo;

public class Real extends Variables {
    private double valor;

    public Real (String nombre, double valor){
        super(nombre);
        this.valor = valor;
    }

    public double getValor(){
        return valor;
    }
    public void setValor(double valor){
        this.valor=valor;
    }
    

}
