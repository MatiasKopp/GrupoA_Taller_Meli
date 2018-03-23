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
        "/users/$id"(controller: "User", action: "user")
        "/users"(controller: "User", method: "POST", action:"add")
        "/items"(controller: "Item", action: "items")
        "/items/$id"(controller: "Item", action: "item")
        "/users/$id/prefers"(controller:"Item", action:"preferences")
        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
