package api.core.grails

class Item {

    String id
    String title
    Double price
    Integer initial_quantity
    Integer available_quantity
    String description
    String city
    String state
    String country
    String latitude
    String longitude
    Date date_created
    Date last_update
    Integer query_count
    Integer weight

    static belongsTo = [category: Category]

    static constraints = {
        title(blank: false, nullable: false)
        price(blank: false, nullable: false)
        initial_quantity(blank: false, nullable: false)
        available_quantity(blank: false, nullable: false)
        description(blank: false, nullable: false)
        city(blank: false, nullable: false)
        state(blank: false, nullable: false)
        country(blank: false, nullable: false)
        latitude(blank: false, nullable: false)
        longitude(blank: false, nullable: false)
        date_created(blank: false, nullable: false)
        last_update(blank: false, nullable: false)
        query_count(blank: false, nullable: false)
        weight(blank: false, nullable: false)
    }

    static mapping = {
        id generator: 'assigned'
        description column: 'description', sqlType: 'text'
        version false
    }

}
