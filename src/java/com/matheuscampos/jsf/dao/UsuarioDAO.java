package com.matheuscampos.jsf.dao;

import com.matheuscampos.jsf.model.Carro;
import com.matheuscampos.jsf.model.Usuario;
import com.matheuscampos.jsf.util.ConnectionFactory;
import com.matheuscampos.jsf.util.exception.ErroSistema;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements CrudDAO<Usuario> {

    @Override
    public void salvar(Usuario entidade) throws ErroSistema {
        try {
            Connection conexao = ConnectionFactory.getConexao();
            PreparedStatement ps;
            if (entidade.getIdUsuario() == null) {
                ps = conexao.prepareStatement("INSERT INTO Usuario (login, senha) VALUES (?, ?)");
            } else {
                ps = conexao.prepareStatement("UPDATE Usuario SET login = ?, senha = ? WHERE idUsuario = ?");
                ps.setInt(3, entidade.getIdUsuario());
            }
            ps.setString(1, entidade.getLogin());
            ps.setString(2, entidade.getSenha());
            ps.execute();
            ConnectionFactory.fecharConexao();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao salvar usuário.", ex);
        }
    }

    @Override
    public void deletar(Usuario entidade) throws ErroSistema {
        try {
            Connection conexao = ConnectionFactory.getConexao();
            PreparedStatement ps = conexao.prepareStatement("DELETE FROM Usuario WHERE idUsuario = ?");
            ps.setInt(1, entidade.getIdUsuario());
            ps.execute();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao deletar usuário.", ex);
        }
    }

    @Override
    public List<Usuario> buscar() throws ErroSistema {
        try {
            Connection conexao = ConnectionFactory.getConexao();
            PreparedStatement ps = conexao.prepareStatement("SELECT * FROM Usuario");
            ResultSet result = ps.executeQuery();
            List<Usuario> usuarios = new ArrayList<>();
            while (result.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(result.getInt("idUsuario"));
                usuario.setLogin(result.getString("login"));
                usuario.setSenha(result.getString("senha"));
                usuarios.add(usuario);
            }
            ConnectionFactory.fecharConexao();
            return usuarios;
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao buscar usuários.", ex);
        }
    }
    
}
