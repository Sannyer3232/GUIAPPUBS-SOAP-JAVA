
package soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the soap package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _BuscarCep_QNAME = new QName("http://service.dad.ifam.edu.br/", "buscarCep");
    private final static QName _Endereco_QNAME = new QName("http://service.dad.ifam.edu.br/", "endereco");
    private final static QName _BuscarCepResponse_QNAME = new QName("http://service.dad.ifam.edu.br/", "buscarCepResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Cep }
     * 
     */
    public Cep createCep() {
        return new Cep();
    }

    /**
     * Create an instance of {@link BuscarCepResponse }
     * 
     */
    public BuscarCepResponse createBuscarCepResponse() {
        return new BuscarCepResponse();
    }

    /**
     * Create an instance of {@link BuscarCep }
     * 
     */
    public BuscarCep createBuscarCep() {
        return new BuscarCep();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BuscarCep }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.dad.ifam.edu.br/", name = "buscarCep")
    public JAXBElement<BuscarCep> createBuscarCep(BuscarCep value) {
        return new JAXBElement<BuscarCep>(_BuscarCep_QNAME, BuscarCep.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Cep }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.dad.ifam.edu.br/", name = "endereco")
    public JAXBElement<Cep> createEndereco(Cep value) {
        return new JAXBElement<Cep>(_Endereco_QNAME, Cep.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BuscarCepResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.dad.ifam.edu.br/", name = "buscarCepResponse")
    public JAXBElement<BuscarCepResponse> createBuscarCepResponse(BuscarCepResponse value) {
        return new JAXBElement<BuscarCepResponse>(_BuscarCepResponse_QNAME, BuscarCepResponse.class, null, value);
    }

}
