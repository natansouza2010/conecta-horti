package ifsp.edu.sqlitedao;

import ifsp.edu.model.*;
import ifsp.edu.usecases.renda.RendaDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RendaDAOImpl implements RendaDAO {
   


    @Override
    public boolean insert(Renda renda) {
        String sql = "INSERT INTO RENDA(id, receita, despesa, lucro_obtido, data_inicial, data_final) VALUES(?, ?, ?, ?, ?, ?)";
        try(PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {
            ps.setInt(1, renda.getId());
            ps.setInt(2, Integer.parseInt(renda.getReceita().toString()));
            ps.setInt(3, Integer.parseInt(renda.getDespesa().toString()));
            ps.setInt(4, Integer.parseInt(renda.getLucroObtido().toString()));
            ps.setString(5, renda.getDataInicial().toString());
            ps.setString(6, renda.getDataFinal().toString());
            ps.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Renda resultSetToEntity(ResultSet rs) throws SQLException {
        String data1 = rs.getString("DATA_INICIAL");
        String data2 = rs.getString("DATA_FINAL");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataInicial = LocalDate.parse(data1, formatter);
        LocalDate dataFinal = LocalDate.parse(data2, formatter);
        List<Pedido> pedidos = null;
        List<CompraProduto> compraProdutos = null;

        return new Renda(
//                rs.getInt("ID"),
//                dataInicial,
//                dataFinal,
//                pedidos,
//                compraProdutos
        );
    }

    @Override
    public Renda findOne(Integer id) {
        Renda renda = null;
        String sql = "SELECT * FROM RENDA WHERE id = ?";
        try(PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                renda = resultSetToEntity(rs);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return renda;
    }

    @Override
    public List<Renda> listAll() {
        List<Renda> rendas = new ArrayList<>();
        String sql = "SELECT * FROM RENDA ";
        try(PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Renda renda = resultSetToEntity(rs);
                rendas.add(renda);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return rendas;
    }

    @Override
    public boolean update(Renda renda) {
        String sql = "UPDATE RENDA SET receita = ?, despesa = ?, lucro_obtido = ?, data_inicial = ?, data_final = ? WHERE id = ?";
        try(PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {
            ps.setInt(1, Integer.parseInt(renda.getReceita().toString()));
            ps.setInt(2, Integer.parseInt(renda.getDespesa().toString()));
            ps.setInt(3, Integer.parseInt(renda.getLucroObtido().toString()));
            ps.setString(4, renda.getDataInicial().toString());
            ps.setString(5, renda.getDataFinal().toString());
            ps.setInt(6, renda.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM RENDA WHERE id = ?";
        try(PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {
            ps.setInt(1, id);
            ps.execute();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Optional<Renda> findById(Integer id) {
        Renda renda = findOne(id);
        return Optional.ofNullable(renda);
    }
}
