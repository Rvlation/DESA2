
package com.example.controller;
import com.example.model.Credentials;
import com.example.model.ValidationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.PropertySource;

@RestController
@RequestMapping("/api")
@PropertySource("classpath:application.properties")
public class ValidationController {

    @Autowired
    private Environment env;

    private String user1Name;
    private String user1Password;
    private String user2Name;
    private String user2Password;
    private String user3Name;
    private String user3Password;

    @Autowired
    public void setProperties() {
        user1Name = env.getProperty("app.user1.name");
        user1Password = env.getProperty("app.user1.password");
        user2Name = env.getProperty("app.user2.name");
        user2Password = env.getProperty("app.user2.password");
        user3Name = env.getProperty("app.user3.name");
        user3Password = env.getProperty("app.user3.password");
    }

    @PostMapping("/validate")
    public ValidationResponse validateUser(@RequestBody Credentials credentials) {
        ValidationResponse response = new ValidationResponse();

        String username = credentials.getUsername();
        String password = credentials.getPassword();

        if ((username.equals(user1Name) && password.equals(user1Password)) ||
            (username.equals(user2Name) && password.equals(user2Password)) ||
            (username.equals(user3Name) && password.equals(user3Password))) {
            response.setAuthorization(1);
            response.setMessage("Autorizado");
        } else {
            response.setAuthorization(0);
            response.setMessage("Usuario o contraseña incorrecto");
        }

        return response;
    }

    @PostMapping("/validate2")
    public ValidationResponse validateUser2(@RequestParam("username") String username, @RequestParam("password") String password) {
        ValidationResponse response = new ValidationResponse();

        if ((username.equals(user1Name) && password.equals(user1Password)) ||
            (username.equals(user2Name) && password.equals(user2Password)) ||
            (username.equals(user3Name) && password.equals(user3Password))) {
            response.setAuthorization(1);
            response.setMessage("Autorizado");
        } else {
            response.setAuthorization(0);
            response.setMessage("Usuario o contraseña incorrecto");
        }

        return response;
    }
}