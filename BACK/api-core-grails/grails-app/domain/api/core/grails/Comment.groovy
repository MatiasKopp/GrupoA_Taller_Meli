package api.core.grails

class Comment {

    String description
    Date date_created

    static belongsTo = [user: User]
    static constraints = {
        description(nullable: false, blank: false)
        date_created(nullable: false, blank: false)
    }
}
