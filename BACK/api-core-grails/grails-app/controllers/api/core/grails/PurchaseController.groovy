package api.core.grails

import grails.converters.JSON

class PurchaseController {


    static responseFormats = ['json']

    PurchaseService purchaseService

    def purchase(String id){
        //log(id)
        Purchase purchase = purchaseService.getPurchase(id)

        if(purchase==null){
            response.status = 404
            render(new ResponseError("purchase_not_found: "+id) as JSON)
        }

        render(purchase as JSON)
    }

    def add(Purchase purchase){
        try{
            purchaseService.save(purchase)
            render(purchase as JSON)
        }catch(Exception ex){
            ex.printStackTrace()
        }
    }

}
