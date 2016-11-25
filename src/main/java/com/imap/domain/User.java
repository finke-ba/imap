package com.imap.domain;

import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Объект, содержащий информацию о пользователе.
 *
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@JsonIgnoreProperties ({"authorities"})
public class User implements UserDetails {

  private static final long serialVersionUID = 6980833944761230840L;
  private boolean                      enabled;
  private boolean                      credentialsNonExpired;
  private boolean                      accountNonLocked;
  private boolean                      accountNonExpired;
  private String                       username;
  private String                       password;
  private List<SimpleGrantedAuthority> authorities;
  private String                       country;
  private String                       fullName;

  public User(String username, String password, List<SimpleGrantedAuthority> authorities) {
    this(true, true, true, true, username, password, authorities, "", "");
  }

  public User(boolean enabled, boolean credentialsNonExpired, boolean accountNonLocked, boolean accountNonExpired,
              String username, String password, List<SimpleGrantedAuthority> authorities, String country,
              String fullName) {
    super();
    this.enabled = enabled;
    this.credentialsNonExpired = credentialsNonExpired;
    this.accountNonLocked = accountNonLocked;
    this.accountNonExpired = accountNonExpired;
    this.username = username;
    this.password = password;
    this.authorities = authorities;
    this.country = country;
    this.fullName = fullName;
  }

  @Override
  public List<SimpleGrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return accountNonExpired;
  }

  @Override
  public boolean isAccountNonLocked() {
    return accountNonLocked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return credentialsNonExpired;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }

  public String getFullName() {
    return fullName;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public void setCredentialsNonExpired(boolean credentialsNonExpired) {
    this.credentialsNonExpired = credentialsNonExpired;
  }

  public void setAccountNonLocked(boolean accountNonLocked) {
    this.accountNonLocked = accountNonLocked;
  }

  public void setAccountNonExpired(boolean accountNonExpired) {
    this.accountNonExpired = accountNonExpired;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setAuthorities(List<SimpleGrantedAuthority> authorities) {
    this.authorities = authorities;
  }

}
