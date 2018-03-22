package api_core_grails

import javax.persistence.Column

class User {

    int user_id;

    String name;

    String surname;

    String email;

    String password;

    static constraints = {
    }
}
