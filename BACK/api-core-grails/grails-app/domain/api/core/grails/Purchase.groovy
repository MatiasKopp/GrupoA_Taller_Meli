package api.core.grails

class Purchase {

    Double base_price
    Double final_price
    Float discount
    Integer coupons

    static belongsTo = [user: User]
    static hasMany = [detail: PurchaseDetail]

    static constraints = {
        base_price(blank: false, nullable: false, default: 0)
        final_price(blank: false, nullable: false, default: 0)
        discount(blank: false, nullable: false, default: 1)
        coupons(blank: false, nullable: false)
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
