package ifsp.edu.sqlitedao;

import ifsp.edu.enums.FormaDePagamento;
import ifsp.edu.enums.StatusPedido;
import ifsp.edu.model.Cliente;
import ifsp.edu.model.Item;
import ifsp.edu.model.Pedido;
import ifsp.edu.model.Produto;
import ifsp.edu.usecases.produto.ProdutoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl {


    public boolean insert(Item item){
        String sql = "INSERT INTO ITEM(valorvenda, quantidade, id_produto, id_pedido) VALUES( ?, ?, ?, ?)";
        try(PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {
            ps.setDouble(1, item.getValorVenda());
            ps.setInt(2, item.getQuantidade());
            ps.setInt(3,item.getProduto().getId());
            ps.setInt(4, item.getPedido().getId());
            ps.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public List<Item> listAll(Integer idPedido) {
        String sql = "SELECT * FROM ITEM WHERE ID_PEDIDO = ?";
        List<Item> items = new ArrayList<>();
        try (PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {
            ps.setInt(1, idPedido);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Item item = new Item();
                item.setValorVenda(rs.getDouble("VALORVENDA"));
                item.setQuantidade(rs.getInt("QUANTIDADE"));

                ProdutoDAO daoProduto = new ProdutoDAOImpl();
                Produto p = daoProduto.findOne(rs.getInt("ID_PRODUTO"));

                item.setProduto(p);

                items.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;


    }

//    private Item resultSetToEntity(ResultSet rs) throws SQLException {
//        String sql = "SELECT * FROM ITEM INNER JOIN PEDIDO WHERE ITEM.ID_PEDIDO = PEDIDO.ID ";
//
//        Cliente cliente = daoCliente.findOne(rs.getString("CPF_CLIENTE"));
////        List<Item> itens = itemDao.listAll(rs.getInt(ID));
//        String data = rs.getString("DATAPEDIDO");
////        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        LocalDate dataPedido = LocalDate.parse(data);
//        return new Pedido(
//                rs.getInt("ID"),
//                cliente,
//                itens,
//                rs.getDouble("VALOR"),
//                dataPedido,
//                StatusPedido.toEnum(rs.getString("STATUS")),
//                rs.getString("ENDERECO"),
//                FormaDePagamento.toEnum(rs.getString("FORMA_PAGAMENTO"))
//        );
//    }



}
