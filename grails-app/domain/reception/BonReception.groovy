package reception

class BonReception {

    Date date
    String emetteur
    String quai

    static hasMany = [produits:Produit]
    static constraints = {
    }
}
