package reception

class Produit {

    Long ref_bon_commane
    Long ref_bon_livraison
    Long ref_produit
    Long quantite
    Long quantite_commande
    String annotation

    static belongsTo = [bonReception:BonReception]
    static constraints = {
    }
}
