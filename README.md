# LiterAlura
Challenge Java - Spring Boot que forma parte de la Formación **Java y Spring Boot G6 - ONE** del programa 
[Oracle Next Education (ONE)](https://www.oracle.com/mx/education/oracle-next-education/), el cual surge de la 
colaboración de Oracle con Alura Latam.

## ¿Qué es LiterAlura?
Es una aplicación en consola para realizar búsquedas de libros en la API de [Gutendex](https://gutendex.com). 
Los libros buscados son almacenados en una base de datos PostgreSQL para hacer más eficiente su búsqueda en 
futuras ocaciones.

## Requisitos
Puede usar el proyecto clonando el repositorio en su computadora. Para la correcta ejecución del proyecto es necesario
contar con los siguientes requerimientos.
- Java 17 o superior.
- PostgreSQL 16.1 o superior.

## Variables de entorno
El programa hace uso de variables de entorno. La configuración de estas se puede realizar manualmente a nivel de
sistema operativo o desde un Entorno de Desarrollo Integrado (IDE).

A continuación, se muestran enlaces de referencia para saber *¿Cómo añadir variables de entorno?* en el caso de usar 
el IDE IntelliJ IDEA.

- https://www.jetbrains.com/help/objc/add-environment-variables-and-program-arguments.html#add-environment-variables
- https://www.baeldung.com/intellij-idea-environment-variables

Las variables de entorno a agregar son las siguientes.

| Variable             | Valor                                       |
|----------------------|---------------------------------------------|
| GUTENDEX_DB_HOST     | *IP de la Base de Datos (ej. localhost)*    |
| GUTENDEX_DB_PORT     | *Puerto de la Base de Datos (ej. 5432)*     |
| GUTENDEX_DB_NAME     | *Nombre de la Base de Datos*                |
| GUTENDEX_DB_USER     | *Nombre de usuario de la Base de Datos*     |
| GUTENDEX_DB_PASSWORD | *Contraseña de usuario de la Base de Datos* |

## Opciones de la aplicación
LiterAlura cuenta con un menú de usuario en el cual se proveen las siguientes funcionalidades.
1. Buscar libro por título
2. Buscar libro por autor
3. Listar todos los libros buscados
4. Listar los autores de todos los libros buscados
5. Listar cuáles autores de los libros buscados estaban vivos en cierto año
6. Listar los libros buscados que están en cierto idioma
7. Obtener la cantidad de libros buscados que están en cierto idioma
8. Estadísticas de de descargas (por idioma) de los libros buscados
9. Top 10 de los libros buscados que tienen más descargas
10. Salir de la aplicación

Para las opciones relacionadas con *idiomas*, en cada una se cuenta con el siguiente submenú.
1. Inglés
2. Francés

En cada opción, tanto del menú como del submenú, se debe introducir su número correspondiente para seleccionarla.