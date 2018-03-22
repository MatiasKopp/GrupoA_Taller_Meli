package api.core.grails

class Preferences {

    String preference

    static hasMany = [users: User]

    static constraints = {

    }
}
