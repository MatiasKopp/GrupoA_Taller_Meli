package api.core.grails

import grails.converters.JSON

class LoginController {

    def index() { }

    def add(User user){
        try{
            userService.save(user)
            render(user as JSON)
        }catch(Exception ex){
            ex.printStackTrace()
        }
    }
}
