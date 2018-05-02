package com.matheuscampos.jsf.dao;

import com.matheuscampos.jsf.model.Carro;
import com.matheuscampos.jsf.util.ConnectionFactory;
import com.matheuscampos.jsf.util.exception.ErroSistema;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarroDAO implements CrudDAO<Carro> {
    
    @Override
    public void salvar(Carro carro) throws ErroSistema {
        try {
            Connection conexao = ConnectionFactory.getConexao();
            PreparedStatement ps;
            if (carro.getIdCarro() == null) {
                ps = conexao.prepareStatement("INSERT INTO Carro (modelo, fabricante, cor, ano) VALUES (?, ?, ?, ?)");
            } else {
                ps = conexao.prepareStatement("UPDATE Carro SET modelo = ?, fabricante = ?, cor = ?, ano = ? WHERE idCarro = ?");
                ps.setInt(5, carro.getIdCarro());
            }
            ps.setString(1, carro.getModelo());
            ps.setString(2, carro.getFabricante());
            ps.setString(3, carro.getCor());
            ps.setDate(4, new Date(carro.getAno().getTime()));
            ps.execute();
            ConnectionFactory.fecharConexao();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao salvar carro.", ex);
        }
    }
    
    @Override
    public void deletar(Carro carro) throws ErroSistema {
        try {
            Connection conexao = ConnectionFactory.getConexao();
            PreparedStatement ps = conexao.prepareStatement("DELETE FROM Carro WHERE idCarro = ?");
            ps.setInt(1, carro.getIdCarro());
            ps.execute();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao deletar carro.", ex);
        }
    }
    
    @Override
    public List<Carro> buscar() throws ErroSistema {
        try {
            Connection conexao = ConnectionFactory.getConexao();
            PreparedStatement ps = conexao.prepareStatement("SELECT * FROM Carro");
            ResultSet result = ps.executeQuery();
            List<Carro> carros = new ArrayList<>();
            while (result.next()) {
                Carro carro = new Carro();
                carro.setIdCarro(result.getInt("idCarro"));
                carro.setModelo(result.getString("modelo"));
                carro.setFabricante(result.getString("fabricante"));
                carro.setCor(result.getString("cor"));
                carro.setAno(result.getDate("ano"));
                carros.add(carro);
            }
            ConnectionFactory.fecharConexao();
            return carros;
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao buscar carros.", ex);
        }
    }

}
