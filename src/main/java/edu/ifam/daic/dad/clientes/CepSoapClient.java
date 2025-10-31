package edu.ifam.daic.dad.clientes;

import soap.Cep;
import soap.CepSOAPService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

public class CepSoapClient {

    private CepSOAPService port;

    public CepSoapClient() throws Exception {
        URL wsdlURL = new URL("http://localhost:8088/cep?wsdl");
        // Namespace (pacote da sua interface, ao contrário)
        String namespace = "http://service.dad.ifam.edu.br/";
        // Nome do Serviço (do @WebService na sua *Implementação*)
        String serviceName = "CepSOAPServiceImplService";

        QName serviceQName = new QName(namespace, serviceName);
        Service service = Service.create(wsdlURL, serviceQName);
        this.port = service.getPort(CepSOAPService.class);
    }

    public Cep buscarCep(String cep) {
        try {
            return port.buscarCep(cep);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Retorna nulo se der erro
        }
    }
}
