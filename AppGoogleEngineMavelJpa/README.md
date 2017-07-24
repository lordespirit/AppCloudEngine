App Google Engine - Java + Mavel + Jpa
=========================

Test de Deploy a Google Cloud SQL con base de datos de la prueba final de tercera evaluación.
Se usa el proyecto 'gallery'.

Encontramos los siguientes objetos (modelos jpa)

* Admin
* Gallery
* Item

La aplicación nos permite gestionar toda la base de datos, con las 'queries' más típicas como:

* SELECT
* UPDATE
* DELETE
* UPDATE

El proyecto utiliza 'Servlets' de tipo 'doGet' y 'doPost' para generar las páginas dinámicas.

## Requirements

Si se quiere correr en local hay que modificar DBManager y cambiar persistencia y añadir 'Local' al final del mismo nombre.
Si se hace hay que crear la base de datos en local con el nombre adecuado, y modificar el fichero para poner nuestro usuario y contraseña.

## Maven

El proyecto es en Maven, por lo que las dependencias seran bajadas automáticamente al bajar el proyecto.

## Maven

Link al deploy, tenemos un fichero que nos indica la dirección web al deploy en Google Cloud. Por si acaso, el link también está indicado aquí:

https://app-cloud-eduard.appspot.com/index.html