# Stockmarket

## Zespół projektowy
- Katarzyna Hibner
- Piotr Skrocki
- Małgorzata Szymczyńska
- Kamil Zaharan

### Instalacja

- Aplikacja wymaga stworzenia bazy danych MySql o nazwie Stockmarket oraz użytkownika o loginie makler z hasłem makler.

### Uruchomienie aplikacji

##### Aby uruchomić LotteryService, należy wywołać:
1. `mvn clean install`
2. `mvn spring-boot:run`

### REST API

Wszystkie strony znajdują się na adresie localhost:8080.
- **/companies** (GET) obsługuje StatisticsService i służy do pobierania ogólnych statystyk

- **adres:8080/api/v1/statistics/users/:userEmail** (GET) obsługuje StatisticsService i służy do pobierania statystyk użytkownika, którego email znajduje się w adresie

- **adres:8080/api/v1/statistics/:lotteryDate** (GET) obsługuje StatisticsService i służy do pobierania statystyk losowania z podanej daty

- **adres:8080/api/v1/users/actions/draw** (POST) ogsługuje LotteryService i służy do losowania zwycięzców