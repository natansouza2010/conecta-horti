package ifsp.edu.sqlitedao;

import ifsp.edu.enums.FormaDePagamento;
import ifsp.edu.enums.StatusPedido;
import ifsp.edu.model.Cliente;
import ifsp.edu.model.Fornecedor;
import ifsp.edu.model.Item;
import ifsp.edu.model.Pedido;
import ifsp.edu.usecases.pedido.PedidoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PedidoDAOImpl implements PedidoDAO {
    private ClienteDAOImpl daoCliente;

    @Override
    public boolean insert(Pedido pedido) {
        String sql = "INSERT INTO PEDIDO(id, valor, datapedido, status, forma_pagamento, cpf_cliente) VALUES(?, ?, ?, ?, ?, ?)";
        try(PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {
            ps.setInt(1, pedido.getId());
            ps.setString(2, pedido.getValor().toString());
            ps.setString(3, pedido.getDataPedido().toString());
            ps.setString(4, pedido.getStatus().toString());
            ps.setString(5, pedido.getPagamento().toString());
            ps.setString(6, pedido.getCliente().getCpf());
            ps.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Pedido resultSetToEntity(ResultSet rs) throws SQLException {
        Cliente cliente = daoCliente.findOne(rs.getString("CPF_CLIENTE"));
        List<Item> itens = null;
        String data = rs.getString("DATAPEDIDO");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataPedido = LocalDate.parse(data, formatter);
        return new Pedido(
                rs.getInt("ID"),
                cliente,
                itens,
                rs.getDouble("VALOR"),
                dataPedido,
                StatusPedido.toEnum(rs.getString("STATUS")),
                rs.getString("ENDERECO"),
                FormaDePagamento.toEnum(rs.getString("FORMA_PAGAMENTO"))
        );
    }

    @Override
    public Pedido findOne(Integer id) {
        Pedido pedido = null;
        String sql = "SELECT * FROM PEDIDO WHERE id = ?";
        try(PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                pedido = resultSetToEntity(rs);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return pedido;
    }

    @Override
    public List<Pedido> listAll() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM PEDIDO ";
        try(PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Pedido pedido = resultSetToEntity(rs);
                pedidos.add(pedido);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return pedidos;
    }

    @Override
    public boolean update(Pedido pedido) {
        String sql = "UPDATE PEDIDO SET valor = ?, datapedido = ?, status = ?, forma_pagamento = ?, cpf_cliente = ? WHERE id = ?";
        try(PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {
            ps.setString(1, pedido.getValor().toString());
            ps.setString(2, pedido.getDataPedido().toString());
            ps.setString(3, pedido.getStatus().toString());
            ps.setString(4, pedido.getPagamento().toString());
            ps.setString(5, pedido.getCliente().getCpf());
            ps.setInt(5, pedido.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM PEDIDO WHERE id = ?";
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
    public List<Pedido> findByDate(LocalDate data) {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM PEDIDO WHERE DATAPEDIDO = ?";
        try(PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {
            ps.setString(1, data.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Pedido pedido = resultSetToEntity(rs);
                pedidos.add(pedido);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return pedidos;
    }

    @Override
    public List<Pedido> findByPeriodo(LocalDate data, LocalDate dataFinal) {
        return null;
    }

    @Override
    public List<Pedido> findByCpfCliente(String cpfCliente) {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM PEDIDO WHERE cpf_cliente = ?";
        try(PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {
            ps.setString(1, cpfCliente);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Pedido pedido = resultSetToEntity(rs);
                pedidos.add(pedido);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return pedidos;
    }

    @Override
    public List<Pedido> findByStatus(StatusPedido statusPedido) {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM PEDIDO WHERE STATUS = ?";
        try(PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {
            ps.setString(1, statusPedido.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Pedido pedido = resultSetToEntity(rs);
                pedidos.add(pedido);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return pedidos;
    }

    @Override
    public Optional<Pedido> findById(Integer id) {
        Pedido pedido = findOne(id);
        return Optional.ofNullable(pedido);
    }

    @Override
    public Optional<Cliente> findClienteByCpf(String cpf) {
        return Optional.empty();
    }

    @Override
    public boolean atualizarStatusPedido(Pedido pedido) {
        return update(pedido);
    }

    @Override
    public boolean escolherMetodoPagamento(Pedido pedido) {
        return false;
    }

    @Override
    public boolean informarEntrega(Pedido pedido) {
        return false;
    }
}
