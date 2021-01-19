package com.diplom.model;

import com.diplom.Activity;
import com.diplom.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@Table(name="customer")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double weight;
    private double height;
    private int age;
    private Activity activity;
    private String password;
    private String login;


    //@ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    //@CollectionTable(name="customer_role", joinColumns = @JoinColumn(name="customer_id"))
    //@Enumerated(EnumType.STRING)
    //private Set<Role> roles;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "daily_menu_id")
    private DailyMenu dailyMenu;

    @OneToMany(mappedBy = "customer")
    private List<Post>posts;

    @OneToMany(mappedBy = "customer")
    private List <Photo> photos;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
