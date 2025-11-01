package edu.ifam.daic.dad.mocks;

import edu.ifam.daic.dad.DTO.DadosDemograficosDTO;

public class MockDemographicsService {

    public DadosDemograficosDTO getDados(String municipio) {
        // Simula uma chamada de rede
        try { Thread.sleep(200); } catch (Exception e) {}

        if (municipio.equals("Manaus")) {
            // Dados fictícios para Manaus
            return new DadosDemograficosDTO(
                    2219580,  // Pop Total
                    1090000,  // Homens
                    1129580,  // Mulheres
                    300000,   // 0-10
                    350000,   // 11-20
                    400000,   // 21-30
                    500000,   // 31-40
                    669580    // 41+
            );
        }
        if (municipio.equals("Rio Branco")) {
            // Dados fictícios para Rio Branco
            return new DadosDemograficosDTO(
                    413418,   // Pop Total
                    200000,   // Homens
                    213418,   // Mulheres
                    80000,    // 0-10
                    85000,    // 11-20
                    90000,    // 21-30
                    80000,    // 31-40
                    78418     // 41+
            );
        }
        // Retorno padrão
        return new DadosDemograficosDTO(0, 0, 0, 0, 0, 0, 0, 0);
    }
}
