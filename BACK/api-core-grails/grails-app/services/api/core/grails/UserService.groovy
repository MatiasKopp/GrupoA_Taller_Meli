package api.core.grails

import grails.gorm.transactions.Transactional

import static Function.log

@Transactional
class UserService {

    User getUser(String id){

        User user = User.findById(id)

        return user
    }

    void save(User user){
        user.coupons = 0
        user.save()
    }

    List<Category> getPreferences(User user){
        return user.preferences
    }

    List<Purchase> getPurchases(User user){
        return user.purchases
    }

    User validateLogin(String email, String password){

        User user = User.findByEmail(email)

        if(user==null){
            return null
        }

        if(user.email==email && user.password==password){
            return user
        }

        return null
    }

}
