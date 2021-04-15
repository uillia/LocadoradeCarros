package controller;

import java.sql.*;

public class Conexao {

    public Connection con;

    public Conexao() {
    }

    public void Conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Conectado");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/locadora?serverTimezone=UTC", "root", "rw160600");
            System.out.println("Conexão Realizada");
            
            
        } catch (ClassNotFoundException exc) {
            System.out.println("Erro no driver " + exc.getMessage());
        } catch (SQLException exc) {
            System.out.println("Erro de conexao =" + exc.getMessage());
        }

    }
     public  void Fechar_Conexao()  {
       try{
           con.close(); 
       } catch(SQLException exc) {
           System.out.println(exc.getMessage());
       }
   }
}
