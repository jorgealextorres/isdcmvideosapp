
package isdcm.video.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for searchByYear complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="searchByYear"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="searchYear" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "searchByYear", propOrder = {
    "searchYear"
})
public class SearchByYear {

    protected int searchYear;

    /**
     * Gets the value of the searchYear property.
     * 
     */
    public int getSearchYear() {
        return searchYear;
    }

    /**
     * Sets the value of the searchYear property.
     * 
     */
    public void setSearchYear(int value) {
        this.searchYear = value;
    }

}
