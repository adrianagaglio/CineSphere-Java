# Catalogo film backend (Demo)

Progetto realizzato con Java 21.

## Descrizione 

Catalogo film. 

Permette:
- operazioni CRUD
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
- Esegui `docker compose up -d` per creare ed eseguire il container per avviare l'applicazione con i parametri specificati nel file docker-compose.yml
- Naviga `http://localhost:8080/swagger-ui/index.html` per consultare la documentazione degli endpoint e testarli

  ## ENV FILE => NECESSARIO PER L'AVVIO DELL'APPLICAZIONE
  Creare file .env dentro la cartella del progetto e definire le seguenti variabili d'ambiente: \
    
  `DB_NAME=[nome db]` (crea un nuovo db locale o remoto ed inserisci i dati richiesti - ATTENZIONE!!! la variabile DB name deve includere anche l'host: es. localhost\nome_db) \
  `DB_USER=[utente db]` \
  `DB_PASSWORD=[password db]` \
  `JWT_SECRET=[chiave jwt ]` (è possibile ottenerne una qui [JwtSecret.com](https://jwtsecret.com/generate)) \
  `CLOUD_NAME=dgevh7ksg` (creare un account gratuito su [cloudinary.com](https://cloudinary.com/) ed inserire i dati richiesti ) \
  `CLOUD_API_KEY=745387371774771` \
  `CLOUD_API_SECRET=fcAbCakLGdV1i60mX4WN7HJ5YnI`

## Eseguire l'applicazione sulla tua macchina locale

- Esegui `npm i` per scaricare le dipendenze.
- Esegui `ng serve` per avviare il server di sviluppo.
- Naviga `http://localhost:4200/`.
- L'applicazione si aggiornarà automaticamente ad ogni modifica del file sorgente.


