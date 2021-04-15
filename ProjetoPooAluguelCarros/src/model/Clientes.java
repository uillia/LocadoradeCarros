
package model;
import java.util.Date;


public class Clientes {
    private String CPF;
    private String numero_cnh;
    private String nome;
    private String numero;
    private String Endereco;
    private Date data_nascimento;
    private String categoria_CNH;

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getNumero_cnh() {
        return numero_cnh;
    }

    public void setNumero_cnh(String numero_cnh) {
        this.numero_cnh = numero_cnh;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String Endereco) {
        this.Endereco = Endereco;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getCategoria_CNH() {
        return categoria_CNH;
    }

    public void setCategoria_CNH(String categoria_CNH) {
        this.categoria_CNH = categoria_CNH;
    }
}
