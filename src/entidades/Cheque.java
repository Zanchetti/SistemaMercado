/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Marcos
 */
@Entity
@DiscriminatorValue(value = "c")
public class Cheque extends Pagamento implements Serializable {

    protected String banco;
    protected int numero;

    public Cheque() {
    }

    public Cheque(String banco, int numero, double valorPagamento) {
        super(valorPagamento);
        this.banco = banco;
        this.numero = numero;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Pagamento em cheque: R$" + valorPagamento + "; " + banco + "; Cheque número: " + numero;
    }
}
