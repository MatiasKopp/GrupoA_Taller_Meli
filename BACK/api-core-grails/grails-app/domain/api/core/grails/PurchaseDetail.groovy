package api.core.grails

class PurchaseDetail {

    Integer quantity

    static belongsTo = [purchase: Purchase, item: Item]

    static constraints = {
        quantity(blank: false, nullable: false, default: 0)
    }
/*
    static mapping = {
        table 'PurchaseDetails'
        id column: 'purchase_detail_id', sqlType: 'int'
        quantity column: 'quantity', sqlType: 'int'

        version false
    }
    */
}
