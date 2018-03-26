package api.core.grails

import grails.gorm.transactions.Transactional

@Transactional
class PurchaseService {

    ItemService itemService
    UserService userService
    StatsService statsService

    Purchase getPurchase(String id){

        Purchase purchase = Purchase.findById(id)

        return purchase
    }

    void save(Purchase purchase){

        purchase.user = userService.getUser(purchase.user.id.toString())

        if(purchase.details != null){

            for (detail in purchase.details){

                PurchaseDetail purchaseDetail = new PurchaseDetail()
                purchaseDetail.quantity = detail.quantity
                purchaseDetail.item = itemService.getItem(detail.item.id)

                //purchase.addToDetails(purchaseDetail)
            }
        }

        purchase.save(flush:true, failOnError: true)

        addCoupons(purchase.user, purchase.coupons)

        statsService.setItemBought(purchase.user)
    }

    void addCoupons(User user, int coupons){
        user.setCoupons(user.coupons + coupons)
        user.save(flush: true, failOnError: true)
    }

}
