package api.core.grails

class Picture {

    String url
    String size
    static belongsTo = [item: Item]

    static constraints = {
        url(blank: false, nullable: false)
        size(blank: false, nullable: false)
    }
/*
    static mapping = {
        table 'Pictures' , schema: "meli_paralelo"
        id column: 'picture_id', sqlType: 'text'
        url column: 'name', sqlType: 'text'
        size column: 'picture', sqlType: 'text'

        version false
    }
    */
}
