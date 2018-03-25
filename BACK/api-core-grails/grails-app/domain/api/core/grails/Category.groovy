package api.core.grails

class Category {

    String id
    String name
    String picture
    Integer total_items_in_this_category

    static constraints = {
        name(blank: false, nullable: false)
        picture(blank: false, nullable: false)
        total_items_in_this_category(blank: false, nullable: false, default: 0)
    }

    static mapping = {
        id generator: 'assigned'
        version false
    }

}
