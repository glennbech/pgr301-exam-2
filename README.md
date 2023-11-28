PGR301 - Candidate 2042
# Oppgave 1: Kjells python kode
[![SAM build and deploy python lambda function](https://github.com/jtj20/pgr301-exam/actions/workflows/python-pipeline.yml/badge.svg)](https://github.com/jtj20/pgr301-exam/actions/workflows/python-pipeline.yml)

## 1A
> 1. Lag en fork av repositoriet

> 2. I ditt repository, legg inn følgende repository action secrets: 

`AWS_ACCESS_KEY_ID`: IAM Access Key ID

`AWS_SECRET_ACCESS_KEY`: IAM Access Key Secret

`BUCKET_NAME`: Navn på S3 bucket som inneholder bilder (bruk candidate-2042)

> 3. Naviger til Github Actions og trykk på følgende workflow

`SAM build and deploy python lambda function`

> 4. Trykk på 'Run workflow' fra branch 'main' og vent på at pipelinen fullfører.

> 5. Trykk på pipelinen, velg steget 'Deploy solution' naviger ned til Outputs og se etter URL'en til Kjells HelloWorldApi. Denne kan brukes for å teste ut koden via postman (GET).

## 1B
Følgende steg er gjort i cloud 9. Clone repository koden.

> 6. Kjør følgende kommandoer for å bygge og kjøre docker image:

`cd pgr301_exam/kjell/hello_world/`

`docker build -t kjellpy .`

`docker run -p 9000:8080 -e AWS_ACCESS_KEY_ID=XXX -e AWS_SECRET_ACCESS_KEY=YYY -e BUCKET_NAME=candidate-2042 kjellpy:latest`

`curl "http://localhost:9000/2015-03-31/functions/function/invocations" -d '{"empty":"payload"}'`

> Vent ett par sekunder og se at du blir returnert en JSON payload. 


# Oppgave 2: Overgang til Java og Spring-boot
## 2A
[![Build and push spring-boot docker image](https://github.com/jtj20/pgr301-exam/actions/workflows/springboot-pipeline.yml/badge.svg)](https://github.com/jtj20/pgr301-exam/actions/workflows/springboot-pipeline.yml)

> 1. Kjør følgende kommandoer for å bygge og kjøre docker image:

`docker build -t ppe . `

`docker run -p 8080:8080 -e AWS_ACCESS_KEY_ID=XXX -e AWS_SECRET_ACCESS_KEY=YYY -e BUCKET_NAME=candidate-2042 ppe`

> 2. Kjør følgende kommando for å teste lokalt

`curl "http://localhost:8080/scan-ppe?bucketName=candidate-2042"`

## 2B

> 1. Legg til følgende repository secret, URL (ikke URI) kan hentes ut fra ECR i AWS og inneholder alt utenom repository navn. (i.e. url.com/)

`AWS_ECR_REGISTRY`

> 2. Naviger til Github Actions og trykk på følgende workflow

`Build and push spring-boot docker image`

> 3. Trykk på 'Run workflow' fra branch 'main' og vent på at pipelinen fullfører.

> 4. Image skal dukke opp i ECR, og nyeste image vil i tillegg ha taggen 'latest'.

# Oppgave 3: Terraform, AWS Apprunner og IaC

## 3A
- [x] Added variables populated through pipeline
- [x] Removed hard-coding of `image_identifier`
- [x] Apprunner is named with variable
- [x] Added instance configuration for apprunner service to specify requested values for `cpu` and `memory`
- [x] Specified terraform version, as not all versions support the lower configurations for `cpu` and `memory`.

## 3B

- [x] Added terraform stage in pipeline, added backend configuration.

> 1.  Dersom stegene er fulgt tidligere (mtp repository secrets) så skal alt være på plass for å få kjørt opp pipelinen. 

> 2. For å kjøre koden via apprunner kan man kjøre følgende request i postman: 

`GET https://gxmpefp8ek.eu-west-1.awsapprunner.com/scan-ppe?bucketName=candidate-2042`

# Oppgave 4: Feedback 

Oppgaven er forsøkt løst, men jeg har dessverre ikke klart å konfigurere tilstrekkelig slik at custom metrics fra Micrometer dukker opp i Cloudwatch.. 

# Oppgave 5: Drøfteoppgaver

## 5A. Kontinuerlig Integrering
CI (Continuous Integration) er automatiserte løsninger for å åpne for hyppige integrasjoner av kode til samme kodebase, med mål om å oppdage potensielle feil tidlig i utviklingsprosessen. Å arbeide under denne metodikken innebærer å jobbe stegvis for å etterstrebe høy kvalitet på kodebasen til enhver tid. 

Den største fordelen med å jobbe med CI er at det er en forutsetning å ha implementert en effektiv infrastruktur som sikrer enkel tilgang på kodebasen, som gjør det både enkelt å bidra uansett om det er som funksjonalitet eller for å fikse en feil. Som en relativt fersk utvikler er det betydelig mye ‘tryggere’ å bidra til kodebasen når man gjør det ofte. I tillegg til dette vil feil oppdages tidlig ved hjelp av gjennomført infrastruktur og implementering av reparasjoner vil ikke være overveldende ettersom det er små hyppige bidrag som er avgitt til kodebasen. I forhold til kodekvalitet så vil det ha mye å si at utvikleren til enhver tid ikke må forholde seg til overveldende kompleksitet, og kan derfor fokusere på å levere mindre funksjonalitet av høyere kvalitet. Dette vil også forhindre ‘rabbit holes’ som er et kjent fenomen og store tidstyver i utviklingsbransjen dersom kompleksiteten blir høy. 

I det praktiske er CI i stor grad implementert ved hjelp av teknologier som git, gjort tilgjengelig av eksempelvis Github og Azure DevOps. I første omgang er det viktig å kartlegge en tydelig branching strategi når man forholder seg til versjonskontroll. Et populært valg her er for eksempel Git Flow. Dette er i stor grad fundamentet for hvordan man skal bidra til noe felles på en effektiv måte. Deretter har man mulighet til å realisere dette videre ved å tildele rettigheter og regler på disse forskjellige branchene via verktøy nevnt ovenfor som forhindrer uønskede snarveier. Videre vil man kunne etablere infrastruktur som automatiserer mye av dette ved hjelp av pipelines som kan stå for bygg, testing og deployments til de relevante utviklingsmiljøene. I tillegg til dette kan man også opprette testplaner som kan generere et automatisert resultat gjennom input hentet fra akseptansetester. Dette bare noen eksempler på hvordan man typisk kan benytte seg av CI i det daglige. 


## 5B. Sammenligning av Scrum/Smidig og DevOps fra et Utviklers Perspektiv
I sin essens er scrum et verktøy som hjelper folk, team, og organisasjoner generere verdi gjennom agile løsninger for komplekse problemer (Schwaber & Sutherland, 2017). Scrum karakteriseres ved at man jobber iterativt med arbeidsoppgaver for å syklisk løse problemer. Dette oppnås ved å tildele nøkkelroller i tillegg til å opprettholde fast, men dynamisk struktur. I det følgende beskrives scrum ut fra et programvareutvikling-perspektiv. 

Scrums hovedroller kan kjennetegnes som produkteier, scrum master og utviklingsteam. Scrum og iterativt arbeid er ikke forbeholdt utviklere, den dynamiske flyten er utarbeidet i samarbeid med alle rollene som er tildelt. Flyten vil være at prosjekteieren bestiller funksjonalitet fra en scrum master, som deretter står som ansvarlig for å orkestrere en iterativ innsats for å løse problemene innad i sitt utviklingsteam. Dette oppnås ved å benytte og drifte viktige komponenter som backlogs og kanban i tillegg til å opprettholde fast og hyppig kommunikasjon, typisk gjennom daglige standups, code review og retrospectives. 

En klar fordel ved bruk av scrum som utvikler er at man har tilgang på hyppige tilbakemeldinger. Dette kommer av metodikkens natur, ettersom man jobber i korte intervaller og dermed leverer oftere til både produkteiere men også til teamet i form av drifting eller gjennom hyppige møter for å diskutere det gjennomførte arbeidet. I tillegg til dette tvinger rammeverket alle de involverte rollene til å redusere kompleksiteten i arbeidsoppgavene ved å stykke det opp ettersom det er et mål å løse oppgavene innenfor det gitte intervallet. Dette er på en annen side også en fallgruve, dersom scrum effektivt skal fungere er man avhengig av å legge grunnlaget for det. Dette krever en innsats ikke bare innad i teamet, men også på tvers av avdelinger innad i selskapet. Dersom enten backlog eller spesifikasjoner blir utydelig og kompleksiteten blir overdreven, vil dette raskt resultere i et stort press på utviklingsteamet og økt teknisk gjeld. 


DevOps fokuserer på samarbeid og automatisering oppnådd ved å hensynta prinsipper som CI/CD og testing. DevOps er utvunnet av et samarbeid mellom operasjonelle team og utviklingsteam. Målet er å kunne gjøre prosesser og samarbeid sømløst i tillegg til å opprettholde kodestandard gjennom testing og tidlig oppdagelse av feil.

Et fullt operasjonelt utviklingsmiljø med devops infrastruktur vil øke leveransetempoet betraktelig. Det er ofte konfigurering og oppsett som tar mest tid innenfor utviklingsprosessen, og ved å implementere DevOps metodikk i miljøet vil man kunne strømlinje denne prosessen, gjøre den trygg og etablere solide metoder for å kunne drifte og utvikle videre. Dette medfører at en utvikler har mer tid til å fokusere på kode og kvalitet på forretningslogikk heller enn gjentatte utfordringer i forhold til infrastrukturen. Dette vil også føre til mindre konfigureringsfeil og mer konsekvente resultater ved deployments til utviklingsmiljøene.

Gjennomført DevOps krever en viss infrastruktur, og denne infrastrukturen krever en viss tid å implementere. Dersom utviklingsteamet selv står for denne prosessen er det en utfordring og mulighet for at det blir tiltakende, og at man bruker lengre tid på infrastruktur enn å skrive forretningslogikken, dette kan føre til forsinkede leveranser. Dersom det er et eksternt operasjonelt team som bistår ved utviklingen av infrastrukturen så kan kommunikasjonen være en utfordring, detaljer kan gå tapt eller det kan gå lengre tid enn ønskelig å motta ressursene man trenger. 

Når det gjelder leveransetempo så er dette i aller høyeste grad et fokus innenfor samtlige utviklingsmetodikker, hvorvidt devops eller scrum er mest effektivt beror på utviklingssituasjonen. DevOps på sin side er mest preget av automatisering av alle prosesser, men scrum er mest preget av å være dynamisk. Et veletablert team som fortrinnsvis utfører deployments til det samme miljøet og mange av de samme applikasjonene vil derfor med høy sannsynlighet foretrekke en mer devops preget metodikk. Mens scrum på sin side, som mer dynamisk, vil være fordelaktig for et utviklingsteam som jobber på tvers av avdelinger og miljøer og jobber med flere forskjellige teknologier, der en effektiv infrastruktur er en kompleks oppgave å sette opp. 

I forhold til programvarekvalitet er man nødt til å hensynta testing. I DevOps er hovedtanken å skrive tester og gjennomføre disse automatisert, mens Scrum på sin side ønsker å i stor grad gjennomføre testingen som en integrert prosess innad i den iterative utviklingsprosessen. Hvorvidt den ene er bedre enn den andre beror på hvilken type applikasjon som utvikles. Dersom det er en data tung applikasjon som er avhengig av data kvalitet og effektivitet, kan devops med sine automatiserte tester være den riktige beslutningen. Dersom man er en programvarearkitekt som planlegger en ny modul i en mobilapplikasjon kan det være mest hensiktsmessig å konsultere med teamet i en code review eller retrospective. 

I min arbeidserfaring og erfaring som student opplever jeg at det er et samspill mellom de to som er optimalt. Det vil ikke i alle tilfeller være hensiktsmessig å jobbe totalt datadrevet, det vil alltid være behov for å kunne konsultere i faste møter med sine kolleger for å kunne sanke inn nye innspill til hvordan man skal angripe en problemstilling i koden. Samtidig er det alltid ønskelig å ha en viss grad av automatisering i koden for å sikre konsekvente deployments og pålitelig infrastruktur. En passende balansegang mellom disse må finnes ut ifra utviklingsteamets komposisjon og arbeidsoppgaver. 

## 5C: Det Andre Prinsippet - Feedback

Det er flere aspekter og nyanser av feedback som kan være nyttig for å sikre at funksjonalitet møter brukernes behov. Det aller første som er av interesse dersom man har ferdigstilt funksjonalitet er UAT (User Acceptance Testing). Dette er tilnærmet å formulere spørsmålet rett til brukeren, dette kan riktignok kun delvis automatiseres ved etablering av ‘test plans’ via eksempelvis Azure DevOps, som tilbyr brukerne et skjema som automatisk innhenter data tilbake til testen.

En annen versjon av feedback som er relevant og kan hentes automatisk er feedback i form av data, gitt at man overholder personvernregler. Dersom man har implementert en ny modul i en nettbutikk, kan man eksempelvis legge inn metrics som man kan samle og sende tilbake til AWS eller Azure eller lignende. Dette kan være en timer som sjekker hvor lang tid en bruker brukte i den nye modulen, tellere for å forstå forholdet mellom antall besøkende på applikasjonen og på den nye modulen. Målinger av hvor lang tid kode-komponentene bruker for å laste inn de forskjellige lagene av den nye modulen for å se om man etterlever kundenes krav til funksjonalitet. På sikt kan man samle disse dataene og lage et beskrivende dashboard med grafer som viser en historikk for å skildre situasjonen. Dette er alle komponenter som er mulig å integrere mens man utvikler. Her går det an å tenke på hva som er viktigst for kunden, og finne metrics for hvordan man kan gjøre det målbart - for å så se om man klarer å etterstrebe den ønskede kvaliteten.

Det er også mulig å integrere disse stegene tidlig i utviklingsprosessen, en typisk strategi er å etablere en beta versjon. Her kan kan legge inn mange metrikker samt å samle inn data via automatiserte skjema etc. Ved å invitere brukere til å teste løsningen tidlig kan man danne en god grunnmur av data for å videre kartlegge en ideell rute for utvikling. 
