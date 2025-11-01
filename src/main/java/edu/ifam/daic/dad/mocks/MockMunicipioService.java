package edu.ifam.daic.dad.mocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockMunicipioService {
    private Map<String, List<String>> municipiosPorEstado;

    public MockMunicipioService() {
        // Simula um banco de dados de municípios
        municipiosPorEstado = new HashMap<>();

        List<String> municipiosAM = new ArrayList<>();
        municipiosAM.add("Manaus");
        municipiosAM.add("Parintins");
        municipiosAM.add("Itacoatiara");
        municipiosPorEstado.put("AM", municipiosAM);

        List<String> municipiosAC = new ArrayList<>();
        municipiosAC.add("Rio Branco");
        municipiosAC.add("Cruzeiro do Sul");
        municipiosPorEstado.put("AC", municipiosAC);

        List<String> municipiosSP = new ArrayList<>();
        municipiosSP.add("São Paulo");
        municipiosSP.add("Campinas");
        municipiosPorEstado.put("SP", municipiosSP);
    }

    /**
     * Simula a busca de municípios por UF.
     */
    public List<String> getMunicipios(String uf) {
        // Simula uma chamada de rede
        try { Thread.sleep(100); } catch (Exception e) {}

        return municipiosPorEstado.getOrDefault(uf, new ArrayList<>());
    }
}
