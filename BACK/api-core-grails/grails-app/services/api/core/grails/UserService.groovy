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

        if (user.preferences != null) {

            for (prefer in user.preferences) {
                prefer = Category.findById( prefer.id )
            }
        }

        user.save(flush:true, failOnError: true)
    }

    List<Category> getPreferences(User user){
        return user.preferences.toArray()
    }

    List<Purchase> getPurchases(User user){
        return user.purchases.toArray()
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

    def Integer getCuponesUsuario(String idUsuario) {
        User usr = getUser(idUsuario)
        if (usr == null){
            return 0
        } else {
            return usr.coupons
        }
    }

}
