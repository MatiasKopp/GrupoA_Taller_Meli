package api.core.grails

import grails.converters.JSON
import org.grails.datastore.mapping.query.Query

class UserController {

    static responseFormats = ['json']

    UserService userService

    def user(String id){
        //log(id)
        User user = userService.getUser(id)

        if(user==null){
            response.status = 404
            render(new ResponseError("user_not_found: "+id) as JSON)
        }

        render(user as JSON)
    }

    def add(User user){
        if (user == null) {
            response.status = 404
            render(new ResponseError("user_not_found: "+id) as JSON)
        }

        try{
            userService.save(user)
        }catch(Exception ex){
            ex.printStackTrace()
        }
    }

    def preferences(int id){
        User user = userService.getUser(id)

        if (user == null) {
            response.status = 404
            render(new ResponseError("user_not_found: "+id) as JSON)
        }

        List<Category> preferences = userService.getPreferences(user)

        if (preferences == null) {
            response.status = 404
            render(new ResponseError("user_without_preferences: "+id) as JSON)
        }

        render(preferences as JSON)
    }

}
