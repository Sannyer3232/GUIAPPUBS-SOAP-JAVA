
package edu.soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the edu.soap package. 
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

    private final static QName _Teste_QNAME = new QName("http://soap.edu/", "teste");
    private final static QName _EstadosResponse_QNAME = new QName("http://soap.edu/", "estadosResponse");
    private final static QName _Estado_QNAME = new QName("http://soap.edu/", "estado");
    private final static QName _ListMunicipios_QNAME = new QName("http://soap.edu/", "listMunicipios");
    private final static QName _GetEstadoResponse_QNAME = new QName("http://soap.edu/", "getEstadoResponse");
    private final static QName _GetEstado_QNAME = new QName("http://soap.edu/", "getEstado");
    private final static QName _Municipio_QNAME = new QName("http://soap.edu/", "municipio");
    private final static QName _ListMunicipiosResponse_QNAME = new QName("http://soap.edu/", "listMunicipiosResponse");
    private final static QName _GetMunicipioResponse_QNAME = new QName("http://soap.edu/", "getMunicipioResponse");
    private final static QName _Estados_QNAME = new QName("http://soap.edu/", "estados");
    private final static QName _GetMunicipio_QNAME = new QName("http://soap.edu/", "getMunicipio");
    private final static QName _TesteResponse_QNAME = new QName("http://soap.edu/", "testeResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: edu.soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EstadosResponse }
     * 
     */
    public EstadosResponse createEstadosResponse() {
        return new EstadosResponse();
    }

    /**
     * Create an instance of {@link Teste }
     * 
     */
    public Teste createTeste() {
        return new Teste();
    }

    /**
     * Create an instance of {@link Estado }
     * 
     */
    public Estado createEstado() {
        return new Estado();
    }

    /**
     * Create an instance of {@link ListMunicipios }
     * 
     */
    public ListMunicipios createListMunicipios() {
        return new ListMunicipios();
    }

    /**
     * Create an instance of {@link GetEstadoResponse }
     * 
     */
    public GetEstadoResponse createGetEstadoResponse() {
        return new GetEstadoResponse();
    }

    /**
     * Create an instance of {@link Municipio }
     * 
     */
    public Municipio createMunicipio() {
        return new Municipio();
    }

    /**
     * Create an instance of {@link GetEstado }
     * 
     */
    public GetEstado createGetEstado() {
        return new GetEstado();
    }

    /**
     * Create an instance of {@link ListMunicipiosResponse }
     * 
     */
    public ListMunicipiosResponse createListMunicipiosResponse() {
        return new ListMunicipiosResponse();
    }

    /**
     * Create an instance of {@link GetMunicipioResponse }
     * 
     */
    public GetMunicipioResponse createGetMunicipioResponse() {
        return new GetMunicipioResponse();
    }

    /**
     * Create an instance of {@link GetMunicipio }
     * 
     */
    public GetMunicipio createGetMunicipio() {
        return new GetMunicipio();
    }

    /**
     * Create an instance of {@link TesteResponse }
     * 
     */
    public TesteResponse createTesteResponse() {
        return new TesteResponse();
    }

    /**
     * Create an instance of {@link Estados }
     * 
     */
    public Estados createEstados() {
        return new Estados();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Teste }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.edu/", name = "teste")
    public JAXBElement<Teste> createTeste(Teste value) {
        return new JAXBElement<Teste>(_Teste_QNAME, Teste.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EstadosResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.edu/", name = "estadosResponse")
    public JAXBElement<EstadosResponse> createEstadosResponse(EstadosResponse value) {
        return new JAXBElement<EstadosResponse>(_EstadosResponse_QNAME, EstadosResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Estado }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.edu/", name = "estado")
    public JAXBElement<Estado> createEstado(Estado value) {
        return new JAXBElement<Estado>(_Estado_QNAME, Estado.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListMunicipios }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.edu/", name = "listMunicipios")
    public JAXBElement<ListMunicipios> createListMunicipios(ListMunicipios value) {
        return new JAXBElement<ListMunicipios>(_ListMunicipios_QNAME, ListMunicipios.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEstadoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.edu/", name = "getEstadoResponse")
    public JAXBElement<GetEstadoResponse> createGetEstadoResponse(GetEstadoResponse value) {
        return new JAXBElement<GetEstadoResponse>(_GetEstadoResponse_QNAME, GetEstadoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEstado }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.edu/", name = "getEstado")
    public JAXBElement<GetEstado> createGetEstado(GetEstado value) {
        return new JAXBElement<GetEstado>(_GetEstado_QNAME, GetEstado.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Municipio }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.edu/", name = "municipio")
    public JAXBElement<Municipio> createMunicipio(Municipio value) {
        return new JAXBElement<Municipio>(_Municipio_QNAME, Municipio.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListMunicipiosResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.edu/", name = "listMunicipiosResponse")
    public JAXBElement<ListMunicipiosResponse> createListMunicipiosResponse(ListMunicipiosResponse value) {
        return new JAXBElement<ListMunicipiosResponse>(_ListMunicipiosResponse_QNAME, ListMunicipiosResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMunicipioResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.edu/", name = "getMunicipioResponse")
    public JAXBElement<GetMunicipioResponse> createGetMunicipioResponse(GetMunicipioResponse value) {
        return new JAXBElement<GetMunicipioResponse>(_GetMunicipioResponse_QNAME, GetMunicipioResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Estados }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.edu/", name = "estados")
    public JAXBElement<Estados> createEstados(Estados value) {
        return new JAXBElement<Estados>(_Estados_QNAME, Estados.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMunicipio }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.edu/", name = "getMunicipio")
    public JAXBElement<GetMunicipio> createGetMunicipio(GetMunicipio value) {
        return new JAXBElement<GetMunicipio>(_GetMunicipio_QNAME, GetMunicipio.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TesteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.edu/", name = "testeResponse")
    public JAXBElement<TesteResponse> createTesteResponse(TesteResponse value) {
        return new JAXBElement<TesteResponse>(_TesteResponse_QNAME, TesteResponse.class, null, value);
    }

}
