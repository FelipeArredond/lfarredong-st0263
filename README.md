# info de la materia: ST0263-241 Topicos especiales en telematica
#
# Estudiante(s): Luis Felipe Arredondo Giraldo, lfarredong@eafit.edu.co
#
# Profesor: nombre, email-eafit
#
# Reto 1 y 2
#
# 1. breve descripción de la actividad

Se desarrollo una red P2P no estructurada la cual cada uno de los peers participantes consultan con los demas peers de la red por archivos para descargar o cargar y en caso tal de que este no lo contenga, se consulta con un servidor central en cual de los peers se encuentra el archivo solicitado y se lo consulta a este

## 1.2. Que aspectos NO cumplió o desarrolló de la actividad propuesta por el profesor (requerimientos funcionales y no funcionales)
Todos los aspectos planteados  (REST, GRPC, login, logout, search, index, docker) se implementaron en este proyecto de manera correcta, por lo tanto nada fue incumplido
# 2. información general de diseño de alto nivel, arquitectura, patrones, mejores prácticas utilizadas.
Se implemento una arquitectura por capas en cada uno de los peers, se utilizan los patrones de desarrollo Singleton y Dependency Inversion Principle para lograr obtener un codigo escalable y facil de leer. Se hizo uso de herencia, clases abstractas usando el paradigma POO

# 3. Descripción del ambiente de desarrollo y técnico: lenguaje de programación, librerias, paquetes, etc, con sus numeros de versiones.
El ambiente de este proyecto al estar dockerizado los peers y el server permiten el uso de diferentes herramientas como docker compose, docker swarm o kubernetes. En mi caso tengo un cluster de kubernetes local usando minikube y definiendo el despliegue a traves de manifiestos k8s lo cual nos permite un despliegue facil y rapido en ambientes cloud.

## Como se compila y ejecuta.
- Se inicializa el cluster local con minikube usando el comando ' minikube start '
- Se ejecutan los archivos k8s dentro de la carpeta de deployment usando el comando ' kubectl apply -f grpc-server.yaml ' y asi para cada uno de los k8s definidos
- Ejecutando el comando ' minikube service service-name --url ' obtendremos el puerto a traves del cual consumiremos el servicio que deseamos usar, en este caso seria acceder a un peer para descargar y cargar archivos en la red P2P
## Detalles del desarrollo.
- Se realiza despliegue local y en ambientes usando kubernetes
- Se hizo uso de programacion reactiva en la imagen base del peer para el manejo de la concurrencia
## Detalles técnicos
- La comunicacion entre el usuario y los peers es a traves de REST API
- La comunicacion entre peers entre si y el servidor es a traves de GRPC para la velocidad y la concurrencia
## Descripción y como se configura los parámetros del proyecto (ej: ip, puertos, conexión a bases de datos, variables de ambiente, parámetros, etc)
- En el archivo grpc-client.yaml se realiza la configuracion de cuantos peers deseo en mi red y definir una conexion inicial entre estos, es decir definir que el peer1 por defecto se comunique con el peer2 y si no encuentra el archivo le consulta al servidor la ubicacion de este para que el peer1 realice la consulta
## Estructura de carpetas
- Al ser arquitectura por capas tenemos los archivos que persisten el la cache del peer en la carpeta de entities, la logica de negocio en el service y los controladores en la carpeta controllers

# 4. Descripción del ambiente de EJECUCIÓN (en producción) lenguaje de programación, librerias, paquetes, etc, con sus numeros de versiones.
- Para esta entrega no se establecieron credenciales para un despliegue a produccion, pero de ser asi bastaria con crear un EKS en AWS, traer las credenciales del cluster y ejecutar kubectl apply a los archivos server y client para tener nuestra red P2P en produccion, muy facilmente ya que todas las imagenes base de los pods a ejecutarse se encuentran publicadas en docker hub.

## Una mini guia de como un usuario utilizaría el software o la aplicación
- Identificar el puerto del peer que queremos usar
- Realizar peticiones http a este basados en los endpoint expuestos para la interaccion de dicho peer en la red

# 5. otra información que considere relevante para esta actividad.

- Link de docker hub de la imagen del servido. [Ir a repositorio del servidor](https://hub.docker.com/repository/docker/felipearredond/telematica/general)
- Link de docker hub de la imagen del peer. [Ir a repositiorio del peer](https://hub.docker.com/repository/docker/felipearredond/telematica_client/general)