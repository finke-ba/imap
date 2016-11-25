package com.imap.controllers;

import com.imap.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

/**
 * Контроллер для аутентификации.
 *
 * @author Boris Finkelshtein <finke.ba@gmail.com>
 */
@RestController
@RequestMapping ("/imap/auth")
public class LoginController {

  private Logger log = LoggerFactory.getLogger (LoginController.class);

  /**
   * Метод аутентификации/получения данных пользователя. GET
   * запрос должен содержать заголовок аутентификации, с данными пользователя
   * (логин и пароль). Если заголовок отсутствует или переданны неверные
   * данные, то метод вернет HTTP статус 403.
   *
   * @param user Данные пользователя, автоматически предоставляются Spring
   *             Security, если в заголовке GET запроса были предоставлены
   *             корректные данные пользователя.
   * @return Данные пользователя или null.
   */
  @RequestMapping (value = "/user", method = RequestMethod.GET)
  public User getUser (Principal user) {

    if (user != null) {

      log.info ("Got user info for login = " + user.getName ());

      if (user instanceof AbstractAuthenticationToken) {
        return (User) ((AbstractAuthenticationToken)user).getPrincipal ();
      }
    }

    return null;

  }

  /**
   * Метод для выхода из приложения и сброса параметров аутентификации.
   *
   * @param rq Запрос
   * @param rs Ответ
   */
  @RequestMapping (value = "/logout", method = RequestMethod.POST)
  public void logout (HttpServletRequest rq, HttpServletResponse rs) {

    SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler ();
    securityContextLogoutHandler.logout (rq, rs, null);

  }

}
