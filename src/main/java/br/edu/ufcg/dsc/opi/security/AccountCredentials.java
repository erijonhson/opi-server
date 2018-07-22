package br.edu.ufcg.dsc.opi.security;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Credentials of the Account.
 */
@ApiModel(value = "AccountCredentials")
public class AccountCredentials {

	@ApiModelProperty(example = "admin@admin.com", required = true)
	@NotEmpty
    private String username;

	@ApiModelProperty(example = "abcde", required = true)
	@NotEmpty
    private String password;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
