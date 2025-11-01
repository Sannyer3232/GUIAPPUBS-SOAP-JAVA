package edu.ifam.daic.dad.mocks;

import edu.ifam.daic.dad.DTO.UbsDTO;

import java.util.ArrayList;
import java.util.List;

public class MockUbsService {

    public List<UbsDTO> getListUbs(String municipio) {
        // Simula uma chamada de rede
        try { Thread.sleep(500); } catch (Exception e) {}

        List<UbsDTO> lista = new ArrayList<>();

        if (municipio.equals("Manaus")) {
            // UBS com endereço completo (não vai chamar o CEP)
            lista.add(new UbsDTO("UBS Japiim", "69077-000", "Av. Rodrigo Otávio", "Japiim", "Manaus", "AM"));

            // ** TESTE IMPORTANTE (CEP real do seu serviço) **
            lista.add(new UbsDTO("UBS Morro da Liberdade", "69074-650", null, null, "Manaus", "AM"));

            UbsDTO ubsArthur = new UbsDTO();

            ubsArthur.setNome("UBS Arthur");
            ubsArthur.setCep("69099-000");

            lista.add(ubsArthur);
        }
        if (municipio.equals("Rio Branco")) {
            // ** TESTE IMPORTANTE 2 (CEP real do seu serviço) **
            lista.add(new UbsDTO("UBS Bairro Sobral", "69903-695", null, null, "Rio Branco", "AC"));
        }
        return lista;
    }
}
