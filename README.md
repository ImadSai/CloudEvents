# Documentation de la classe `CloudEvent<T>`

## 1. Description Générale

La classe `CloudEvent<T>` est conçue pour transporter des évènements (messages) enrichis de métadonnées d’entreprise, tout en respectant la spécification [CloudEvents](https://cloudevents.io/) (version 1.0). Elle se compose de deux grands ensembles d’attributs :

1. **Enterprise Events** :  
   Des métadonnées internes à l’entreprise (`userId`, `sessionId`, `requestId`, etc.) utiles pour tracer et corréler les évènements dans les différents systèmes.

2. **Cloud Events** :  
   Des champs respectant la spécification CloudEvents, tels que `id`, `time`, `specversion`, `type`, etc., afin de standardiser l’échange d’évènements entre systèmes et plateformes.

---

## 2. Tableau des Champs

| **Champ**           | **Type**                     | **Description**                                                                                                                                            | **Exemple**                                                   |
|---------------------|------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------|
| `userId`            | `String`                    | Identifiant unique de l’utilisateur à l’origine de l’événement (ou qui a initié l’action).                                                                 | `"user-12345"`                                                |
| `sessionId`         | `String`                    | Identifiant de la session (ex. session web) au moment de la création de l’événement.                                                                       | `"session-67890"`                                             |
| `requestId`         | `String`                    | Identifiant de la requête qui a déclenché l’événement.                                                                                                     | `"req-abcdef"`                                                |
| `contentCategory`   | `CloudEventContentCategory` | Catégorie du contenu métier. Peut aider à distinguer la nature de l’événement (ex. `ORDER`, `PAYMENT`, `NOTIFICATION`, etc.).                              | `CloudEventContentCategory.ORDER`                             |
| `id`                | `String`                    | Identifiant unique de l’événement CloudEvent (généré par défaut avec `UUID`).                                                                              | `"7e53d7d2-9d2f-4d4f-90ca-5f561a6380df"`                       |
| `time`              | `Date`                      | Horodatage de l’événement CloudEvent (UTC). Formaté en `yyyy-MM-dd'T'HH:mm:ss.SSSXXX`.                                                                      | `"2023-01-15T09:24:32.123Z"`                                  |
| `specversion`       | `CloudEventSpecVersion`     | Version de la spécification CloudEvents, par défaut `V1`.                                                                                                  | `CloudEventSpecVersion.V1`                                   |
| `type`              | `EventType`                 | Type de l’événement (ex. `com.company.order.created`).                                                                                                     | `EventType.ORDER_CREATED`                                     |
| `source`            | `String`                    | Origine de l’événement, souvent une URI ou un chemin spécifique (ex. `/orders-service`).                                                                    | `"/orders-service"`                                           |
| `subject`           | `String`                    | Sujet de l’événement, souvent un identifiant supplémentaire (ex. ID d’une ressource).                                                                       | `"order-42"`                                                 |
| `datacontenttype`   | `CloudEventDataContentType` | Indique le type de contenu des données (ex. `application/json`).                                                                                           | `CloudEventDataContentType.APPLICATION_JSON`                  |
| `data`              | `T`                         | Contenu de l’événement. Il s’agit de l’objet métier qui est transporté (une commande, un paiement, un message, etc.).                                      | `{ "orderId": "42", "status": "CREATED" }` (exemple JSON)     |

---

## 3. Pourquoi utiliser CloudEvents ?

- **Standardisation** :  
  CloudEvents fournit un ensemble d’attributs standards (`id`, `source`, `type`, `subject`, etc.) qui facilitent l’interopérabilité entre différentes plateformes et systèmes.

- **Interopérabilité** :  
  Grâce à la spécification, vous pouvez échanger des événements entre des services écrits dans différents langages ou frameworks sans vous soucier d’une structure propriétaire.

- **Extensibilité** :  
  Les attributs obligatoires et optionnels sont clairement définis, mais vous pouvez également ajouter des champs personnalisés (comme ceux liés aux métadonnées d’entreprise) sans casser la compatibilité CloudEvents.

- **Traçabilité & Observabilité** :  
  Les champs `id`, `time` et `source` permettent de tracer facilement les événements et de mettre en place des corrélations (ex. logs, métriques, etc.).

---

## 4. Conventions de Nommage (Topics, Queues, etc.)

Lorsque vous utilisez des systèmes de messaging (Kafka, RabbitMQ, ActiveMQ, etc.), il est important de suivre des conventions claires pour éviter les collisions et faciliter la maintenance :

1. **Nom de Topic/Queue**
    - **Syntaxe recommandée** : `[contexte].[ressource].[action].[version]`
        - Ex. `orders.created.v1`
        - Ex. `payments.processed.v1`
    - Si vous utilisez des environnements multiples, vous pouvez préfixer le nom avec la référence de l’environnement :
        - Ex. `dev.orders.created.v1`
        - Ex. `prod.payments.failed.v1`

2. **Type d’Événement**
    - Le champ `type` dans CloudEvent peut reprendre la même convention que le nom du topic, ou une variante.
    - Par exemple : `com.company.orders.created.v1`
    - Ceci permet de rendre cohérent le nom de l’événement avec son topic et facilite la cartographie des événements dans la documentation.

3. **Versionnement**
    - Lorsqu’une nouvelle version d’un schéma d’événement est publiée, incrémentez la version (`v2`, `v3`, etc.) dans le nom du topic/queue.
    - Cela permet de gérer la compatibilité ascendante et la transition progressive entre les versions.

4. **Taille & Format**
    - Évitez les noms de topic/queue trop longs ou complexes, et utilisez des séparateurs simples (le point `.` est fréquent).

---

## 5. Autres Informations Utiles

- **Gestion de la Sérialisation** :  
  Par défaut, dans beaucoup de microservices, on utilise JSON pour sérialiser/désérialiser le champ `data`. Assurez-vous que la classe `T` (par exemple `Order`) soit correctement annotée (avec Jackson ou un autre framework) pour que la sérialisation fonctionne sans problème.

- **Validation** :  
  Vous pouvez ajouter des annotations de validation (ex. `@NotNull`, `@Size`) sur certains champs pour garantir la qualité des données.

- **Sécurité** :  
  En cas de transport de données sensibles, assurez-vous de chiffrer ou d’anonymiser certains champs (informations personnelles, tokens, etc.).

- **Idempotence** :  
  Le champ `id` (UUID) peut aider à garantir qu’un même événement ne soit pas traité plusieurs fois. Selon la logique métier, vous pouvez vérifier la présence de cet `id` dans un store de “messages traités” pour éviter les doublons.

- **Observabilité** :  
  L’utilisation de bibliothèques de monitoring (ex. [Micrometer](https://micrometer.io/)) et de traçage distribué (ex. [OpenTelemetry](https://opentelemetry.io/)) peut améliorer la supervision de vos événements en production.

