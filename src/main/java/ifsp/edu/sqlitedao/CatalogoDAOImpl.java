package ifsp.edu.sqlitedao;

import ifsp.edu.model.Catalogo;
import ifsp.edu.model.Cliente;
import ifsp.edu.model.Produto;
import ifsp.edu.usecases.catalogo.CatalogoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CatalogoDAOImpl implements CatalogoDAO {
    @Override
    public boolean insert(Catalogo catalogo) {
        String sql = "INSERT INTO CATALOGO(id, datainicial, datafinal) VALUES(?, ?, ?)";
        try(PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {
            ps.setInt(1, catalogo.getId());
            ps.setString(2, catalogo.getDataInicial().toString());
            ps.setString(3, catalogo.getDataFinal().toString());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Catalogo resultSetToEntity(ResultSet rs) throws SQLException {
        String dataIni = rs.getString("DATAINICIAL");
        String dataFin = rs.getString("DATAFINAL");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataInicial = LocalDate.parse(dataIni,formatter);
        LocalDate dataFinal = LocalDate.parse(dataFin,formatter);
        return new Catalogo(
                rs.getInt("ID"),
                dataInicial,
                dataFinal,
                null
        );
    }

    @Override
    public Catalogo findOne(Integer id) {
        Catalogo catalogo = null;
        String sql = "SELECT * FROM CATALOGO WHERE id = ?";
        try(PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                catalogo = resultSetToEntity(rs);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return catalogo;
    }

    @Override
    public List<Catalogo> listAll() {
        List<Catalogo> catalogos = new ArrayList<>();
        String sql = "SELECT * FROM CATALOGO";
        try(PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Catalogo catalogo = resultSetToEntity(rs);
                catalogos.add(catalogo);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return catalogos;
    }

    @Override
    public boolean update(Catalogo catalogo) {

        String sql = "UPDATE CATALOGOS SET datainicial = ?, datafinal = ? WHERE id = ?";
        try(PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {

            ps.setString(1, catalogo.getDataInicial().toString());
            ps.setString(2, catalogo.getDataFinal().toString());
            ps.setInt(3, catalogo.getId());

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE * FROM CATALOGO WHERE id = ?";
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
    public boolean deleteProdutoDoCatalogo(LocalDate dataInicial, LocalDate dataFinal, Produto produto) {
        return false;
    }

    @Override
    public boolean updateProdutoDoCatalogo(Catalogo catalogo, Produto produto) {
        return false;
    }

    @Override
    public Optional<LocalDate> findDataFinal(LocalDate dataFinal) {
        return Optional.empty();
    }

    @Override
    public Optional<LocalDate> findDataInicial(LocalDate dataInicial) {
        return Optional.empty();
    }

    @Override
    public Optional<Produto> findProdutoByName(String nome) {
        return Optional.empty();
    }
}
