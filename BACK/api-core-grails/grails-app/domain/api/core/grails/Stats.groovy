package api.core.grails

class Stats {

    static belongsTo = [user: User]

    Integer visit_items
    Integer bought_items

    static constraints = {
        visit_items(default: 0, nullable: false)
        bought_items(default: 0, nullable: false)
    }
}
