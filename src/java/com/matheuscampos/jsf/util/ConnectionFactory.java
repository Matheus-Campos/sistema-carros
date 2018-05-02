package com.matheuscampos.jsf.util;

import com.matheuscampos.jsf.util.exception.ErroSistema;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    
    private static Connection conexao;
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/sistema_carros";
    private static final String USUARIO = "root";
    private static final String SENHA = null;

    public static Connection getConexao() throws ErroSistema{
        if (conexao == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            } catch (SQLException ex) {
                throw new ErroSistema("Nao foi possivel conectar ao banco de dados.", ex);
            } catch (ClassNotFoundException ex) {
                throw new ErroSistema("O driver do banco de dados nao foi encontrado.", ex);
            }
        }
        return conexao;
    }
    
    public static void fecharConexao() throws ErroSistema{
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException ex) {
                throw new ErroSistema("Erro ao fechar conexao com o banco de dados.", ex);
            }
        }
        conexao = null;
    }
}
