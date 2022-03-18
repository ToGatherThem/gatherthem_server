package fr.bryanprolong.gatherthem.gatherthem_server.collections.exposition;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collections")
public class CollectionsController {


    @GetMapping
    public String getStaticData(){
        return "[\n" +
                "        {\n" +
                "            \"id\": \"a36f30f0-63aa-43c5-b9b6-09f94b9a0644\",\n" +
                "            \"owner_id\": \"51e18d3c-88f5-4917-97d1-2ee3ed9be3b5\",\n" +
                "            \"type\": \"Livres\",\n" +
                "            \"name\": \"Mes livres de fantasy\",\n" +
                "            \"description\": \"J'adore vraiment beaucoup très très énormement ces livres\",\n" +
                "            \"created_at\": 1647509337,\n" +
                "            \"properties\" : [\n" +
                "                {\n" +
                "                    \"name\": \"Titre\",\n" +
                "                    \"index\": 0\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Résumé\",\n" +
                "                    \"index\": 2\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Auteur\",\n" +
                "                    \"index\": 1\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Date de parution\",\n" +
                "                    \"index\": 3\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"ISBN\",\n" +
                "                    \"index\": 4\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Date d'obtention\",\n" +
                "                    \"index\": 5\n" +
                "                }\n" +
                "            ],\n" +
                "            \"items\": [\n" +
                "                {\n" +
                "                    \"id\": \"096e73bc-8eba-4f00-a831-7ba095da6d44\",\n" +
                "                    \"created_at\": 1647509337,\n" +
                "                    \"date_d_obtention\": \"\",\n" +
                "                    \"titre\": \"Harry Potter à l'école des sorciers\",\n" +
                "                    \"resume\": \"Le jour de ses onze ans, Harry Potter, un orphelin élevé par un oncle et une tante qui le détestent, voit son existence bouleversée. Un géant vient le chercher pour l'emmener à Poudlard, une école de sorcellerie ! Voler en balai, jeter des sorts, combattre les trolls : Harry se révèle un sorcier doué. Mais un mystère entoure sa naissance et l'effroyable V., le mage dont personne n'ose prononcer le nom.\",\n" +
                "                    \"auteur\": \"J.K. Rowling\",\n" +
                "                    \"date_de_parution\": \"\",\n" +
                "                    \"isbn\": \"2070584623\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "]";
    }

}
