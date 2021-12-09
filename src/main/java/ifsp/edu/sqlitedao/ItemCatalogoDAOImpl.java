package ifsp.edu.sqlitedao;

import ifsp.edu.model.Catalogo;
import static ifsp.edu.model.Catalogo.*;
import ifsp.edu.model.Item;
import ifsp.edu.model.ItemCatalogo;
import ifsp.edu.model.Produto;
import ifsp.edu.usecases.catalogo.CatalogoDAO;
import ifsp.edu.usecases.produto.ProdutoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemCatalogoDAOImpl {
    static Integer idCatalogo2 = numberOfRows();



    public static int numberOfRows(){
        String sql = "SELECT COUNT(*) as ROWS FROM CATALOGO";
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





    public boolean insert(ItemCatalogo itemCatalogo){



        String sql = "INSERT INTO ITEMCATALOGO(id_catalogo, id_produto, valor) VALUES( ?, ?, ?)";
        try(PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {
            ps.setInt(1, idCatalogo2 );
            ps.setInt(2, itemCatalogo.getProduto().getId());
            ps.setDouble(3, itemCatalogo.getValor());
            ps.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(ItemCatalogo item){
        String sql = "UPDATE ITEMCATALOGO SET id_catalogo = ? WHERE ID_CATALAGO is null" ;
        try (PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {
            ps.setInt(1, item.getCatalogo().getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<ItemCatalogo> listItemsByIdCatalogo(Integer idCatalogo) {
        String sql = "SELECT * FROM ITEMCATALOGO WHERE ID_CATALOGO = ?";
        List<ItemCatalogo> items = new ArrayList<>();
        try (PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {
            ps.setInt(1, idCatalogo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProdutoDAO daoProduto = new ProdutoDAOImpl();
                Produto p = daoProduto.findOne(rs.getInt("ID_PRODUTO"));

                CatalogoDAO daoCatalogo = new CatalogoDAOImpl();
                Catalogo c = daoCatalogo.findOne(rs.getInt("ID_CATALOGO"));

                ItemCatalogo itemCatalogo = new ItemCatalogo(c,p,rs.getDouble("VALOR"));

                items.add(itemCatalogo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;

    }

    public boolean deleteCatalogo(Integer idCatalogo) {
        String sql = "DELETE FROM ITEMCATALOGO WHERE id_catalogo = ?";
        try(PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {
            ps.setInt(1, idCatalogo);
            ps.execute();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteItemCatalogo(ItemCatalogo itemCatalogo) {
        String sql = "DELETE FROM ITEMCATALOGO WHERE id_catalogo = ? and id_produto = ?";
        try(PreparedStatement ps = ConnectionFactory.criarPreparedStatement(sql)) {
            ps.setInt(1, itemCatalogo.getCatalogo().getId());
            ps.setInt(1, itemCatalogo.getProduto().getId());
            ps.execute();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
