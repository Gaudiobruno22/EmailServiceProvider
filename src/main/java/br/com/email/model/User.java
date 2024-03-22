package br.com.email.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.TypeResolutionStrategy.Lazy;


@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@EqualsAndHashCode
public class User implements Serializable, UserDetails{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "user_name", columnDefinition = "varchar(255)", unique = true)
	private String userName;
	
	@Column(name = "full_name", columnDefinition = "varchar(255)")
	private String fullName;
	
	@Column(name = "password", columnDefinition = "varchar(255)")
	private String password;
	
    @Column(name = "account_non_expired", columnDefinition = "bit(1)")
    private Boolean accountNonExpired;
    
    @Column(name = "account_non_locked", columnDefinition = "bit(1)")
    private Boolean accountNonLocked;
    
    @Column(name = "credentials_non_expired", columnDefinition = "bit(1)")
    private Boolean credentialsNonExpired;
    
    @Column(name = "enabled", columnDefinition = "bit(1)")
    private Boolean enabled;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_permission",
    		   joinColumns = @JoinColumn(name = "id_user"),
    		   inverseJoinColumns = @JoinColumn(name = "id_permission"))
    private List<Permission> permissions;
    
	public List<String> getRoles(){
    	List<String> roles = new ArrayList<>();
    	for(Permission permission : permissions) {
    		roles.add(permission.getDescription());
    	}
		return roles;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.permissions;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}
}
