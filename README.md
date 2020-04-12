# java_zaawansowana

## Reverse Proxy vs Forward Proxy
### Opis rozwiązania
Rozwiązanie zostało oparte o użycie jako reverse proxy ngix'a oraz odpowiedni wpis w pliku hosts.
Kiedy odwołuje się lokalnie po zdefiniowanym adresie maciej.local, dzięki wpisowi w pliku hosts to zapytanie zostanie rozwiązane lokalnie, a dzięki postawionemu lokalnie kontenerowi z nginx nasłuchującego na porcie 80 oraz z vhostem skonfigurowanym na maciej.local, zostaje przekierowane na port 8080.
### Czym różni się Reverse Proxy od Forward Proxy?
Forward Proxy jest niczym innym jak przekazywaniem pakietów do docelowego hosta, z tym, że odbiorca identyfikuję nadawcę nie po hoście, z którego zostało wysłane zapytanie, a po hoście na którym został ustawiony forward proxy.

Reverse Proxy z kolei jest usługą pośredninczącą na ogół pomiędzy kilkoma serwisami, dzięki temu host, który próbuję dostać się do jakiegoś serwisu może użyć nazwy domenowej na porcie 80/443, a nie musi znać portu usługi, również na poziomie Reverse Proxy można przypisać ceryfikat SSL do naxwy domenowej danego serwisu.
