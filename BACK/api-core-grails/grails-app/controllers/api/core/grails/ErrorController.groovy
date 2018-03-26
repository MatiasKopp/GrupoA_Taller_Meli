package api.core.grails

import grails.converters.JSON

class ErrorController {

    def notFound(){
        response.status = 404
        render(new ResponseError("page_not_found") as JSON)
    }
}
