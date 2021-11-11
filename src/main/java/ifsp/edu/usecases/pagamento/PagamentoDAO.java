package ifsp.edu.usecases.pagamento;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PagamentoDAO {
    public void atualizaStatusPedido(Integer idPedido){
        StringBuilder sb = new StringBuilder();
        sb.append("update status from pedido ");
        sb.append("where id = ?");
        PreparedStatement ps = null;
        try{
//            ps = getConnection().prepareCall()(sb.toString());
            ps.setInt(1, idPedido);
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
