package presentation;
import model.Oeuvre;
import services.OeuvreServices;

public class OeuvreTest {
    public static void main(String[] args) {

        OeuvreServices service = new OeuvreServices();

        // TEST 1 - Créer une oeuvre
        System.out.println("=== TEST 1 : CREATE ===");
        try {
            Oeuvre o = new Oeuvre("La Joconde", "Picasso", "Peinture", 5000);
            Oeuvre created = service.insert(o);
            System.out.println("✅ Oeuvre créée : ID=" + created.getIdOeuvre());
        } catch (Exception e) {
            System.out.println("❌ Erreur CREATE : " + e.getMessage());
        }

        // TEST 2 - Afficher toutes les oeuvres
        System.out.println("\n=== TEST 2 : FIND ALL ===");
        try {
            service.findAllOeuvre().forEach(o ->
                    System.out.printf("✅ %d : %s | %s | %s | %d DH%n",
                            o.getIdOeuvre(), o.getTitre(), o.getArtiste(), o.getCategorie(), o.getPrix())
            );
        } catch (Exception e) {
            System.out.println("❌ Erreur FIND ALL : " + e.getMessage());
        }

        // TEST 3 - Trouver une oeuvre par ID
        System.out.println("\n=== TEST 3 : FIND BY ID ===");
        try {
            Oeuvre found = service.findOeuvre(1);
            if (found != null) {
                System.out.println("✅ Oeuvre trouvée : " + found.getTitre() + " | " + found.getArtiste());
            } else {
                System.out.println("❌ Oeuvre non trouvée !");
            }
        } catch (Exception e) {
            System.out.println("❌ Erreur FIND BY ID : " + e.getMessage());
        }

        // TEST 4 - Filtrer par artiste
        System.out.println("\n=== TEST 4 : FILTRE PAR ARTISTE ===");
        try {
            service.filtreByArtist("Picasso").forEach(o ->
                    System.out.println("✅ " + o.getTitre() + " | " + o.getArtiste())
            );
        } catch (Exception e) {
            System.out.println("❌ Erreur FILTRE ARTISTE : " + e.getMessage());
        }

        // TEST 5 - Filtrer par categorie
        System.out.println("\n=== TEST 5 : FILTRE PAR CATEGORIE ===");
        try {
            service.filtreByCategory("Peinture").forEach(o ->
                    System.out.println("✅ " + o.getTitre() + " | " + o.getCategorie())
            );
        } catch (Exception e) {
            System.out.println("❌ Erreur FILTRE CATEGORIE : " + e.getMessage());
        }

        // TEST 6 - Update
        System.out.println("\n=== TEST 6 : UPDATE ===");
        try {
            Oeuvre toUpdate = new Oeuvre("La Joconde Updated", "Picasso", "Sculpture", 9000, 2);
            boolean updated = service.updateOeuvre(toUpdate);
            System.out.println(updated ? "✅ Oeuvre modifiée !" : "❌ Modification échouée !");
        } catch (Exception e) {
            System.out.println("❌ Erreur UPDATE : " + e.getMessage());
        }

        // TEST 7 - Delete
        System.out.println("\n=== TEST 7 : DELETE ===");
        try {
            boolean deleted = service.deleteOeuvre(1);
            System.out.println(deleted ? "✅ Oeuvre supprimée !" : "❌ Suppression échouée !");
        } catch (Exception e) {
            System.out.println("❌ Erreur DELETE : " + e.getMessage());
        }
    }
}