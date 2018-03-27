package api.core.grails

import grails.converters.JSON

class StatsController {

    StatsService statsService
    UserService userService

    def stats(){

        render(statsService.getAllStats() as JSON)

    }

    def userStats(){

        User user = userService.getUser(params.id.toString())

        if (user == null) {
            response.status = 404
            render(new ResponseError("user_not_found: "+id) as JSON)
        }

        grails.converters.JSON.properties.deep = false
        render(this.statsService.getUserStas(user) as JSON)
    }


}
