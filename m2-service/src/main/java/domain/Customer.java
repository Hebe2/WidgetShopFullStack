package domain;

import net.sf.oval.constraint.Email;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.MatchPattern;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;



/**
 * @author Mark George
 */
public class Customer {

    private Integer customerId;

    @NotNull(message = "username must be provided.")
    @NotBlank(message = "username must be provided.")
    @Length(min = 5, message = "username must contain at least five characters.")
    private String username;

    @NotNull(message = "first name must be provided.")
    @NotBlank(message = "first name must be provided.")
    @Length(min = 2, message = "username must contain at least two characters.")
    private String firstName;

    @NotNull(message = "last name must be provided.")
    @NotBlank(message = "last name must be provided.")
    @Length(min = 2, message = "last must contain at least two characters.")
    private String surname;

    @NotNull(message = "password must be provided.")
    @NotBlank(message = "password must be provided.")
    @Length(min = 2, message = "password must contain at least two characters.")
    @MatchPattern(
            pattern = "^(?=.*[A-Za-z])(?=.*\\d).+$",
            message = "password must contain both letters and numbers"
    )
    private String password;

    @NotNull(message = "email address must be provided.")
    @NotBlank(message = "email address must be provided.")
    @Email(message = "email address must be valid")
    private String emailAddress;

    @NotNull(message = "shipping address must be provided.")
    @NotBlank(message = " must be provided.")
    @Length(min = 2, message = "shipping address must contain at least two characters.")
    private String shippingAddress;

    public Customer(Integer customerId, String username, String firstName, String surname, String shippingAddress, String emailAddress) {
        this.customerId = customerId;
        this.username = username;
        this.firstName = firstName;
        this.surname = surname;
        this.shippingAddress = shippingAddress;
        this.emailAddress = emailAddress;
    }
    
     public Customer() {
        
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer personId) {
        this.customerId = personId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerId=" + customerId + ", username=" + username + ", firstName=" + firstName + ", surname=" + surname + ", password=" + password + ", emailAddress=" + emailAddress + ", shippingAddress=" + shippingAddress + '}';
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hashCode(this.username);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Customer other = (Customer) obj;
        return java.util.Objects.equals(this.username, other.username);
    }

}
