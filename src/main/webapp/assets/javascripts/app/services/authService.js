angular.module("imap").service("authService", ['$http', '$location', '$cookies', function ($http, $location, $cookies) {

  /**
   * @desc Аутентифицировать пользователя. Если пользователь вошел в систему - будет вызвана функция
   *       callback с аргументом true, иначе будет вызвана функция callback с аргументом false.
   *
   * @param name Логин
   * @param password Пароль
   * @param remember true если нужно запомнить пользователя, иначе false
   * @param callback Функция
   *
   */
  this.authenticate = function (name, password, remember, callback) {

    if (!name || !password) {
      callback (false);
      return;
    }

    var headers = {
      authorization: "Basic " + btoa (name + ":" + password)
    };

    $http.get ('/imap/auth/user', {'headers': headers}).then (
        function successCallback(response) {
          if (response) {
            if (remember) {
              createRememberMeCookie (response.data);
            }
            callback (true);
          } else {
            callback (false);
          }

        },
        function errorCallback() {
          callback (false);
        });
  };

  /**
   * @desc Получить информацию о текущем пользователе. Если пользователь вошел в систему - будет вызвана функция
   *       callback с данными пользователя, иначе будет функция callback будет вызывана с аргументом null.
   *
   * @param callback Функция
   *
   */
  this.getUserInfo = function(callback) {
    $http.get ('/imap/auth/user').then (
        function successCallback(response) {
          if (response.data.username) {
            callback(data);
          } else {
            callback(null);
          }
        },
        function errorCallback() {
          callback (null);
        });
  };

  /**
   * @desc Выйти из системы.
   */
  this.logout = function() {
    removeRememberMeCookie();
    return $http.post('imap/auth/logout', {});
  };

  /**
   * @desc Создать cookie для аутентификации пользователя без ввода имени и пароля. Функция вызывается в том случае, если
   *       пользователь установил опцию "Запомнить меня". Вызывается сразу после успешного входа в функции authenticate.
   *
   * @param userDetails Данные пользователя после успешной
   *                    аутентификации, которые вернул сервер.
   *
   */
  var createRememberMeCookie = function(userDetails) {

    var name = userDetails.username;
    var pwd = userDetails.password;
    var expireDate = new Date();
    expireDate.setDate(expireDate.getDate() + 30);

    var cookieValue = btoa(name + ":" + expireDate.getTime().toString() + ":"
                           + md5(name + ":" + expireDate.getTime().toString() + ":" + pwd + ":" + "IMAP_REMEMBER_TOKEN")
    );

    $cookies.put('IMAP_REMEMBER_ME_COOKIE',
                 cookieValue, {
                   'expires' : expireDate,
                   'path' : '/'
                 });
  };

  /**
   * @desc Удалить "remember me" cookie. Сбрасывает дату действия для cookie IMAP_REMEMBER_ME_COOKIE на текущую - 1 день*
   */
  var removeRememberMeCookie = function() {
    var expireDate = new Date();
    expireDate.setDate(expireDate.getDate() - 1);
    $cookies.put('IMAP_REMEMBER_ME_COOKIE',
                 '', {
                   'expires' : expireDate,
                   'path' : '/'
                 });
  };

}]);