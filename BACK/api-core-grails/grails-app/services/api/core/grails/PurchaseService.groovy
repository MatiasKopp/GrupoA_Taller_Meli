package api.core.grails

import grails.gorm.transactions.Transactional

@Transactional
class PurchaseService {

    ItemService itemService
    UserService userService
    StatsService statsService

    Purchase getPurchase(String id) {

        Purchase purchase = Purchase.findById(id)

        return purchase
    }

    void save(Purchase purchase) {

        purchase.user = userService.getUser(purchase.user.id.toString())

        if (purchase.details != null) {

            for (detail in purchase.details) {
                String id = detail.item.id
                Integer quantity = detail.quantity

                PurchaseDetail purchaseDetail = new PurchaseDetail()
                purchaseDetail.quantity = quantity
                purchaseDetail.item = itemService.getItem(id)
                itemService.buyItem(id, quantity)
                //System.out.println("id: " + id)
                //System.out.println("quantity: " + quantity)
                //purchase.addToDetails(purchaseDetail)
            }
        }

        purchase.save(flush: true, failOnError: true)

        addCoupons(purchase.user, purchase.coupons)

        statsService.setItemBought(purchase.user)
    }

    void addCoupons(User user, int coupons) {
        user.setCoupons(user.coupons + coupons)
        user.save(flush: true, failOnError: true)
    }

}
