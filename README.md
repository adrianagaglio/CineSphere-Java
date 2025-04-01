# Catalogo film backend (Demo)

Progetto realizzato con Java 21.

## Descrizione 

Catalogo film. 

Permette:
- operazioni CRUD
- ricerca film per titolo
- ricerca film correlati per genere e autore
- registrazione nuovi utenti e login
- aggiunta e rimozione preferiti
- ratings dei film
- consultazione lista utenti registrati con relativi film preferiti

Tecnologie utilizzate:
- Java (interazione db con JPA, controller protetti con Spring Security, controlli di validazione)
- JWT per sistema di autenticazione

Dati:
- Rest API con salvataggio dati su db PostgreSQL su neon.tech

## Eseguire l'applicazione con Docker (consigliato)

- Se non hai Docker installato, segui le istruzioni dal sito ufficiale: [Docker](https://docs.docker.com/desktop/)
- Esegui `docker build . -t cinesphere-be-app` per creare l'immagine dell'applicazione con tutte le dipendenze necessarie al suo funzionamento
- Esegui `docker compose up -d` per creare ed eseguire il container per avviare l'applicazione con i parametri specificati nel file docker-compose.yml \

  oppure
- Esegui `docker run -d -p 8080:8080 --env-file .env cinesphere-be-app`
- Naviga `http://localhost:8080/swagger-ui/index.html` per consultare la documentazione degli endpoint e testarli

  ## ENV FILE => NECESSARIO PER L'AVVIO DELL'APPLICAZIONE
  Creare file .env dentro la root del progetto e definire le seguenti variabili d'ambiente: \
    
  `DB_NAME=[nome db]` (crea un nuovo db locale o remoto ed inserisci i dati richiesti \
  ATTENZIONE!!! la variabile DB_NAME deve includere anche l'host: es. localhost\nome_db) \
  `DB_USER=[utente db]` \
  `DB_PASSWORD=[password db]` \
  `JWT_SECRET=[segreto jwt]` (è possibile ottenerne una qui [JwtSecret.com](https://jwtsecret.com/generate)) \
  `CLOUD_NAME=dgevh7ksg` (creare un account gratuito su [cloudinary.com](https://cloudinary.com/) ed inserire i dati richiesti ) \
  `CLOUD_API_KEY=745387371774771` \
  `CLOUD_API_SECRET=fcAbCakLGdV1i60mX4WN7HJ5YnI`

  A questo punto è possibile avviare l'applicazione seguendo le indicazioni sopra.

## Eseguire l'applicazione sulla tua macchina locale (sconsigliato)
- Assicurati di eseguire il progetto con la versione 21 di Java
- Imposta le variabili d'ambiente (specificate sopra) all'interno del tuo IDE (es. IntelliJ) o sostiiscile dentro il file src/main/resources/application.properties
- Avvia l'applicazione
- Naviga `http://localhost:8080/swagger-ui/index.html` per consultare la documentazione degli endpoint e testarli
- L'applicazione si aggiornarà automaticamente ad ogni modifica del file sorgente.

## Link al frontend
- [CineSphere Frontend Web App](https://ng-cinesphere.netlify.app/)
- [CineSphere Frontend GitHub](https://github.com/adrianagaglio/CineSphere-Angular)
