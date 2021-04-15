package controller;

//import java.sql.Date;
import com.sun.security.ntlm.Client;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.time.LocalDate;
import model.Funcionarios;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Clientes;
import model.Veiculos;
import model.Locacao;

public class Metodos {

    Conexao c = new Conexao();

    public void CadastrarFuncioario(Funcionarios func) {
        String ver = null;
        try {

            c.Conectar();
            String sql = "select * from funcionarios where CPF = '" + func.getCPF() + "' ";
            PreparedStatement ps = c.con.prepareStatement(sql);
            ps.execute();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                ver = rs.getString("CPF");
            }

            ps.execute();
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "Alerta", 2);
        }

        if (ver == null) {
            try {
                
                c.Conectar();
                PreparedStatement pst = c.con.prepareStatement("INSERT INTO funcionarios "
                        + " (Nome,Endereco,Data_Nascimento,CPF,Numero) VALUES (?,?,?,?,?)");

                pst.setString(1, func.getNome());
                pst.setString(2, func.getEndereco());
                pst.setDate(3, new java.sql.Date(func.getData_Nascimento().getTime()));
                pst.setString(4, func.getCPF());
                pst.setString(5, func.getNumero());

                pst.execute();

                JOptionPane.showMessageDialog(null, "Cadastro Realizado com Sucesso ", "", 1);

                pst.close();
                c.Fechar_Conexao();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Alerta", 2);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Erro: CPF ja cadastrado");
        }

    }

    public void CadastrarVeiculo(Veiculos veic) {
        int ver = 0;
        try {
            
            c.Conectar();
            String sql = "select * from veiculos where numero_crv = '" + veic.getNumero_crv() + "' ";
            PreparedStatement ps = c.con.prepareStatement(sql);
            ps.execute();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                ver = rs.getInt("numero_crv");
            }

            ps.execute();
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "Alerta", 2);
        }

        if (ver == 0) {
            try {
                
                c.Conectar();
                PreparedStatement pt = c.con.prepareStatement("INSERT INTO veiculos"
                        + "(numero_crv,codmarca,ano,modelo,categoria,quilometragem,valor_dia,valor_fixo) VALUES (?,?,?,?,?,?,?,?)");

                pt.setString(1, veic.getNumero_crv());
                pt.setInt(2, veic.getMarca());
                pt.setString(3, veic.getAno());
                pt.setString(4, veic.getModelo());
                pt.setString(5, veic.getCategoria());
                pt.setString(6, veic.getQuilometraem());
                pt.setDouble(7, veic.getValorDia());
                pt.setDouble(8, veic.getValorFixo());

                pt.execute();

                JOptionPane.showMessageDialog(null, "Veiculo Cadastrado com Sucesso", "", 1);

                pt.close();
                c.Fechar_Conexao();

            } catch (SQLException e) {

                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Alerta", 2);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Erro: Num CRV ja cadastrado");
        }
    }

    public void CadastrarClientes(Clientes cli) {
        String ver = null;
        try {

            c.Conectar();
            String sql = "select * from clientes where CPF = '" + cli.getCPF() + "' ";
            PreparedStatement ps = c.con.prepareStatement(sql);
            ps.execute();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                ver = rs.getString("CPF");
            }

            ps.execute();
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "Alerta", 2);
        }

        if (ver == null) {

            c.Conectar();
            try {
                PreparedStatement ps = c.con.prepareStatement("INSERT INTO clientes"
                        + "(CPF,numero_cnh,nome,numero,endereco,data_nascimento,categoria_cnh) VALUES(?,?,?,?,?,?,?)");

                ps.setString(1, cli.getCPF());
                ps.setString(2, cli.getNumero_cnh());
                ps.setString(3, cli.getNome());
                ps.setString(4, cli.getNumero());
                ps.setString(5, cli.getEndereco());
                ps.setDate(6, new java.sql.Date(cli.getData_nascimento().getTime()));
                ps.setString(7, cli.getCategoria_CNH());

                ps.execute();

                JOptionPane.showMessageDialog(null, "Cliente Cadastrado com Sucesso", "", 1);

                ps.close();
                c.Fechar_Conexao();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "Alerta", 2);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Erro: CPF ja cadastrado");
        }
    }

    public void LocarVeiculo(Locacao loc) {
        int codvei;

        c.Conectar();
        try {
            PreparedStatement ps = c.con.prepareStatement("INSERT INTO locacao"
                    + "(codcliente,codveiculo,codfuncionario,data_locacao,qtdias,data_prev,valor,atividade) VALUES(?,?,?,?,?,?,?,?)");

            ps.setInt(1, loc.getCodCli());
            ps.setInt(2, loc.getCodVei());
            ps.setInt(3, loc.getCodfunc());
            ps.setString(4, loc.getData_loc().toString());
            ps.setInt(5, loc.getQtdDias());
            ps.setString(6, loc.getData_loc().plusDays(loc.getQtdDias()).toString());
            ps.setDouble(7, loc.getValorf());
            ps.setString(8, "on");

            codvei = loc.getCodVei();

            mudarDisp(codvei);// muda a disponibilidade para 0

            ps.execute();

            JOptionPane.showMessageDialog(null, "Locação realizada !", "", 1);

            ps.close();
            c.Fechar_Conexao();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "Alerta", 2);
        }

    }

    public double CalcVal(int codvei, int qtddias, LocalDate data_loc) {
        c.Conectar();

        double total = 0, ValorDia = 0, ValorFixo = 0, juros = 1;
        try {
            String sql = "select * from veiculos where codveiculo = '" + codvei + "' ";
            PreparedStatement ps = c.con.prepareStatement(sql);
            ps.execute();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                ValorDia = rs.getDouble("valor_dia");
                ValorFixo = rs.getDouble("valor_fixo");
            }

            ps.execute();
            ps.close();
            c.Fechar_Conexao();
            int j = 0;
            for (int i = 1; i < 10; i++) {
                if (data_loc.isAfter(data_loc.plusDays(qtddias).plusWeeks(i))) {
                    juros = juros + juros * 0.048;
                    j = 1;
                }
            }

            total = (ValorFixo + ValorDia * qtddias) * juros;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "Alerta", 2);
        }

        return total;
    }

    public void TabelaConsulta(DefaultTableModel tabela1, DefaultTableModel tabela2, String codbusca) {

        try {

            c.Conectar();
            String sql = "select * from locacao where codcliente = '" + codbusca + "' and atividade = 'on'";
            PreparedStatement ps = c.con.prepareStatement(sql);
            ps.execute();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                tabela1.addRow(new Object[]{
                    rs.getInt("codlocacao"),
                    retornaNomeCli(rs.getInt("codcliente")),//chama o metodo que busca o nome com base no codigo
                    retornaModelo(rs.getInt("codveiculo")),//chama o metodo que busca o modelo do veiculo com base no codigo
                    rs.getDate("data_locacao"),
                    rs.getDate("data_prev"),
                    rs.getInt("qtdias"),
                    "R$" + rs.getDouble("valor")});
            }

            ps.execute();
            ps.close();
            c.Fechar_Conexao();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "Alerta", 2);
        }
        try {
            String att = "off";
            c.Conectar();
            String sql = "select * from registroentrega where codcliente = '" + codbusca + "'";
            PreparedStatement ps = c.con.prepareStatement(sql);
            ps.execute();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                tabela2.addRow(new Object[]{
                    rs.getInt("codlocacao"),
                    retornaNomeCli(rs.getInt("codcliente")),//chama o metodo que busca o nome com base no codigo
                    retornaModelo(rs.getInt("codveiculo")),//chama o metodo que busca o modelo do veiculo com base no codigo
                    rs.getString("data_locacao"),
                    rs.getString("data_entrega"),
                    "R$" + rs.getDouble("valortotal"),
                    rs.getInt("multa")
                });
            }

            ps.execute();
            ps.close();
            c.Fechar_Conexao();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "Alerta", 2);
        }

    }

    public int catClientNum(String catCli) {
        int catC = 0;
        if (catCli.equals("B") || catCli.equals("AB")) {
            catC = 1;
        }
        if (catCli.equals("C") || catCli.equals("AC")) {
            catC = 2;
        }
        if (catCli.equals("D") || catCli.equals("AD")) {
            catC = 3;
        }
        if (catCli.equals("E") || catCli.equals("AE")) {
            catC = 4;
        }
        return catC;
    }

    public int catVeicNum(String catVei) {
        int catV = 0;
        if (catVei.equals("B")) {
            catV = 1;
        }
        if (catVei.equals("C")) {
            catV = 2;
        }
        if (catVei.equals("D")) {
            catV = 3;
        }
        if (catVei.equals("E")) {
            catV = 4;
        }
        return catV;

    }

    public void mudarDisp(int codvei) {
        int disp = 0;
        c.Conectar();
        try {
            String sql = "select * from veiculos where codveiculo = '" + codvei + "' ";
            PreparedStatement ps = c.con.prepareStatement(sql);
            ps.execute();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) {

                disp = rs.getInt("disponibilidade");
            }

            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (disp == 0) {
            disp = 1;

        } else {
            disp = 0;
        }
        try {
            PreparedStatement ps = c.con.prepareStatement("update veiculos set disponibilidade = '" + disp + "' where codveiculo = '" + codvei + "' ");
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void carregaTabCarros(DefaultTableModel tabela) {
        try {
            c.Conectar();
            String sql = "select * from veiculos where disponibilidade = '" + 1 + "'";
            PreparedStatement ps = c.con.prepareStatement(sql);
            ps.execute();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                tabela.addRow(new Object[]{
                    rs.getInt("codveiculo"),
                    rs.getString("modelo")});
            }

            ps.execute();
            ps.close();
            c.Fechar_Conexao();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "Alerta", 2);
        }

    }

    public String retornaModelo(int codVei) {
        String modelo = null;

        c.Conectar();
        try {
            String sql = "select * from veiculos where codveiculo = '" + codVei + "' ";
            PreparedStatement ps = c.con.prepareStatement(sql);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                modelo = rs.getString("modelo");
            }
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return modelo;
    }

    public String retornaNomeCli(int codCli) {
        String nome = null;

        c.Conectar();
        try {
            String sql = "select * from clientes where codcliente = '" + codCli + "' ";
            PreparedStatement ps = c.con.prepareStatement(sql);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                nome = rs.getString("nome");
            }
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nome;
    }

    public void DarBaixa(int codLoc) {
        //Metodos mais complexo do programa
        int multa = 0;
        String data_loc = null, data_prev = null, at = null;
        Locacao loc = new Locacao();

        c.Conectar();

        try {
            c.Conectar();
            String sql = "select * from locacao where codlocacao = '" + codLoc + "' ";
            PreparedStatement ps = c.con.prepareStatement(sql);
            ps.execute();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                loc.setCodCli(rs.getInt("codcliente"));
                loc.setCodVei(rs.getInt("codveiculo"));
                loc.setCodfunc(rs.getInt("codfuncionario"));
                data_loc = rs.getString("data_locacao");
                loc.setQtdDias(rs.getInt("qtdias"));
                data_prev = rs.getString("data_prev");
                loc.setValorf(rs.getDouble("valor"));
            }//pega os valores para o objeto

            ps.execute();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "Alerta", 2);
        }

        try {
            c.Conectar();
            PreparedStatement ps = c.con.prepareStatement("update locacao set atividade = 'off' where codlocacao = '" + codLoc + "' ");
            ps.execute();
            mudarDisp(loc.getCodVei());// muda a disponibilidade para 1

        } catch (SQLException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }

        LocalDate hoje = LocalDate.now();

        try {
            c.Conectar();
            PreparedStatement ps = c.con.prepareStatement("INSERT INTO registroentrega"
                    + "(codlocacao, codcliente, codveiculo, codfuncionario, data_locacao, data_prev, data_entrega, valortotal, multa) VALUES(?,?,?,?,?,?,?,?,?)");

            ps.setInt(1, codLoc);
            ps.setInt(2, loc.getCodCli());
            ps.setInt(3, loc.getCodVei());
            ps.setInt(4, loc.getCodfunc());
            ps.setString(5, data_loc);
            ps.setString(6, data_prev);
            ps.setString(7, hoje.toString());
            ps.setDouble(8, CalcVal(loc.getCodVei(), loc.getQtdDias(), LocalDate.parse(data_loc)));

            if (loc.getValorf() > loc.getValorf()) {
                multa = 1;
            }
            ps.setInt(9, multa);

            ps.execute();

            JOptionPane.showMessageDialog(null, "Veículo devolvido, locação finalizada !", "", 1);

            ps.close();
            c.Fechar_Conexao();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "Alerta", 2);
        }

    }

    public void carregaTabCarroAlter(DefaultTableModel tab) {
        try {
            c.Conectar();
            String sql = "select * from veiculos";
            PreparedStatement ps = c.con.prepareStatement(sql);
            ps.execute();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                tab.addRow(new Object[]{
                    rs.getInt("codveiculo"),
                    rs.getString("codmarca"),
                    rs.getString("modelo"),
                    rs.getString("categoria"),
                    rs.getString("numero_crv"),
                    rs.getString("disponibilidade")});

            }

            ps.execute();
            ps.close();
            c.Fechar_Conexao();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "Alerta", 2);
        }
    }

    public String retornaAtividade(int codvei) {
        String at = null;

        try {
            c.Conectar();
            String sql = "select * from locacao where codveiculo = '" + codvei + "' ";
            PreparedStatement ps = c.con.prepareStatement(sql);
            ps.execute();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                at = rs.getString("atividade");
            }

            ps.execute();
            ps.close();
            c.Fechar_Conexao();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "Alerta", 2);
        }
        if (at != "on" || at != "off") {
            at = "nc";

        }
        return at;

    }

    public void AtualizarVeic(Veiculos vei, int codvei) {
        try {
            c.Conectar();
            PreparedStatement ps = c.con.prepareStatement("update veiculos set numero_crv ='" + vei.getNumero_crv() + "',"
                    + " categoria = '" + vei.getCategoria() + "', "
                    + " disponibilidade = '" + vei.getDisponibilidade() + "' where codveiculo = '" + codvei + "' ");
            ps.execute();
            ps.close();
            c.Fechar_Conexao();

        } catch (SQLException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}
