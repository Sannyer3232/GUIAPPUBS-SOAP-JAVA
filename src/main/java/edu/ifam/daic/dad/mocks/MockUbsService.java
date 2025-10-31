package edu.ifam.daic.dad.mocks;

import edu.ifam.daic.dad.DTO.UbsDTO;

import java.util.ArrayList;
import java.util.List;

public class MockUbsService {

    public List<UbsDTO> getListUbs(String uf) {
        // Simula uma chamada de rede
        try { Thread.sleep(500); } catch (Exception e) {}

        List<UbsDTO> lista = new ArrayList<>();
        if (uf.equals("AM")) {
            // UBS com endereço completo (não vai chamar o CEP)
            lista.add(new UbsDTO("UBS Japiim", "69077-000", "Av. Rodrigo Otávio", "Japiim", "Manaus", "AM"));

            UbsDTO ubsArthur = new UbsDTO();

            ubsArthur.setUf("AM");
            ubsArthur.setNome("Ubs Arthur");
            ubsArthur.setCep("69099-000");

            lista.add(ubsArthur);
            // ** O TESTE IMPORTANTE **
            // UBS com endereço incompleto (vai chamar o CEP)
            // Use um CEP que sabemos que existe no seu serviço real
            lista.add(new UbsDTO("UBS Morro da Liberdade", "69074-650", null, null, "Manaus", "AM"));
        }
        if (uf.equals("AC")) {
            // ** O TESTE IMPORTANTE 2 **
            // CEP 69903-695 (20 de Novembro) que usamos nos testes anteriores
            lista.add(new UbsDTO("UBS Bairro Sobral", "69903-695", null, null, "Rio Branco", "AC"));
        }
        return lista;
    }
}
