package ifsp.edu.view;

import ifsp.edu.enums.FormaDePagamento;
import ifsp.edu.enums.StatusPedido;
import ifsp.edu.model.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Cliente cliente = new Cliente("489751","Natan","Olá","222","2000");
        Fornecedor fornecedor = new Fornecedor("1","dois0","3","5000","São carlos", "Oi");
        Produto p1 = new Produto("Pão",2,"pacote",5.0, 9.0, fornecedor);
        Produto p2 = new Produto("abacate",3,"pacote",1.0, 9.0, fornecedor);
        Produto p3 = new Produto("arroz",4,"pacote",3.0, 8.0, fornecedor);
        Produto p4 = new Produto("rúcula",5,"pacote",4.0, 11.0, fornecedor);
        Produto p5 = new Produto("alface",6,"pacote",8.0, 10.0, fornecedor);

        Item item1 = new Item(4,p1);
        Item item2 = new Item(1,p1);
        String end = ("endereço asdfgh");
        FormaDePagamento pagamento = FormaDePagamento.CARTAO;
        Pedido pedido  = new Pedido(1, cliente, LocalDate.now(), StatusPedido.A_PAGAR, end, pagamento);
        pedido.addItems(item1);
        pedido.addItems(item2);
        pedido.calculaTotalPedido();
        System.out.println(pedido.getValor());
        //        System.out.println(pedido.getValor());

        Renda renda = new Renda(1, LocalDate.now());

        renda.addPedido(pedido);
        renda.addProduto(p1);
        renda.addProduto(p2);
        renda.addProduto(p3);
        renda.addProduto(p4);
        renda.addProduto(p5);
        System.out.println(renda.calculaValorDespesa());

        System.out.println(renda.atualizarRenda());



    }

}
