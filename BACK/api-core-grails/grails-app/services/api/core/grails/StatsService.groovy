package api.core.grails

import grails.gorm.transactions.Transactional

@Transactional
class StatsService {

    def setItemVisited(User user){

        Stats stats = Stats.findByUser(user)

        if(stats==null){

            stats = new Stats()
            stats.user = user
            stats.visit_items = 1
            stats.bought_items = 0
            stats.save(flush:true, failOnError: true)

        } else{

            stats.visit_items++
            stats.save(flush:true, failOnError: true)

        }

    }

    def setItemBought(User user){

        Stats stats = Stats.findByUser(user)

        if(stats==null){

            stats = new Stats()
            stats.user = user
            stats.visit_items = 0
            stats.bought_items = 1
            stats.save(flush:true, failOnError: true)

        } else{

            stats.bought_items++
            stats.save(flush:true, failOnError: true)

        }

    }



}
