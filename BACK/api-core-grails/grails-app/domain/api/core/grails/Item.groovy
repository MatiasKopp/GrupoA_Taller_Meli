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
    Date last_updated
    Integer query_count
    Integer weight

    static hasMany = [pictures: Picture]
    static belongsTo = [category: Category]

    static constraints = {
        title(blank: false, nullable: false)
        price(blank: false, nullable: false)
<<<<<<< HEAD
        initial_quantity(blank: false, nullable: false, default: 0)
        available_quantity(blank: false, nullable: false, default: 0)
        description(blank: false, nullable: false)
        city(blank: false, nullable: false)
        state(blank: false, nullable: false)
        country(blank: false, nullable: false)
        latitude(blank: false, nullable: false)
        longitude(blank: false, nullable: false)
=======
        initial_quantity(blank: false, nullable: false)
        available_quantity(blank: false, nullable: false)
        description(blank: true, nullable: false)
        city(blank: true, nullable: false)
        state(blank: true, nullable: false)
        country(blank: true, nullable: false)
        latitude(blank: true, nullable: false)
        longitude(blank: true, nullable: false)
>>>>>>> 0405d20e8d0db76002666835db8c4b088aef1d52
        date_created(blank: false, nullable: false)
        last_updated(blank: false, nullable: false)
        query_count(blank: false, nullable: false, default: 0)
        weight(blank: false, nullable: false, default: 0)
    }

    static mapping = {
        id generator: 'assigned'
        description column: 'description', sqlType: 'text'
        version false
    }

}
