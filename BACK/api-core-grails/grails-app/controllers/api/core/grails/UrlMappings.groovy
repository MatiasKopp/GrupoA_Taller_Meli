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
        "/users"(controller: "User", action: "add", method: "POST")
        "/items"(controller: "Item", action: "items")
        "/items/$id"(controller: "Item", action: "item")
        "/users/$id/prefers"(controller:"Item", action:"preferences")
        "/purchases"(controller: "Purchase", action: "add", method: "POST")
        "/purchases/$id"(controller: "Purchase", action: "purchase")
        "/users/$id/purchases"(controller:"User", action:"purchases")
        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
