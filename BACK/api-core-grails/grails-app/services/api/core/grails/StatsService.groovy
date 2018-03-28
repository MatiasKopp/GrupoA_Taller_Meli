package api.core.grails

import grails.gorm.transactions.Transactional
import static Function.log
@Transactional
class StatsService {

    def setItemVisited(User user){

        Stats stats = Stats.findByUser(user)

        if(stats==null){

            stats = this.newUserStas(user)

        }

        stats.visit_items++
        stats.save(flush:true, failOnError: true)



    }

    def setItemBought(User user){

        Stats stats = Stats.findByUser(user)

        if(stats==null){

            stats = this.newUserStas(user)

        }

        stats.bought_items++
        stats.save(flush:true, failOnError: true)



    }

    Map<String, Double> getTotalStats(){
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

    ArrayList<Stats> getAllStats(){
        ArrayList<Stats> stats = new ArrayList<Stats>()
        stats = Stats.getAll()

        return stats
    }


    Stats getUserStas(User user){
        Stats stats = Stats.findByUser(user)

        if(stats==null){

            stats = this.newUserStas(user)

        }

        return stats
    }

    Stats newUserStas(User user){
        Stats stats = new Stats()
        stats.user = user
        stats.visit_items = 0
        stats.bought_items = 0
        stats.save(flush:true, failOnError: true)
        return stats
    }



}
