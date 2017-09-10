package ma.octo.agritech.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ma.octo.agritech.requests.StoreUserRequest;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    @JsonIgnore
    private String password;

    private String phone;

    private String address;

    private String city;

    private String country;

    private String function;

    private String society;

    private String image;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "USERS_ROLES",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;


    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;


    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Production> productions;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Negociation> negociations = new ArrayList<>();

    public User() {
    }

    public User(String username, String firstName, String lastName, String email, String password, String phone,
                String address, String city, String country, String function, String society, String image) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.setPassword(password);
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.country = country;
        this.function = function;
        this.society = society;
        this.image = image;
    }

    public User(String username, String firstName, String lastName, String email, String password, String phone,
                String address, String city, String country, String function, String society, String image, List<Role> roles) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.setPassword(password);
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.country = country;
        this.function = function;
        this.society = society;
        this.roles = roles;
        this.image = image;
    }

    public User(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.phone = user.getPhone();
        this.address = user.getAddress();
        this.city = user.getCity();
        this.country = user.getCountry();
        this.function = user.getFunction();
        this.roles = user.getRoles();
        this.society = user.getSociety();
        this.image = user.getImage();
    }

    public User(StoreUserRequest storeUserRequest) {
        this.username = storeUserRequest.getUsername();
        this.firstName = storeUserRequest.getFirstname();
        this.lastName = storeUserRequest.getLastname();
        this.email = storeUserRequest.getEmail();
        this.password = storeUserRequest.getPassword();
        this.phone = storeUserRequest.getPhone();
        this.address = storeUserRequest.getAdress();
        this.city = storeUserRequest.getCity();
        this.function = storeUserRequest.getFunction();
        this.society = storeUserRequest.getSociete();
        this.image = storeUserRequest.getImage();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getSociety() {
        return society;
    }

    public void setSociety(String society) {
        this.society = society;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Production> getProductions() {
        return productions;
    }

    public void setProductions(List<Production> productions) {
        this.productions = productions;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Negociation> getNegociations() {
        return negociations;
    }

    public void setNegociations(List<Negociation> negociations) {
        this.negociations = negociations;
    }

    public boolean hasRole(String roleRef) {
        for (Role role:this.roles) {
            if(role.getRef().equals(roleRef)){
                return true;
            }

        }
        return false;
    }
    public boolean hasRole(Role role) {

        return this.hasRole(role.getRef());
    }
    public boolean hasRole(Long roleId) {

        for (Role role:this.roles) {
            if(role.getId()==(roleId)){
                return true;
            }

        }
        return false;
    }
}
