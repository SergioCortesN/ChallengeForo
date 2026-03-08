Autenticación
A partir de ahora, solo los usuarios autenticados pueden interactuar con la API.

Implementa un mecanismo de autenticación en la API para que los usuarios puedan autenticarse y enviar solicitudes a ella.

→ Recuerda agregar la dependencia "Spring Security" en tu archivo pom.xml (si aún no lo has hecho en la etapa de configuración del entorno Java y Spring).

Configuración de seguridad
Para configurar la autenticación en tu proyecto, es necesario definir tu clase SecurityConfigurations con información para el acceso a través de solicitudes http, utilizando anotaciones como @Configuration y @EnableWebSecurity, así como la clase spring HttpSecurity.

Autenticación en el código Java
El proceso de autenticación en la API se realiza con la implementación de un controller responsable de recibir las solicitudes de inicio de sesión. Asegúrate de utilizar las anotaciones @RestController y @RequestMapping para definir la URL del controller.

Además, utilizamos una clase DTO (en el curso implementada como instancia Record en Java) para recibir los datos de inicio de sesión y contraseña, y luego autenticar al usuario en el método AuthenticationManager presente en la clase SecurityConfigurations.

→ Recuerda utilizar las anotaciones @PostMapping, @RequestBody y @Valid para recibir y validar los datos de la solicitud.

Base de datos
Para implementar el mecanismo de autenticación en la API, deberás modificar su estructura de base de datos, incluyendo una nueva tabla para almacenar los datos de autenticación de los usuarios.

En este caso, necesitarás crear o modificar la migración referente a los datos de los usuarios. Esta modificación es importante para gestionar los datos de inicio de sesión y contraseña de cada usuario.

Token JWT
Para agregar mayor seguridad a tu aplicación, una opción muy ventajosa es requerir tokens para autenticación. El JWT (JSON Web Token) es un estándar utilizado para compartir información entre cliente y servidor que será muy útil en esta tarea.

→ Para poder utilizar el JWT es necesario agregar su biblioteca en nuestro pom.xml como dependencia. Accede al sitio para obtener la biblioteca en Java de Auth0: JWT.IO

Esta biblioteca es importante precisamente para poder generar el token en el estándar JWT y así agregarlo en la configuración de seguridad de nuestro proyecto, creando una clase DTO UsernamePasswordAuthenticationToken para recibir el nombre de usuario y contraseña.

Generar y validar token
Además, es necesaria la construcción de una clase de servicio, TokenService, para aislar la generación y validación del token.

En la clase, se ha implementado el método "generarToken()", utilizando la biblioteca JWT para crear un token con el algoritmo HMAC256 y una contraseña. También se ha añadido la funcionalidad de configurar la fecha de expiración del token.

Dentro de esta clase, se debe implementar el método "generarToken()" que utiliza la biblioteca JWT para generar el token con el algoritmo HMAC256 y una contraseña secreta. Además, también es importante definir la fecha de expiración del token.

Por último, es necesario inyectar esta clase en tu controlador de autenticación, y así obtener el token retornado en la respuesta de la solicitud de inicio de sesión.

→ Recuerda que en esta etapa utilizamos atributos definidos en nuestro application.properties como jwt.secret y jwt.expiration.

Control de acceso
Después de la generación del token JWT, este debe ser utilizado para autenticar la gestión de registros de los tópicos, incluyendo acciones como creación, consulta, actualización o eliminación. La API debe ser configurada para responder con el resultado de cada solicitud, siempre y cuando el token proporcionado sea válido.

Antes de configurar más detalles del control de acceso, es válido agregar una nueva solicitud con una URL y un archivo JSON que contenga el nombre de usuario y contraseña para la generación del token. ¿Qué te parece si la URL es "http://localhost:8080/login"?

→ Destacamos la importancia de almacenar y enviar el token junto con las próximas solicitudes.

En principio, debemos mapear las URLs y validar los tokens en nuestro controlador. Recomendamos crear una clase separada para validar los tokens y llamarla antes de las solicitudes en el controlador para evitar la repetición de código.

→ Reforzamos la necesidad de crear un filter o interceptor en el proyecto para validar el token en cada solicitud.

Ahora, manos a la obra y continúa con esta etapa crucial del proceso de autenticación de nuestro FórumHub. No olvides manejar los errores y los estados de las solicitudes de la API utilizando las excepciones de nuestro querido Java.

