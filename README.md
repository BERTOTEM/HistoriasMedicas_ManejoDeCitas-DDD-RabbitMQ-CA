# Proyecto Base Implementando Clean Architecture

## Antes de Iniciar

Empezaremos por explicar los diferentes componentes del proyectos y partiremos de los componentes externos, continuando con los componentes core de negocio (dominio) y por último el inicio y configuración de la aplicación.

Lee el artículo [Clean Architecture — Aislando los detalles](https://medium.com/bancolombia-tech/clean-architecture-aislando-los-detalles-4f9530f35d7a)

# Arquitectura

![Clean Architecture](https://miro.medium.com/max/1400/1*ZdlHz8B0-qu9Y-QO3AXR_w.png)

## Domain

Es el módulo más interno de la arquitectura, pertenece a la capa del dominio y encapsula la lógica y reglas del negocio mediante modelos y entidades del dominio.

## Usecases

Este módulo gradle perteneciente a la capa del dominio, implementa los casos de uso del sistema, define lógica de aplicación y reacciona a las invocaciones desde el módulo de entry points, orquestando los flujos hacia el módulo de entities.

## Infrastructure

### Helpers

En el apartado de helpers tendremos utilidades generales para los Driven Adapters y Entry Points.

Estas utilidades no están arraigadas a objetos concretos, se realiza el uso de generics para modelar comportamientos
genéricos de los diferentes objetos de persistencia que puedan existir, este tipo de implementaciones se realizan
basadas en el patrón de diseño [Unit of Work y Repository](https://medium.com/@krzychukosobudzki/repository-design-pattern-bc490b256006)

Estas clases no puede existir solas y debe heredarse su compartimiento en los **Driven Adapters**

### Driven Adapters

Los driven adapter representan implementaciones externas a nuestro sistema, como lo son conexiones a servicios rest,
soap, bases de datos, lectura de archivos planos, y en concreto cualquier origen y fuente de datos con la que debamos
interactuar.

### Entry Points

Los entry points representan los puntos de entrada de la aplicación o el inicio de los flujos de negocio.

## Application

Este módulo es el más externo de la arquitectura, es el encargado de ensamblar los distintos módulos, resolver las dependencias y crear los beans de los casos de use (UseCases) de forma automática, inyectando en éstos instancias concretas de las dependencias declaradas. Además inicia la aplicación (es el único módulo del proyecto donde encontraremos la función “public static void main(String[] args)”.

**Los beans de los casos de uso se disponibilizan automaticamente gracias a un '@ComponentScan' ubicado en esta capa.**

# Funcionalidades
### El doctor Ramiro Fernandez nos contactó porque quiere adquirir un sistema para la administración de las historias médicas y el manejo de citas de sus pacientes. 
### Por ello nosotros tenemos la tarea de realizar un sistema:


- Que le permita al doctor establecer un horario de disponibilidad por día de la semana. ej (lunes 8am a 5pm, martes 9am a 3 pm ... ) 
- Que le permita a el doctor agregar un paciente nuevo 
- Que le permita al doctor modificar la información personal del paciente de ser necesario 
- Agregar revisión de cita médica.
- Que le permita a los pacientes que están inscritos  agendar citas pero sólo en los horarios disponibles, es decir deben ir saliendo de disponibilidad los horarios - que ya han sido tomados.(Hay que definir también cuanto toma cada cita como un estándar para saber qué franja horaria se va ocupando a medida que se van agendando - citas)
- Que le permita al doctor cancelar las citas que se van agendando.(Si el doctor cancela la cita el horario debe volver a pasar a estar disponible)
- Que le permita al doctor conocer el estado de su agenda de la semana actual.
- Que le permita al doctor listar todo el historial médico de un  paciente con todas las interacciones que han tenido.
- Por último que le permita eliminar paciente


# Modelamiento Estrategico y Tactico
https://app.diagrams.net/#G1Bk_sBeR1xxSRKNWdqDkcxZ6x1u0i56Oh

# Consumo de API
### Crear Un Paciente

![image](https://user-images.githubusercontent.com/82735837/224729619-7573b764-7c72-4e02-aead-4351639b94bb.png)

### ActualizarPaciente

![image](https://user-images.githubusercontent.com/82735837/225964082-e682d47b-f623-4387-93ab-3e303a1cc37e.png)

### Crear una agenda

![image](https://user-images.githubusercontent.com/82735837/224730034-96bde2e2-53d2-4fdb-acf7-73a6b540c0b7.png)

### Crear disponibilidad Horaria de un dia

![image](https://user-images.githubusercontent.com/82735837/224730352-79b48e51-daca-4fcc-9133-b9dc26c578f4.png)

### AgregarCita

![image](https://user-images.githubusercontent.com/82735837/225963758-73985c7f-ad92-4b41-83c1-eef3235c0644.png)

### CancelarCita

![image](https://user-images.githubusercontent.com/82735837/225964840-ccaeb45a-efcb-49d8-981f-516b540427aa.png)

### HistorialPaciente

![image](https://user-images.githubusercontent.com/82735837/224733862-72389a21-e137-4996-91f6-e79a6ed1f1c8.png)

### ListarAgenda

![image](https://user-images.githubusercontent.com/82735837/225964612-dd5a3267-c6ee-4c85-b3ee-e7e0f270e862.png)

### EliminarPaciente

![image](https://user-images.githubusercontent.com/82735837/225965017-ec94c896-679d-47b6-8af1-978c39174d0c.png)

# Api consume la cola de rabbit y envia correos

https://github.com/BERTOTEM/ConsumoRabbitEnvioCorreo.git

## Autor y Versión
- Julian Roberto Mazo Arroyave
- 1.0.0



