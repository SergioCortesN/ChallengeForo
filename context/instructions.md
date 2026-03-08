##Registro de un nuevo tópico
La API debe contar con un endpoint (punto final) para el registro de tópicos, y debe aceptar solicitudes del tipo POST para la URI /tópicos.

Los datos del tópico (título, mensaje, autor y curso) deben ser enviados en el cuerpo de la solicitud, en formato JSON.

→ No olvides utilizar la anotación @RequestBody para que tu proyecto Spring reciba correctamente los datos del cuerpo de la solicitud.

→ Además, recuerda que el tópico debe ser guardado en la base de datos construida para el proyecto, así que aquí tienes el recordatorio de utilizar el método save del JpaRepository para realizar la persistencia de los datos del tópico creado.

Sugerencia: para ayudar en la validación de los datos, intenta utilizar la anotación Java integrada en Spring @Valid.

Reglas de negocio
Todos los campos son obligatorios, por lo tanto, es necesario verificar si todos los campos se están ingresando correctamente.

La API no debe permitir el registro de tópicos duplicados (con el mismo título y mensaje).

Listado de tópicos
La API debe contar con un punto final para el listado de todos los tópicos, y debe aceptar solicitudes del tipo GET para la URI /tópicos.

Los datos de los tópicos (título, mensaje, fecha de creación, estado, autor y curso) deben ser devueltos en el cuerpo de la respuesta, en formato JSON.

→ Recordando que al tratar con el CRUD es necesario trabajar con JpaRepository asociado al tópico, especialmente en la lista de datos de la base de datos utilizamos el método findAll.

Opcionales:

¿Qué hay de mostrar los primeros 10 resultados ordenados por fecha de creación del tópico en orden ASC?

¿Qué tal mostrar los resultados usando un criterio de búsqueda? Sugerimos listar por: nombre de curso y año específico.

Otra opción opcional: ¿Qué tal practicar el listado de resultados con paginación? Utilizando la anotación @PageableDefault en tu código para manejar el volumen de datos presentados al usuario.

Detalle de tópicos
La API debe contar con un endpoint (punto final) para el listado de todos los tópicos, y debe aceptar solicitudes del tipo GET para la URI /tópicos/{id}.

Los datos de los tópicos (título, mensaje, fecha de creación, estado, autor y curso) deben ser devueltos en el cuerpo de la respuesta, en formato JSON.

→ Recuerda utilizar la anotación @‌PathVariable en tu código para recibir el campo de ID de la solicitud GET.

Reglas de negocio
Solicitar el campo ID para realizar la consulta es una acción obligatoria, ya que tu usuario necesita poder visualizar los detalles de un tópico solicitando una consulta a los datos en la base de datos. En este caso, es necesario verificar si el campo ID se ingresó correctamente.

Actualización de tópico
La API debe contar con un endpoint (punto final) para la actualización de los datos de un determinado tópico, y debe aceptar solicitudes del tipo PUT para la URI /tópicos/{id}.

Observación: las mismas reglas de negocio del registro de un tópico deben aplicarse también en su actualización.

Dado que estamos realizando una consulta en la base de datos para luego actualizar un tópico, es necesario solicitar y verificar el campo ID de su solicitud.

En el código del proyecto, sugerimos, al igual que en la tarjeta de Detalle de Tópicos, el uso de la anotación @PathVariable para obtener el ID de la solicitud PUT.

→ Recuerda verificar si el tópico existe en la base de datos para realizar su actualización. En este caso, sugerimos utilizar el método isPresent() de la clase Java llamada Optional.

Eliminación de tópico
La API debe contar con un endpoint para la eliminación de un tópico específico, el cual debe aceptar solicitudes del tipo DELETE para la URI /tópicos/{id}.

Dado que estamos realizando una consulta en la base de datos para luego actualizar un tópico, es necesario solicitar y verificar el campo ID de su solicitud.

En el código del proyecto, sugerimos, al igual que en la tarjeta de Detalle de Tópicos, el uso de la anotación @PathVariable para obtener el ID de la solicitud PUT.

→ Recuerda verificar si el tópico existe en la base de datos antes de realizar su actualización. En este caso, sugerimos el uso del método isPresent() de la clase Java llamada Optional.