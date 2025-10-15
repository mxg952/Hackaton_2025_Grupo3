

## Hackaton 2025 · Grup 3

### Descripció del projecte

Aquest projecte correspon al **backend del Grup 3** desenvolupat per a la **Hackaton 2025**.
L’aplicació proporciona una **API RESTful** implementada amb **Java 21** i **Spring Boot 3**, que ofereix dades urbanes de Barcelona relatives a **barris, habitatges turístics (HUT), nivell de soroll, metro, i formularis de contacte / newsletter**.

El sistema combina:

* **Dades estàtiques** en fitxers JSON per a la informació urbana i turística.
* **Persistència real** mitjançant **base de dades H2** per a formularis i subscripcions de newsletter.

---

### ⚙️ Tecnologies utilitzades

| Tecnologia          | Descripció                                                 |
| ------------------- | ---------------------------------------------------------- |
| **Java 21**         | Llenguatge de programació principal                        |
| **Spring Boot 3.x** | Framework per crear serveis REST                           |
| **Maven 3.9+**      | Eina de compilació i gestió de dependències                |
| **H2 Database**     | Base de dades embeguda per guardar formularis i newsletter |
| **Spring Data JPA** | ORM per a la persistència sobre H2                         |
| **Jackson**         | Serialització i parseig de JSON                            |

---

### 🧩 Arquitectura del projecte

El backend segueix una **arquitectura en capes** clàssica i escalable:

```
Controller  →  Service  →  Repository / JSON Data
```

#### 🔸 Controller

Defineix els endpoints HTTP i gestiona les respostes de l’API.
Exemples: `NeighborhoodController`, `HutController`, `FormController`, `NoiseLevelController`, etc.

#### 🔸 Service

Conté la **lògica de negoci**, gestiona la lectura dels fitxers JSON o la interacció amb H2 segons el mòdul.

#### 🔸 Repository / Data

* Per als **formularis** i **newsletter**, s’utilitza **H2 Database** amb Spring Data JPA.
* Per a la resta d’informació, les dades es carreguen des de fitxers JSON dins `src/main/resources/`.

---

### 🧾 Endpoints disponibles

| Mètode | Endpoint                                             | Descripció                                                   |
| ------ | ---------------------------------------------------- | ------------------------------------------------------------ |
| `GET`  | `http://localhost:8080/api/formularios/media/barrio` | Retorna la mitjana de dades de formularis agrupada per barri |
| `GET`  | `http://localhost:8080/api/summary/full-data`        | Retorna el resum complet de dades urbanes                    |
| `GET`  | `http://localhost:8080/api/hut/full-data`            | Retorna totes les dades d’habitatges d’ús turístic (HUT)     |
| `GET`  | `http://localhost:8080/metro/hut/full-data`          | Retorna dades de metro relacionades amb HUT                  |
| `GET`  | `http://localhost:8080/api/neighborhoods/full-data`  | Retorna el fitxer complet de barris de Barcelona             |
| `GET`  | `http://localhost:8080/api/newsletter/full-data`     | Retorna totes les subscripcions a la newsletter desades a H2 |
| `GET`  | `http://localhost:8080/api/noise-level/full-data`    | Retorna les dades de nivells de soroll per barri             |

> Totes les respostes es retornen en format **JSON**.

---

### 💾 Base de dades H2

* La **H2 Database** s’utilitza per persistir els formularis i newsletters.
* Configuració per defecte a `application.properties`.

**Consola H2:**

```
http://localhost:8080/h2-console
```

**Credencials per defecte:**

```
URL: jdbc:h2:mem:testdb
Usuari: sa
Contrasenya: (en blanc)
```

Les entitats `Form` i `Newsletter` es creen automàticament mitjançant Spring Data JPA.

---

### Requisits previs

#### 1️Instal·lar **Java 21**

Comprova si ja el tens instal·lat:

```bash
java -version
```

Si no, descarrega’l des de:
👉 [https://www.oracle.com/java/technologies/javase/jdk21-downloads.html](https://www.oracle.com/java/technologies/javase/jdk21-downloads.html)

Configura la variable d’entorn:

```bash
setx JAVA_HOME "C:\Program Files\Java\jdk-21"
```

i afegeix `%JAVA_HOME%\bin` al PATH del sistema.

---

#### 2️⃣ Instal·lar **Apache Maven**

Comprova la instal·lació:

```bash
mvn -v
```

Si no el tens, baixa’l de:
👉 [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi)

Descomprimeix-lo, crea la variable:

```bash
setx M2_HOME "C:\Program Files\Apache\maven"
setx PATH "%PATH%;%M2_HOME%\bin"
```

---

### 🚀 Com executar el projecte

1. **Clona el repositori**

   ```bash
   git clone https://github.com/<usuari>/Hackaton_2025_Grupo3.git
   cd Hackaton_2025_Grupo3
   ```

2. **Compila i executa**

   ```bash
   mvn clean spring-boot:run
   ```

3. **Prova l’API**

   ```
   http://localhost:8080/api/summary/full-data
   ```

4. **Accedeix a la consola H2 (opcional)**

   ```
   http://localhost:8080/h2-console
   ```

---

### 🧩 Justificació tècnica del patró seguit

El projecte implementa una **arquitectura en capes** amb els següents objectius:

* **Separació de responsabilitats**: cada capa té un rol clar (controlador, servei, dades).
* **Escalabilitat**: els JSON poden substituir-se fàcilment per una base de dades real sense afectar el frontend.
* **Testabilitat**: facilita proves unitàries independents per capa.
* **Mantenibilitat**: codi modular, net i coherent.
* **Reutilització**: patró uniforme en tots els mòduls (barris, HUT, metro, soroll, formularis, newsletter).



