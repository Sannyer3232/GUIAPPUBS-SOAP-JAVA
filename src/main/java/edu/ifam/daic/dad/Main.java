package edu.ifam.daic.dad;


import soap.CepSOAPService;

import soap.CepSOAPServiceImplService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        CepSOAPServiceImplService service = new CepSOAPServiceImplService();

        CepSOAPService porta = service.getCepSOAPServiceImplPort();

        System.out.println("Buscando Endereço: 69033-320");
        System.out.println(porta.buscarCep("69033-320").getBairro() + " - " + porta.buscarCep("69033-320").getCidade() + " - " + porta.buscarCep("69033-320").getLogradouro());

    }
}