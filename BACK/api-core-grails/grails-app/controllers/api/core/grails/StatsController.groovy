package api.core.grails

import grails.converters.JSON

class StatsController {

    StatsService statsService

    def stats(){

        render(statsService.getAllStats() as JSON)

    }
    def userStats(){
        render([] as JSON)
    }
}
