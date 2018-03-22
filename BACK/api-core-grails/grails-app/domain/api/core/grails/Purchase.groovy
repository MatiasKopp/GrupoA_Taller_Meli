package api.core.grails

class Purchase {

    Double base_price
    Double final_price
    Float discount

    static belongsTo = [user: User]

    static constraints = {
        base_price(blank: false, nullable: false)
        final_price(blank: false, nullable: false)
        discount(blank: false, nullable: false)
    }
/*
    static mapping = {
        table 'Purchases' , schema: "meli_paralelo"
        id column: 'purchase_id', sqlType: 'int'
        base_price column: 'base_price', sqlType: 'double'
        final_price column: 'final_price', sqlType: 'double'
        discount column: 'discount', sqlType: 'float'

        version false
    }
    */
}
