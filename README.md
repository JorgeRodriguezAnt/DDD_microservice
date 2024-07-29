# Generador de microservicios
Esta API fue desarrollada en Spring Boot, enfocada en el patron Domain-Driven Design.
En la imagen se observa el uso de dicha API, en donde se debe ingresar un modelo de dominio del sistema que se debe encontrar en un archivo JSON.
La API se encarga de generar las clases necesarias para el correcto funcionamiento del sistema y el código se aloja en un repositorio.
Un beneficio del uso de esta API que el usuario solo debe enfocarse en ingresar la capa de dominio del sistema para la generación del microservicio y en su uso sólo se debe agregar las credenciales de la bases de datos(MySQL) 
para su uso.

El microservicio generado presenta un CRUD basico en donde el usuario podra realizar las funciones esenciales, además de poder agregar nuevos servicios.

Se utilizo Spring boot para crear esta API y MySQL para guardar todos los modelos que son ingresados por el usuario.


![Understanding the data](https://github.com/JorgeRodriguezAnt/DDD_microservice/blob/develop/ddd.png)

