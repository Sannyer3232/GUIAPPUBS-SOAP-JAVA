package edu.ifam.daic.dad;


import edu.soap.Estado;
import edu.soap.IBGEService;
import edu.soap.IBGEServiceImplementService;
import edu.soap.Municipio;

public class TesteIBGE {

    public static void main(String[] args) {

        IBGEServiceImplementService service = new IBGEServiceImplementService();

        IBGEService porta = service.getIBGEServiceImplementPort();

        for(Estado estado : porta.estados()){
            System.out.println(estado.getNome());
        }

        for(Municipio municipio : porta.listMunicipios("Amazonas")){
            System.out.println(municipio.getNmMun());
        }


    }
}
