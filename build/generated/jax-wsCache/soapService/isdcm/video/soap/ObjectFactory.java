
package isdcm.video.soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the isdcm.video.soap package. 
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

    private final static QName _SearchByAutor_QNAME = new QName("http://soap.video.isdcm/", "searchByAutor");
    private final static QName _SearchByAutorResponse_QNAME = new QName("http://soap.video.isdcm/", "searchByAutorResponse");
    private final static QName _SearchByTitle_QNAME = new QName("http://soap.video.isdcm/", "searchByTitle");
    private final static QName _SearchByTitleResponse_QNAME = new QName("http://soap.video.isdcm/", "searchByTitleResponse");
    private final static QName _SearchByYear_QNAME = new QName("http://soap.video.isdcm/", "searchByYear");
    private final static QName _SearchByYearResponse_QNAME = new QName("http://soap.video.isdcm/", "searchByYearResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: isdcm.video.soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SearchByAutor }
     * 
     */
    public SearchByAutor createSearchByAutor() {
        return new SearchByAutor();
    }

    /**
     * Create an instance of {@link SearchByAutorResponse }
     * 
     */
    public SearchByAutorResponse createSearchByAutorResponse() {
        return new SearchByAutorResponse();
    }

    /**
     * Create an instance of {@link SearchByTitle }
     * 
     */
    public SearchByTitle createSearchByTitle() {
        return new SearchByTitle();
    }

    /**
     * Create an instance of {@link SearchByTitleResponse }
     * 
     */
    public SearchByTitleResponse createSearchByTitleResponse() {
        return new SearchByTitleResponse();
    }

    /**
     * Create an instance of {@link SearchByYear }
     * 
     */
    public SearchByYear createSearchByYear() {
        return new SearchByYear();
    }

    /**
     * Create an instance of {@link SearchByYearResponse }
     * 
     */
    public SearchByYearResponse createSearchByYearResponse() {
        return new SearchByYearResponse();
    }

    /**
     * Create an instance of {@link Video }
     * 
     */
    public Video createVideo() {
        return new Video();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchByAutor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.video.isdcm/", name = "searchByAutor")
    public JAXBElement<SearchByAutor> createSearchByAutor(SearchByAutor value) {
        return new JAXBElement<SearchByAutor>(_SearchByAutor_QNAME, SearchByAutor.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchByAutorResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.video.isdcm/", name = "searchByAutorResponse")
    public JAXBElement<SearchByAutorResponse> createSearchByAutorResponse(SearchByAutorResponse value) {
        return new JAXBElement<SearchByAutorResponse>(_SearchByAutorResponse_QNAME, SearchByAutorResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchByTitle }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.video.isdcm/", name = "searchByTitle")
    public JAXBElement<SearchByTitle> createSearchByTitle(SearchByTitle value) {
        return new JAXBElement<SearchByTitle>(_SearchByTitle_QNAME, SearchByTitle.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchByTitleResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.video.isdcm/", name = "searchByTitleResponse")
    public JAXBElement<SearchByTitleResponse> createSearchByTitleResponse(SearchByTitleResponse value) {
        return new JAXBElement<SearchByTitleResponse>(_SearchByTitleResponse_QNAME, SearchByTitleResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchByYear }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.video.isdcm/", name = "searchByYear")
    public JAXBElement<SearchByYear> createSearchByYear(SearchByYear value) {
        return new JAXBElement<SearchByYear>(_SearchByYear_QNAME, SearchByYear.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchByYearResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.video.isdcm/", name = "searchByYearResponse")
    public JAXBElement<SearchByYearResponse> createSearchByYearResponse(SearchByYearResponse value) {
        return new JAXBElement<SearchByYearResponse>(_SearchByYearResponse_QNAME, SearchByYearResponse.class, null, value);
    }

}
