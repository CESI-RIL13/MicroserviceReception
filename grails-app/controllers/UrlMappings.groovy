class UrlMappings {

    static mappings = {

        "/bon-expeditions"(resources:"bonExpedition") {
            "/produits"(resources:"produit")
        }
        "/produits"(resources:"produit") {
            "/bon-expedition"(resource:"bonExpedition")
        }
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
