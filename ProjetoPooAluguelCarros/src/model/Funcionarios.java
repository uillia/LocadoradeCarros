package model;


import java.util.Date;

public class Funcionarios {

    private String CPF;
    private String Numero;
    private String Endereco;
    private String Nome;
    private Date Data_Nascimento;
    
    
    
    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String Numero) {
        this.Numero = Numero;
    }

  

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String Endereco) {
        this.Endereco = Endereco;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public Date getData_Nascimento() {
        return Data_Nascimento;
    }

    public void setData_Nascimento(Date Data_Nascimento) {
       this.Data_Nascimento = Data_Nascimento;
    }

    
}
