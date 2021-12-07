package ifsp.edu.sqlitedao;

import ifsp.edu.model.Fornecedor;
import ifsp.edu.usecases.fornecedor.FornecedorDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FornecedorDAOImpl implements FornecedorDAO {
    @Override
    public boolean insert(Fornecedor fornecedor) {
        String sql = "INSERT INTO FORNECEDOR(cnpj, nome, telefone1, telefone2, razao_social, endereco) VALUES(?, ?, ?, ?, ?, ?)";
        try(PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {
            ps.setString(1, fornecedor.getCnpj());
            ps.setString(2, fornecedor.getNome());
            ps.setString(3, fornecedor.getTelefone1());
            ps.setString(4, fornecedor.getTelefone2());
            ps.setString(5, fornecedor.getRazaoSocial());
            ps.setString(6, fornecedor.getEndereco());
            ps.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Fornecedor resultSetToEntity(ResultSet rs) throws SQLException {
        return new Fornecedor(
                rs.getString("CNPJ"),
                rs.getString("NOME"),
                rs.getString("TELEFONE1"),
                rs.getString("TELEFONE2"),
                rs.getString("RAZAO_SOCIAL"),
                rs.getString("ENDERECO")
        );
    }

    @Override
    public Fornecedor findOne(String cnpj) {
        Fornecedor fornecedor = null;
        String sql = "SELECT * FROM FORNECEDOR WHERE cnpj = ?";
        try(PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {
            ps.setString(1, cnpj);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                fornecedor = resultSetToEntity(rs);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return fornecedor;
    }

    @Override
    public List<Fornecedor> listAll() {
        List<Fornecedor> fornecedores = new ArrayList<>();
        String sql = "SELECT * FROM FORNECEDOR ";
        try(PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Fornecedor fornecedor = resultSetToEntity(rs);
                fornecedores.add(fornecedor);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return fornecedores;
    }

    @Override
    public boolean update(Fornecedor fornecedor) {
        String sql = "UPDATE FORNECEDOR SET nome = ?, telefone1 = ?, telefone2 = ?, razao_social = ?, endereco = ? WHERE cnpj = ?";
        try(PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {
            ps.setString(1, fornecedor.getNome());
            ps.setString(2, fornecedor.getTelefone1());
            ps.setString(3, fornecedor.getTelefone2());
            ps.setString(4, fornecedor.getRazaoSocial());
            ps.setString(5, fornecedor.getEndereco());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String cnpj) {
        String sql = "DELETE FROM FORNECEDOR WHERE cnpj = ?";
        try(PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {
            ps.setString(1, cnpj);
            ps.execute();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Optional<Fornecedor> findByCNPJ(String cnpj) {
        Fornecedor fornecedor = findOne(cnpj);
        return Optional.ofNullable(fornecedor);
    }

    @Override
    public Fornecedor findByName(String name) {
        Fornecedor fornecedor = null;
        String sql = "SELECT * FROM FORNECEDOR WHERE nome = ?";
        try(PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                fornecedor = resultSetToEntity(rs);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return fornecedor;
    }
}