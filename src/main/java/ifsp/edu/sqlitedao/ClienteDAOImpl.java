package ifsp.edu.sqlitedao;

import ifsp.edu.model.Cliente;
import ifsp.edu.usecases.cliente.ClienteDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteDAOImpl implements ClienteDAO {

    @Override
    public boolean insert(Cliente cliente) {
        String sql = "INSERT INTO CLIENTE(cpf, nome, endereco, telefone1, telefone2) VALUES(?, ?, ?, ?, ?)";
        try(PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {
            ps.setString(1, cliente.getCpf());
            ps.setString(2, cliente.getNome());
            ps.setString(3, cliente.getEndereco());
            ps.setString(4, cliente.getTelefone1());
            ps.setString(5, cliente.getTelefone2());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Cliente resultSetToEntity(ResultSet rs) throws SQLException {
        return new Cliente(
                rs.getString("CPF"),
                rs.getString("NOME"),
                rs.getString("ENDERECO"),
                rs.getString("TELEFONE1"),
                rs.getString("TELEFONE2")
        );
    }

    @Override
    public Cliente findOne(String cpf) {
        Cliente cliente = null;
        String sql = "SELECT * FROM CLIENTE WHERE cpf = ?";
        try(PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {
            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                cliente = resultSetToEntity(rs);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return cliente;
    }

    @Override
    public List<Cliente> listAll() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM CLIENTE ";
        try(PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Cliente cliente = resultSetToEntity(rs);
                clientes.add(cliente);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return clientes;
    }

    @Override
    public boolean update(Cliente cliente) {
        String sql = "UPDATE CLIENTE SET nome = ?, endereco = ?, telefone1 = ?, telefone2 = ? WHERE cpf = ?";
        try(PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEndereco());
            ps.setString(3, cliente.getTelefone1());
            ps.setString(4, cliente.getTelefone2());
            ps.setString(5, cliente.getCpf());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String cpf) {
        String sql = "DELETE FROM CLIENTE WHERE cpf = ?";
        try(PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {
            ps.setString(1, cpf);
            ps.execute();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Optional<Cliente> findByCpf(String cpf) {
        Cliente cliente = findOne(cpf);
        return Optional.ofNullable(cliente);
    }
}
