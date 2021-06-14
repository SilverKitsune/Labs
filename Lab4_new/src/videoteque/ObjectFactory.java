
package videoteque;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the videoteque package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: videoteque
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link VideoLibrary }
     * 
     */
    public VideoLibrary createVideoLibrary() {
        return new VideoLibrary();
    }

    /**
     * Create an instance of {@link VideoLibrary.Movie }
     * 
     */
    public VideoLibrary.Movie createVideoLibraryMovie() {
        return new VideoLibrary.Movie();
    }

    /**
     * Create an instance of {@link VideoLibrary.Clients }
     * 
     */
    public VideoLibrary.Clients createVideoLibraryClients() {
        return new VideoLibrary.Clients();
    }

    /**
     * Create an instance of {@link VideoLibrary.Movie.Director }
     * 
     */
    public VideoLibrary.Movie.Director createVideoLibraryMovieDirector() {
        return new VideoLibrary.Movie.Director();
    }

    /**
     * Create an instance of {@link VideoLibrary.Clients.Client }
     * 
     */
    public VideoLibrary.Clients.Client createVideoLibraryClientsClient() {
        return new VideoLibrary.Clients.Client();
    }

}
