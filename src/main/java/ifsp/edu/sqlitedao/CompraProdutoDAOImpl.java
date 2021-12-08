package ifsp.edu.sqlitedao;

import ifsp.edu.model.CompraProduto;
import ifsp.edu.model.Fornecedor;
import ifsp.edu.model.Produto;
import ifsp.edu.usecases.compraproduto.CompraProdutoDAO;
import ifsp.edu.usecases.produto.ProdutoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompraProdutoDAOImpl implements CompraProdutoDAO {
    private ProdutoDAO daoProduto = new ProdutoDAOImpl();
    static Integer idCompra = numberOfRows();



    private static int numberOfRows(){
        String sql = "SELECT COUNT(*) as ROWS FROM COMPRAPRODUTO";
        try(PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)){
            ResultSet rs = ps.executeQuery();
            System.out.println(rs);
            return Integer.valueOf(rs.getString("ROWS"));

        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean insert(CompraProduto compraProduto) {
        String sql = "INSERT INTO COMPRAPRODUTO(id, momento, valor, id_produto) VALUES(?, ?, ?, ?)";
        try(PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {
            ps.setInt(1, idCompra);
            ps.setString(2, compraProduto.getMomento().toString());
            ps.setDouble(3, Double.parseDouble(compraProduto.getValor().toString()));
            ps.setInt(4, compraProduto.getProduto().getId());
            ps.execute();
            idCompra++;
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private CompraProduto resultSetToEntity(ResultSet rs) throws SQLException {

        Produto produto = daoProduto.findOne(Integer.parseInt(rs.getString("ID_PRODUTO")));
        String data = rs.getString("MOMENTO");
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate momento = LocalDate.parse(data);
        return new CompraProduto(
                rs.getInt("ID"),
                produto,
                momento
        );
    }

    @Override
    public CompraProduto findOne(Integer id) {
        CompraProduto compraProduto = null;
        String sql = "SELECT * FROM COMPRAPRODUTO WHERE id = ?";
        try(PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                compraProduto = resultSetToEntity(rs);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return compraProduto;
    }

    @Override
    public List<CompraProduto> listAll() {
        List<CompraProduto> compraProdutos = new ArrayList<>();
        String sql = "SELECT * FROM COMPRAPRODUTO ";
        try(PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                CompraProduto compraProduto = resultSetToEntity(rs);
                compraProdutos.add(compraProduto);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return compraProdutos;
    }

    @Override
    public boolean update(CompraProduto compraProduto) {
        String sql = "UPDATE COMPRAPRODUTO SET momento = ?, valor = ?, id_produto = ? WHERE id = ?";
        try(PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {

            ps.setString(1, compraProduto.getMomento().toString());
            ps.setInt(2, Integer.parseInt(compraProduto.getValor().toString()));
            ps.setInt(3, compraProduto.getProduto().getId());
            ps.setInt(4, compraProduto.getId());

            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM COMPRAPRODUTO WHERE id = ?";
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
    public Optional<CompraProduto> findByCompra(CompraProduto compra) {
        return Optional.empty();
    }
}
