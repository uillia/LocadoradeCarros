
package model;
import java.time.LocalDate;
import java.util.Date;

public class Locacao {
    private int codCli;
    private int codVei;
    private int codfunc;
    private LocalDate data_loc;
    private int qtdDias;
    private double valorf;

    public int getCodCli() {
        return codCli;
    }

    public void setCodCli(int codCli) {
        this.codCli = codCli;
    }

    public int getCodVei() {
        return codVei;
    }

    public void setCodVei(int codVei) {
        this.codVei = codVei;
    }

    public int getCodfunc() {
        return codfunc;
    }

    public void setCodfunc(int codfunc) {
        this.codfunc = codfunc;
    }

    public int getQtdDias() {
        return qtdDias;
    }

    public void setQtdDias(int qtdDias) {
        this.qtdDias = qtdDias;
    }

    public LocalDate getData_loc() {
        return data_loc;
    }

    public void setData_loc(LocalDate data_loc) {
        this.data_loc = data_loc;
    }

    public double getValorf() {
        return valorf;
    }

    public void setValorf(double valorf) {
        this.valorf = valorf;
    }
    
}
