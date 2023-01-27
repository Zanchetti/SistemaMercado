
import daos.DaoCliente;
import daos.DaoVenda;
import entidades.Cliente;
import entidades.Pagamento;
import entidades.Venda;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Marcos
 */
//na classes dinheiro precisa arrumar o toString na questão do troco
public class Sistema {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        DaoCliente daoCliente = new DaoCliente();
        DaoVenda daoVenda = new DaoVenda();

        int opcao = 0;

        do {
            System.out.println("");
            System.out.println("0-Sair");
            System.out.println("1-Cadastro Cliente");
            System.out.println("2-Cadastro de vendas e pagamentos");
            System.out.println("3-Listar tudo");
            System.out.println("4-Listar vendas pela data");
            System.out.print("INFORME A OPÇÃO: ");
            opcao = s.nextInt();
            switch (opcao) {
                case 1: {
                    //cadastrar cliente
                    int verifica = 0;
                    String cpf;
                    do {
                        System.out.print("Informe o cpf do cliente: ");
                        cpf = s.next();
                        if (daoCliente.selecionar(cpf) != null) {
                            verifica = 0;
                            System.out.println("Já existe um cadastro com esse cpf!");
                        } else {
                            verifica = 1;
                        }
                    } while (verifica == 0);

                    System.out.print("Informe o nome do cliente:  ");
                    String nome = s.next();
                    System.out.print("Informe a data de nascimento do cliente:  ");
                    String dataNascimento = s.next();
                    Cliente c = new Cliente(cpf, nome, dataNascimento);
                    if (daoCliente.inserir(c)) {
                        System.out.println("Sucesso ao cadastrar o cliente!");
                    } else {
                        System.out.println("Falha ao cadastrar o cliente!");
                    }
                    break;
                }

                case 2: {
                    //cadastrar vendas
                    System.out.print("Informe a data da venda: ");
                    String data = s.next();
                    System.out.print("Informe o valor da venda: ");
                    double valor = s.nextDouble();
                    System.out.print("Informe o cpf do cliente: ");
                    String cpf = s.next();
                    Cliente c = daoCliente.selecionar(cpf);
                    Venda v = new Venda(data, false, valor, 0, c);
                    if (daoVenda.inserir(v)) {
                        System.out.println("");
                        System.out.println("Sucesso ao cadastrar sua compra!");
                        System.out.println("");
                    } else {
                        System.out.println("Falha ao inserir!");
                        break;
                    }
                    double pagamentosTotais = 0;
                    double valorPagamento = 0;
                    do {
                        int verifica2 = 0;
                        int verifica3 = 0;
                        int tipo = 0;
                        do {
                            System.out.print("Pagamento: Insira 1 para dinheiro e 2 para cheque: ");
                            tipo = s.nextInt();
                            if (tipo == 1 || tipo == 2) {
                                verifica2 = 1;
                            } else {
                                System.out.println("TENTE NOVAMENTE!");
                            }
                        } while (verifica2 == 0);
                        System.out.print("Quanto será pago pelo meio escolhido: R$");
                        valorPagamento = s.nextDouble();
                        if (tipo == 1) {
                            int verifica = 0;
                            double quantidadeEntrada = 0;
                            do {
                                System.out.print("Informe a quantidade de entrada: R$");
                                quantidadeEntrada = s.nextDouble();
                                if (quantidadeEntrada < valorPagamento) {
                                    System.out.println("Dê uma entrada maior ou igual ao valor que você deseja pagar!");
                                } else {
                                    verifica = 1;
                                }
                            } while (verifica == 0);
                            pagamentosTotais = pagamentosTotais + valorPagamento;
                            double troco = quantidadeEntrada - valorPagamento;
                            if (pagamentosTotais > valor) {
                                if (valor < valorPagamento) {
                                    valorPagamento = valor - (pagamentosTotais - valorPagamento);
                                } else {
                                    valorPagamento = pagamentosTotais - valor;
                                }
                                System.out.println("Seu troco será de: R$" + (pagamentosTotais - valor + troco));
                                pagamentosTotais = pagamentosTotais - (pagamentosTotais - valor);
                            } else if (troco > 0) {
                                System.out.println("O seu troco será de: R$" + troco);
                            }
                            v = daoVenda.selecionar(v.getId());
                            v.adicionarPagamentoDinheiro(valorPagamento, quantidadeEntrada);
                            if (daoVenda.editar(v)) {
                                System.out.println("Sucesso ao registrar o seu pagamento!");
                            } else {
                                System.out.println("Falha ao registrar seu pagamento!");
                                break;
                            }
                            verifica2 = 0;
                        } else if (tipo == 2) {
                            System.out.print("Informe o nome do banco: ");
                            String banco = s.next();
                            System.out.print("Informe o número da conta: ");
                            int numero = s.nextInt();
                            pagamentosTotais = pagamentosTotais + valorPagamento;
                            if (pagamentosTotais > valor) {
                                if (valor < valorPagamento) {
                                    valorPagamento = valor - (pagamentosTotais - valorPagamento);
                                } else {
                                    valorPagamento = pagamentosTotais - valor;
                                }
                                System.out.println("Seu troco será de: R$" + (pagamentosTotais - valor));
                                pagamentosTotais = pagamentosTotais - (pagamentosTotais - valor);
                            }
                            v = daoVenda.selecionar(v.getId());
                            v.adicionarPagamentoCheque(valorPagamento, banco, numero);
                            if (daoVenda.editar(v)) {
                                System.out.println("Sucesso ao registrar o seu pagamento!");
                            } else {
                                System.out.println("Falha ao registrar seu pagamento");
                                break;
                            }
                            verifica2 = 0;
                        }
                    } while (valor > pagamentosTotais);
                    if (valor == pagamentosTotais) {
                        v.setValorPago(pagamentosTotais);
                        v.setQuitada(true);
                        if (daoVenda.editar(v)) {
                            System.out.println("Sua compra foi paga com sucesso!");
                        } else {
                            System.out.println("ERRO!");
                            break;
                        }
                        break;
                    }
                    break;
                }

                case 3: {
                    //listagem das vendas cadastradas com seus respectivos pagamentos
                    for (Venda v : daoVenda.listar()) {
                        System.out.println("");
                        System.out.println(v.toString());
                        for (Pagamento p : v.getPagamentos()) {
                            System.out.println(p.toString());
                        }
                    }
                    break;
                }

                case 4: {
                    //listagem de vendas de uma determinada data, apresentando inclusive os pagamentos de cada venda
                    System.out.print("Informe a data da venda para consulta: ");
                    String data = s.next();
                    for (Venda v : daoVenda.listarVendasData(data)) {
                        System.out.println("");
                        System.out.println(v.toString());
                        for (Pagamento p : v.getPagamentos()) {
                            System.out.println(p.toString());
                        }
                    }
                    break;
                }

            }
        } while (opcao != 0);
        System.out.println("----------ADEUS----------");
        daoVenda.fechar();
    }
}
