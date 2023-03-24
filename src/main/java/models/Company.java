
package models;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Company {

    public String name;
    public String catchPhrase;
    public String bs;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Company() {
    }

    /**
     * 
     * @param bs
     * @param catchPhrase
     * @param name
     */
    public Company(String name, String catchPhrase, String bs) {
        super();
        this.name = name;
        this.catchPhrase = catchPhrase;
        this.bs = bs;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("catchPhrase");
        sb.append('=');
        sb.append(((this.catchPhrase == null)?"<null>":this.catchPhrase));
        sb.append(',');
        sb.append("bs");
        sb.append('=');
        sb.append(((this.bs == null)?"<null>":this.bs));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
}
