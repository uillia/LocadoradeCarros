
package model;


public class Veiculos {
   
    private String numero_crv;
    private String ano;
    private String modelo;
    private String categoria;
    private String quilometraem;
    private int disponibilidade;
    private int Marca;
    private double valorDia;
    private double valorFixo;
    
   

    public int getMarca() {
        return Marca;
    }

    public void setMarca(int Marca) {
        this.Marca = Marca;
    }

    

    public String getNumero_crv() {
        return numero_crv;
    }

    public void setNumero_crv(String numero_crv) {
        this.numero_crv = numero_crv;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getQuilometraem() {
        return quilometraem;
    }

    public void setQuilometraem(String quilometraem) {
        this.quilometraem = quilometraem;
    }

    public int getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(int disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public double getValorDia() {
        return valorDia;
    }

    public void setValorDia(double valorDia) {
        this.valorDia = valorDia;
    }

    public double getValorFixo() {
        return valorFixo;
    }

    public void setValorFixo(double valorFixo) {
        this.valorFixo = valorFixo;
    }
    
    
}
