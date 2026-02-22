package presentation;
import model.Client;
import model.Oeuvre;
import model.VenteArt;
import services.VenteArtService;
import java.time.LocalDateTime;

public class VenteArtTest {
    public static void main(String[] args) {

        VenteArtService service = new VenteArtService();

        // TEST 1 - Créer une vente
        System.out.println("=== TEST 1 : CREATE ===");
        try {
            Client client = new Client(16, "Maroanin", "maroanin@gmail.com");
            Oeuvre oeuvre = new Oeuvre("La Joconde", "Picasso", "Peinture", 5000, 2);
            VenteArt vente = new VenteArt(client, oeuvre, LocalDateTime.now());
            VenteArt created = service.newVente(vente);
            System.out.println("✅ Vente créée : ID=" + created.getIdVente());
        } catch (Exception e) {
            System.out.println("❌ Erreur CREATE : " + e.getMessage());
        }

        // TEST 2 - Afficher toutes les ventes
        System.out.println("\n=== TEST 2 : FIND ALL ===");
        try {
            service.showAllventes().forEach(v ->
                    System.out.printf("✅ ID=%d | Client=%s | Oeuvre=%s | Date=%s%n",
                            v.getIdVente(),
                            v.getClient().getNom(),
                            v.getOuvre().getTitre(),
                            v.getDateVente())
            );
        } catch (Exception e) {
            System.out.println("❌ Erreur FIND ALL : " + e.getMessage());
        }

        // TEST 3 - Trouver une vente par ID
        System.out.println("\n=== TEST 3 : FIND BY ID ===");
        try {
            VenteArt found = service.findVente(1);
            if (found != null) {
                System.out.println("✅ Vente trouvée : Client=" + found.getClient().getNom()
                        + " | Oeuvre=" + found.getOuvre().getTitre());
            } else {
                System.out.println("❌ Vente non trouvée !");
            }
        } catch (Exception e) {
            System.out.println("❌ Erreur FIND BY ID : " + e.getMessage());
        }

        // TEST 4 - Top Artistes
        System.out.println("\n=== TEST 4 : TOP ARTISTES ===");
        try {
            service.afficherTopArtiste().forEach(a ->
                    System.out.println("✅ " + a)
            );
        } catch (Exception e) {
            System.out.println("❌ Erreur TOP ARTISTES : " + e.getMessage());
        }

        // TEST 5 - Delete
        System.out.println("\n=== TEST 5 : DELETE ===");
        try {
            boolean deleted = service.deleteUpdate(1);
            System.out.println(deleted ? "✅ Vente supprimée !" : "❌ Suppression échouée !");
        } catch (Exception e) {
            System.out.println("❌ Erreur DELETE : " + e.getMessage());
        }
    }
}