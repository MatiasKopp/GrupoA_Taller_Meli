package api.core.grails

import grails.gorm.transactions.Transactional

@Transactional
class UserService {

    User getUser(String id){

        User user = User.findById(id)

        return user
    }

    void save(User user){
        user.save()
    }

    List<Category> getPreferences(User user){
        return user.preferences
    }

}
