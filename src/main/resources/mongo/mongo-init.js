db = db.getSiblingDB('GatherThemMongo')
db.createUser(
    {
        user: "dev",
        pwd: "example",
        roles: [
            {
                role: "readWrite",
                db: "GatherThemMongo"
            }
        ]
    }
)

db.createCollection('collections');

db.collections.insertMany([
    {
        "id": "a36f30f0-63aa-43c5-b9b6-09f94b9a0644",
        "owner_id": "51e18d3c-88f5-4917-97d1-2ee3ed9be3b5",
        "type": "Livres",
        "name": "Mes livres de fantasy",
        "description": "J'adore vraiment beaucoup très très énormement ces livres",
        "created_at": 1647509337,
        "fields" : [
            {
                "name": "Titre",
                "index": 0
            },
            {
                "name": "Résumé",
                "index": 2
            },
            {
                "name": "Auteur",
                "index": 1
            },
            {
                "name": "Date de parution",
                "index": 3
            },
            {
                "name": "ISBN",
                "index": 4
            },
            {
                "name": "Date d'obtention",
                "index": 5
            }
        ],
        "items": [
            {
                "id": "096e73bc-8eba-4f00-a831-7ba095da6d44",
                "created_at": 1647509337,
                "date_d_obtention": "",
                "titre": "Harry Potter à l'école des sorciers",
                "resume": "Le jour de ses onze ans, Harry Potter, un orphelin élevé par un oncle et une tante qui le détestent, voit son existence bouleversée. Un géant vient le chercher pour l'emmener à Poudlard, une école de sorcellerie ! Voler en balai, jeter des sorts, combattre les trolls : Harry se révèle un sorcier doué. Mais un mystère entoure sa naissance et l'effroyable V., le mage dont personne n'ose prononcer le nom.",
                "auteur": "J.K. Rowling",
                "date_de_parution": "",
                "isbn": "2070584623"
            }
        ]
    }
]);
