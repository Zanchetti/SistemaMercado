/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Marcos
 */
@Entity
@DiscriminatorValue(value = "d")
public class Dinheiro extends Pagamento implements Serializable {

    protected double quantidadeEntrada;

    public Dinheiro() {
    }

    public Dinheiro(double quantidadeEntrada, double valorPagamento) {
        super(valorPagamento);
        this.quantidadeEntrada = quantidadeEntrada;
    }
    
    public double getQuantidadeEntrada() {
        return quantidadeEntrada;
    }

    public void setQuantidadeEntrada(double quantidadeEntrada) {
        this.quantidadeEntrada = quantidadeEntrada;
    }

    @Override
    public String toString() {
        double troco = quantidadeEntrada-valorPagamento; 
        return "Pagamento em dinheiro: R$" + valorPagamento + "; Quantia entregue: R$" + quantidadeEntrada + "; Troco: R$" + troco;
    }
}
