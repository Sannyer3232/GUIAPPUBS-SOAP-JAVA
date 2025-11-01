
package edu.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de municipio complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="municipio">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cd_mun" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nm_mun" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="total_moradores" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="sexo_masculino" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="sexo_feminino" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="sexo_idade0a9" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="sexo_idade10a19" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="sexo_idade20a39" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="sexo_idade40mais" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "municipio", propOrder = {
    "cdMun",
    "estado",
    "nmMun",
    "totalMoradores",
    "sexoMasculino",
    "sexoFeminino",
    "sexoIdade0A9",
    "sexoIdade10A19",
    "sexoIdade20A39",
    "sexoIdade40Mais"
})
public class Municipio {

    @XmlElement(name = "cd_mun")
    protected int cdMun;
    protected String estado;
    @XmlElement(name = "nm_mun")
    protected String nmMun;
    @XmlElement(name = "total_moradores")
    protected int totalMoradores;
    @XmlElement(name = "sexo_masculino")
    protected int sexoMasculino;
    @XmlElement(name = "sexo_feminino")
    protected int sexoFeminino;
    @XmlElement(name = "sexo_idade0a9")
    protected int sexoIdade0A9;
    @XmlElement(name = "sexo_idade10a19")
    protected int sexoIdade10A19;
    @XmlElement(name = "sexo_idade20a39")
    protected int sexoIdade20A39;
    @XmlElement(name = "sexo_idade40mais")
    protected int sexoIdade40Mais;

    /**
     * Obtém o valor da propriedade cdMun.
     * 
     */
    public int getCdMun() {
        return cdMun;
    }

    /**
     * Define o valor da propriedade cdMun.
     * 
     */
    public void setCdMun(int value) {
        this.cdMun = value;
    }

    /**
     * Obtém o valor da propriedade estado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Define o valor da propriedade estado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstado(String value) {
        this.estado = value;
    }

    /**
     * Obtém o valor da propriedade nmMun.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNmMun() {
        return nmMun;
    }

    /**
     * Define o valor da propriedade nmMun.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNmMun(String value) {
        this.nmMun = value;
    }

    /**
     * Obtém o valor da propriedade totalMoradores.
     * 
     */
    public int getTotalMoradores() {
        return totalMoradores;
    }

    /**
     * Define o valor da propriedade totalMoradores.
     * 
     */
    public void setTotalMoradores(int value) {
        this.totalMoradores = value;
    }

    /**
     * Obtém o valor da propriedade sexoMasculino.
     * 
     */
    public int getSexoMasculino() {
        return sexoMasculino;
    }

    /**
     * Define o valor da propriedade sexoMasculino.
     * 
     */
    public void setSexoMasculino(int value) {
        this.sexoMasculino = value;
    }

    /**
     * Obtém o valor da propriedade sexoFeminino.
     * 
     */
    public int getSexoFeminino() {
        return sexoFeminino;
    }

    /**
     * Define o valor da propriedade sexoFeminino.
     * 
     */
    public void setSexoFeminino(int value) {
        this.sexoFeminino = value;
    }

    /**
     * Obtém o valor da propriedade sexoIdade0A9.
     * 
     */
    public int getSexoIdade0A9() {
        return sexoIdade0A9;
    }

    /**
     * Define o valor da propriedade sexoIdade0A9.
     * 
     */
    public void setSexoIdade0A9(int value) {
        this.sexoIdade0A9 = value;
    }

    /**
     * Obtém o valor da propriedade sexoIdade10A19.
     * 
     */
    public int getSexoIdade10A19() {
        return sexoIdade10A19;
    }

    /**
     * Define o valor da propriedade sexoIdade10A19.
     * 
     */
    public void setSexoIdade10A19(int value) {
        this.sexoIdade10A19 = value;
    }

    /**
     * Obtém o valor da propriedade sexoIdade20A39.
     * 
     */
    public int getSexoIdade20A39() {
        return sexoIdade20A39;
    }

    /**
     * Define o valor da propriedade sexoIdade20A39.
     * 
     */
    public void setSexoIdade20A39(int value) {
        this.sexoIdade20A39 = value;
    }

    /**
     * Obtém o valor da propriedade sexoIdade40Mais.
     * 
     */
    public int getSexoIdade40Mais() {
        return sexoIdade40Mais;
    }

    /**
     * Define o valor da propriedade sexoIdade40Mais.
     * 
     */
    public void setSexoIdade40Mais(int value) {
        this.sexoIdade40Mais = value;
    }

}
