package api.core.grails

import grails.converters.JSON

class ErrorController {

    def notFound(){
        response.status = 404
        render(new ResponseError("page_not_found") as JSON)
    }

    def serverError(){
        response.status = 500
        render(new ResponseError("internal_server_error") as JSON)
    }
}
