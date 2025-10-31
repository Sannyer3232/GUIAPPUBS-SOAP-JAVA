package edu.ifam.daic.dad.mocks;

import edu.ifam.daic.dad.DTO.DadosDemograficosDTO;

public class MockDemographicsService {

    public DadosDemograficosDTO getDados(String uf) {
        // Simula uma chamada de rede
        try { Thread.sleep(200); } catch (Exception e) {}

        if (uf.equals("AM")) {
            // Dados fictícios para o Amazonas
            return new DadosDemograficosDTO(
                    4207714,  // Pop Total
                    2112700,  // Homens
                    2095014,  // Mulheres
                    800000,   // 0-10
                    850000,   // 11-20
                    900000,   // 21-30
                    750000,   // 31-40
                    907714    // 41+
            );
        }
        if (uf.equals("AC")) {
            // Dados fictícios para o Acre
            return new DadosDemograficosDTO(
                    894470,   // Pop Total
                    447200,   // Homens
                    447270,   // Mulheres
                    150000,   // 0-10
                    160000,   // 11-20
                    170000,   // 21-30
                    200000,   // 31-40
                    214470    // 41+
            );
        }
        // Retorno padrão
        return new DadosDemograficosDTO(0, 0, 0, 0, 0, 0, 0, 0);
    }
}
