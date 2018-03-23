package api.core.grails

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/categories"(controller: "Category", action: "categories")
        "/categories/$id"(controller: "Category", action: "category")
        "/items"(controller: "Item", action: "items")
        "/items/$id"(controller: "Item", action: "item")

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
