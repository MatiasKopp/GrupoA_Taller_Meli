package api.core.grails

import grails.gorm.transactions.Transactional
import static Function.log
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

    Map<String, Double> getAllStats(){
        def stats = [:]
        def criteria = Stats.createCriteria()
        def stats_criterias = criteria.list{
            projections {
                sum('visit_items')
                sum('bought_items')
                count('user')
            }
        }

        stats['visit_items'] = stats_criterias[0][0]!=null ? stats_criterias[0][0] : 0
        stats['bought_items'] = stats_criterias[0][1]!=null ? stats_criterias[0][1] : 0
        stats['cant_users'] = stats_criterias[0][2]

        return stats

    }



}
