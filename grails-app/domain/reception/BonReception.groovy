package reception
import grails.rest.Resource

@Resource(formats=['html','json', 'xml'])
class BonReception {

    Date date
    String emetteur
    String quai

    static hasMany = [produits:Produit]
    static constraints = {
    }
}
