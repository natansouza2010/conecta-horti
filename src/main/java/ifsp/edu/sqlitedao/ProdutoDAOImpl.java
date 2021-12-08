package ifsp.edu.sqlitedao;

import ifsp.edu.enums.FormaDePagamento;
import ifsp.edu.enums.StatusPedido;
import ifsp.edu.model.*;
import ifsp.edu.usecases.produto.ProdutoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdutoDAOImpl implements ProdutoDAO {

    private FornecedorDAOImpl daoFornecedor = new FornecedorDAOImpl();

    @Override
    public boolean insert(Produto produto) {
        String sql = "INSERT INTO PRODUTO(id, nome, descricao, valorcusto, valorvenda, cnpj_fornecedor) VALUES(?, ?, ?, ?, ?, ?)";
        try(PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {
            ps.setInt(1, produto.getId());
            ps.setString(2, produto.getNome());
            ps.setString(3, produto.getDescricao());
            ps.setDouble(4, Double.parseDouble(produto.getValorCusto().toString()));
            ps.setDouble(5, Double.parseDouble(produto.getValorVenda().toString()));
            ps.setString(6, produto.getFornecedor().getCnpj());
            ps.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Produto resultSetToEntity(ResultSet rs) throws SQLException {
        Fornecedor fornecedor = daoFornecedor.findOne(rs.getString("CNPJ_FORNECEDOR"));

        return new Produto(
                rs.getInt("ID"),
                rs.getString("NOME"),
                rs.getString("DESCRICAO"),
                rs.getDouble("VALORCUSTO"),
                rs.getDouble("VALORVENDA"),
                fornecedor
        );
    }

    @Override
    public Produto findOne(Integer id) {
        Produto produto = null;
        String sql = "SELECT * FROM PRODUTO WHERE id = ?";
        try(PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                produto = resultSetToEntity(rs);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return produto;
    }

    @Override
    public List<Produto> listAll() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM PRODUTO ";
        try(PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Produto produto = resultSetToEntity(rs);
                produtos.add(produto);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return produtos;
    }

    @Override
    public boolean update(Produto produto) {
        String sql = "UPDATE PRODUTO SET nome = ?, descricao = ?, valorcusto = ?, valorvenda = ?, cnpj_fornecedor = ? WHERE id = ?";
        try(PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {
            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getDescricao());
            ps.setDouble(3, Double.parseDouble(produto.getValorCusto().toString()));
            ps.setDouble(4, Double.parseDouble(produto.getValorVenda().toString()));
            ps.setString(5, produto.getFornecedor().getCnpj());
            ps.setInt(6, produto.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM PRODUTO WHERE id = ?";
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
    public Optional<Produto> findById(Integer id) {
        Produto produto = findOne(id);
        return Optional.ofNullable(produto);
    }

    @Override
    public Produto findByNome(String nome) {
        Produto produto = null;
        String sql = "SELECT * FROM PRODUTO WHERE nome = ?";
        try(PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                produto = resultSetToEntity(rs);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return produto;
    }

    @Override
    public List<Produto> findByFornecedor(String cnpj) {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM PRODUTO WHERE CNPJ_FORNECEDOR = ? ";
        try(PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {
            ps.setString(1 ,cnpj);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Produto produto = resultSetToEntity(rs);
                produtos.add(produto);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return produtos;
    }

    @Override
    public Fornecedor findFornecedorByProduto(Produto produto) {
        Optional<Fornecedor> fornecedor = null;
        String sql = "SELECT * FROM PRODUTO WHERE id = ?";
        try(PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {
            ps.setInt(1, produto.getId());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                produto = resultSetToEntity(rs);
                fornecedor = daoFornecedor.findByCNPJ(produto.getFornecedor().getCnpj());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return fornecedor.get();
    }
}
