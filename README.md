# Laboratorio Bases de Datos

## Configuración
### 1. Conexión con la Base de Datos MySQL

Para crear un archivo `db.properties` en la carpeta `src/main/resources/` que contenga las credenciales de la conexión a la base de datos y la tabla con la cual trabajar, sigue estos pasos:

1. Abre el directorio del proyecto en tu IDE o editor de texto.
2. Navega hasta la carpeta `src/main/resources/`.
3. Crea un nuevo archivo llamado `db.properties`.
4. Abre el archivo `db.properties` y añade la siguiente información:

```properties
# Credenciales de la conexión a la base de datos
db.url=jdbc:mysql://localhost:3306/nombre_base_datos
db.username=nombre_usuario
db.password=contraseña
```

5. Reemplaza `nombre_base_datos`, `nombre_usuario` y `contraseña` con los valores correspondientes a tu base de datos.

Guarda los cambios en el archivo `db.properties` y ahora tendrás un archivo de configuración que contiene las credenciales de la conexión a la base de datos y la tabla con la cual trabajar.

Recuerda que es importante mantener la seguridad de las credenciales y no compartir este archivo con terceros.
