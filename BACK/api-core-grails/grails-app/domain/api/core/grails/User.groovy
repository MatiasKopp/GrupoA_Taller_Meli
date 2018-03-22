package api.core.grails

class User {

    String name
    String surname
    String email
    String password

    static hasMany = [preferences: Preferences]

    static constraints = {
        name(blank: false, nullable: false)
        surname(blank: false, nullable: false)
        email(blank: false, nullable: false)
        password(blank: false, nullable: false)
    }
/*
    static mapping = {
        //datasource 'meli_paralelo'
        table 'Users' , schema: "meli_paralelo"
        id column: 'user_id', sqlType: 'int'
        name column: 'name', sqlType: 'text'
        surname column: 'surname', sqlType: 'text'
        email column: 'email', sqlType: 'text'
        password column: 'password', sqlType: 'text'

        version false
    }
    */
}
