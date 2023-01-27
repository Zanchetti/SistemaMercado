/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Marcos
 */
@Entity
@Table(name = "vendas")
public class Venda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String data;
    private boolean quitada;
    private double valor;
    private double valorPago;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_vendas")
    private List<Pagamento> listaPagamentos = new ArrayList<>();

    public Venda() {
    }

    public Venda(String data, boolean quitada, double valor, double valorPago, Cliente cliente) {
        this.data = data;
        this.quitada = quitada;
        this.valor = valor;
        this.valorPago = valorPago;
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isQuitada() {
        return quitada;
    }

    public void setQuitada(boolean quitada) {
        this.quitada = quitada;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Pagamento> getPagamentos() {
        return listaPagamentos;
    }

    public void adicionarPagamentoDinheiro(double quantidadeEntrada, double valorPagamento) {
        Pagamento pagamentoDinheiro = new Dinheiro(valorPagamento, quantidadeEntrada);
        this.listaPagamentos.add(pagamentoDinheiro);
    }

    public void adicionarPagamentoCheque(double valorPagamento, String banco, int numero) {
        Pagamento pagamentoCheque = new Cheque(banco, numero, valorPagamento);
        this.listaPagamentos.add(pagamentoCheque);
    }

    @Override
    public String toString() {
        String varQuitada = "";
        if (quitada == true) {
            varQuitada = "Sim";
        } else {
            varQuitada = "Não";
        }
        System.out.println("-----------------------------------------");
        return "Data: " + data + "; Valor: R$" + valor + "; Valor Pago: R$" + valorPago + "; Quitada: " + varQuitada;
    }
}
