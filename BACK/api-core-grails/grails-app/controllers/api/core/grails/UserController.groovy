package api.core.grails

import grails.converters.JSON
import org.grails.datastore.mapping.query.Query

import static Function.log

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
        try{
            userService.save(user)
            render(user as JSON)
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

    def purchases(int id){
        User user = userService.getUser(id)

        if (user == null) {
            response.status = 404
            render(new ResponseError("user_not_found: "+id) as JSON)
        }

        List<Purchase> purchases = userService.getPurchases(user)

        if (purchases == null) {
            response.status = 404
            render(new ResponseError("user_without_purchases: "+id) as JSON)
        }

        render(purchases as JSON)
    }

    def login(){

        String email = ""
        String password = ""

        try {

            def user_json = request.JSON
            email = user_json.email
            password = user_json.password
        }catch (Exception e){
            response.status = 400
            render(new ResponseError("missing_parameters") as JSON)
        }

        if(email==null || password==null || email.isEmpty() || password.isEmpty()){
            response.status = 400
            render(new ResponseError("missing_parameters") as JSON)
        }

        User user = this.userService.validateLogin(email, password)

        if(user==null){
            response.status = 400
            render(new ResponseError("invaild_user") as JSON)
        } else {
            render(user as JSON)
        }

    }


}
