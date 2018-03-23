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

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
