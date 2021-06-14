
package videoteque;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="clients">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="client" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="movies_count" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="total_price" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="movie" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="director" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="genre" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *                   &lt;element name="year">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *                         &lt;minInclusive value="1888"/>
 *                         &lt;maxInclusive value="2021"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="rating">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}float">
 *                         &lt;minInclusive value="1.0"/>
 *                         &lt;maxInclusive value="10.0"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="type">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;enumeration value="dvd"/>
 *                       &lt;enumeration value="blueRay"/>
 *                       &lt;enumeration value="vhs"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "clients",
        "movie"
})
@XmlRootElement(name = "videoLibrary")
public class VideoLibrary {

    @XmlElement(required = true)
    protected VideoLibrary.Clients clients;
    @XmlElement(required = true)
    protected List<VideoLibrary.Movie> movie;

    /**
     * Gets the value of the clients property.
     *
     * @return possible object is
     * {@link VideoLibrary.Clients }
     */
    public VideoLibrary.Clients getClients() {
        return clients;
    }

    /**
     * Sets the value of the clients property.
     *
     * @param value allowed object is
     *              {@link VideoLibrary.Clients }
     */
    public void setClients(VideoLibrary.Clients value) {
        this.clients = value;
    }

    /**
     * Gets the value of the movie property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the movie property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMovie().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VideoLibrary.Movie }
     */
    public List<VideoLibrary.Movie> getMovie() {
        if (movie == null) {
            movie = new ArrayList<VideoLibrary.Movie>();
        }
        return this.movie;
    }

    public String toString() {
        return "My Video Library:\n" + movie + "\n" + clients;
    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="client" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="movies_count" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="total_price" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "client"
    })
    public static class Clients {

        @XmlElement(required = true)
        protected List<VideoLibrary.Clients.Client> client;

        /**
         * Gets the value of the client property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the client property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getClient().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link VideoLibrary.Clients.Client }
         */
        public List<VideoLibrary.Clients.Client> getClient() {
            if (client == null) {
                client = new ArrayList<VideoLibrary.Clients.Client>();
            }
            return this.client;
        }

        @Override
        public String toString() {
            return "Clients{" + client + '}';
        }

        /**
         * <p>Java class for anonymous complex type.
         *
         * <p>The following schema fragment specifies the expected content contained within this class.
         *
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="movies_count" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="total_price" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "name",
                "moviesCount",
                "totalPrice"
        })
        public static class Client {

            @XmlElement(required = true)
            protected String name;
            @XmlElement(name = "movies_count")
            protected int moviesCount;
            @XmlElement(name = "total_price")
            protected int totalPrice;

            /**
             * Gets the value of the name property.
             *
             * @return possible object is
             * {@link String }
             */
            public String getName() {
                return name;
            }

            /**
             * Sets the value of the name property.
             *
             * @param value allowed object is
             *              {@link String }
             */
            public void setName(String value) {
                this.name = value;
            }

            /**
             * Gets the value of the moviesCount property.
             */
            public int getMoviesCount() {
                return moviesCount;
            }

            /**
             * Sets the value of the moviesCount property.
             */
            public void setMoviesCount(int value) {
                this.moviesCount = value;
            }

            /**
             * Gets the value of the totalPrice property.
             */
            public int getTotalPrice() {
                return totalPrice;
            }

            /**
             * Sets the value of the totalPrice property.
             */
            public void setTotalPrice(int value) {
                this.totalPrice = value;
            }

            @Override
            public String toString() {
                return  "\n{Client: name='" + name +
                        "\n moviesCount=" + moviesCount +
                        "\n totalPrice=" + totalPrice + "}";
            }
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="director" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="genre" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
     *         &lt;element name="year">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
     *               &lt;minInclusive value="1888"/>
     *               &lt;maxInclusive value="2021"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="rating">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}float">
     *               &lt;minInclusive value="1.0"/>
     *               &lt;maxInclusive value="10.0"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="type">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *             &lt;enumeration value="dvd"/>
     *             &lt;enumeration value="blueRay"/>
     *             &lt;enumeration value="vhs"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "title",
            "director",
            "genre",
            "year",
            "rating"
    })
    public static class Movie {

        @XmlElement(required = true)
        protected String title;
        @XmlElement(required = true)
        protected List<VideoLibrary.Movie.Director> director;
        @XmlElement(required = true)
        protected List<String> genre;
        protected int year;
        protected float rating;
        @XmlAttribute(name = "type")
        protected String type;

        /**
         * Gets the value of the title property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getTitle() {
            return title;
        }

        /**
         * Sets the value of the title property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setTitle(String value) {
            this.title = value;
        }

        /**
         * Gets the value of the director property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the director property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDirector().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link VideoLibrary.Movie.Director }
         */
        public List<VideoLibrary.Movie.Director> getDirector() {
            if (director == null) {
                director = new ArrayList<VideoLibrary.Movie.Director>();
            }
            return this.director;
        }

        /**
         * Gets the value of the genre property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the genre property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getGenre().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         */
        public List<String> getGenre() {
            if (genre == null) {
                genre = new ArrayList<String>();
            }
            return this.genre;
        }

        /**
         * Gets the value of the year property.
         */
        public int getYear() {
            return year;
        }

        /**
         * Sets the value of the year property.
         */
        public void setYear(int value) {
            this.year = value;
        }

        /**
         * Gets the value of the rating property.
         */
        public float getRating() {
            return rating;
        }

        /**
         * Sets the value of the rating property.
         */
        public void setRating(float value) {
            this.rating = value;
        }

        /**
         * Gets the value of the type property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getType() {
            return type;
        }

        /**
         * Sets the value of the type property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setType(String value) {
            this.type = value;
        }

        public String toString() {
            return  "Title: \"" + title + '\"' +
                    "\nDirector(s): " + director +
                    "\nGenre(s): " + genre +
                    "\nYear: " + year +
                    "\nRating (KinoPoisk):" + rating +
                    "\nType: " + type + '\n';
        }

        /**
         * <p>Java class for anonymous complex type.
         *
         * <p>The following schema fragment specifies the expected content contained within this class.
         *
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "firstName",
                "lastName"
        })
        public static class Director {

            @XmlElement(required = true)
            protected String firstName;
            @XmlElement(required = true)
            protected String lastName;

            /**
             * Gets the value of the firstName property.
             *
             * @return possible object is
             * {@link String }
             */
            public String getFirstName() {
                return firstName;
            }

            /**
             * Sets the value of the firstName property.
             *
             * @param value allowed object is
             *              {@link String }
             */
            public void setFirstName(String value) {
                this.firstName = value;
            }

            /**
             * Gets the value of the lastName property.
             *
             * @return possible object is
             * {@link String }
             */
            public String getLastName() {
                return lastName;
            }

            /**
             * Sets the value of the lastName property.
             *
             * @param value allowed object is
             *              {@link String }
             */
            public void setLastName(String value) {
                this.lastName = value;
            }

            @Override
            public String toString() {
                return firstName + ' ' + lastName;
            }

        }

    }

}
