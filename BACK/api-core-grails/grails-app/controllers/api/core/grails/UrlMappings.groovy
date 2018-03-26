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

        "/categories/$id/items"(controller: "Item", action: "itemsByCategory")


        "/users/$id"(controller: "User", action: "user")
        "/users"(controller: "User", action: "add", method: "POST")
        "/items"(controller: "Item", action: "items")
        "/items/$id"(controller: "Item", action: "item")
        "/items/search"(controller: "Item", action: "searchItems")

        "/stats"(controller: "Stats", action: "stats")
        "/user/$id/stats"(controller: "Stats", action: "userStats")

        "/users/$id/prefers"(controller:"Item", action:"preferences")
        "/purchases"(controller: "Purchase", action: "add", method: "POST")
        "/purchases/$id"(controller: "Purchase", action: "purchase")
        "/users/$id/purchases"(controller:"User", action:"purchases")

        "/login"(controller:"User", action:"login", method: "POST")

        "/comments"(controller:"Comment", action:"postComment", method: "POST")
        "/comments"(controller:"Comment", action:"getComments", method: "GET")

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(controller:"Error", action:"notFound")
    }
}
