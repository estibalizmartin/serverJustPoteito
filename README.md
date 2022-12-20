# serverJustPoteito
El proyecto se compone de dos aplicaciones clientes y un servidor. Las primeras son una app móvil Android y una aplicación web desarrollada con Blazor. El servidor, ubicado en este repositorio, es una API REST que se comunica a través de solicitudes HTTP para ejecutar funciones CRUD estándar.

Por medio de estos enlaces es posible acceder a los repositorios de las respectivas aplicaciones cliente:
- Aplicación móvil: [repo Android](https://github.com/estibalizmartin/androidJustPoteito.git) 
- Aplicación web: [repo Blazor](https://github.com/Txabo/JustPoteitoBlazor.git)

## Project description
![erdiagram](https://user-images.githubusercontent.com/78641797/208735750-88cc9392-cf6b-4a94-a865-d2c774dbe96e.png)

- El administrador podrá: 
  - Listar usuarios
  - Crear usuarios
  - Eliminar usuarios
  - Modificar usuarios
  - Asignar y modificar permisos de usuarios
 
- El usuario podrá:
  - Registrarse
  - Iniciar sesión
  - Recuperar su contraseña
  - Cambiar su contraseña
  - Listar platos
  - Escribir comentarios

## Built with 
- Spring Boot 3.0.0
- Hibernate
- MySQL

## Getting started 
Para obtener una copia local en funcionamiento, siga estos sencillos pasos.

### Prerequisites
1. Es necesario tener instalados Eclipse o IntelliJ, MySQL y Postman para probar las diferentes peticiones.
2. Crear la base de datos Poteito.
3. Generar las tablas, sus relaciones y los inserts de cada una de ellas importando el script .sql incluido en la carpeta src/main/resources/db.

### Installation
1. Clonar el repositorio.


        git clone https://github.com/estibalizmartin/serverJustPoteito.git
2. Modificar en application.properties las siguientes propiedades:
    - spring.datasource.url: cambiar el puerto de la base de datos en caso de que esté ocupado
    - spring.datasource.username: introducir el usuario correspondiente de la base de datos
    - spring.datasource.password: introducir la contraseña correspondiente de dicho usuario

### Usage
Para arrancar el proyecto ejecutar (Run) la clase ServerJustPoteitoApplication ubicada en src/main/java/com/example/serverJustPoteito.

Un ejemplo de petición es el siguiente:


        GET http://localhost:8080/api/dishes

Asimismo, el proyecto cuenta con una colección Postman en formato .json que incluye todas las peticiones necesarias para acceder a cada endpoint del servidor. Para acceder a este archivo será necesario seguir la ruta src/main/resources/json.

### Testing 

## API documentation

## Contact
- Txaber Olabe (txaber.olabesa@elorrieta-errekamari.com)
- David Pereiro (david.pereiroam@elorrieta-errekamari.com)
- Fabiana Silva (fabiana.silva@elorrieta-errekamari.com)
- Estíbaliz Martín (estibalizmares@gmail.com)

## License
Distributed under the MIT License.
