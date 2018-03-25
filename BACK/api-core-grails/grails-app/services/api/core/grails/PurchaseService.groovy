package api.core.grails

import grails.gorm.transactions.Transactional

@Transactional
class PurchaseService {

    Purchase getPurchase(String id){

        Purchase purchase = Purchase.findById(id)

        return purchase
    }

    void save(Purchase purchase){
        addCoupons(purchase.user, purchase.coupons)
        if(purchase.detail != null){
            //List<PurchaseDetail> purchaseDetails = purchase.detail
            for (PurchaseDetail purchaseDetail in purchase.detail){
                purchaseDetail.setPurchase(purchase)
                purchaseDetail.save()
            }
        }
        purchase.save()
    }

    void addCoupons(User user, int coupons){
        user.setCoupons(user.coupons + coupons)
        user.save()
    }

}
