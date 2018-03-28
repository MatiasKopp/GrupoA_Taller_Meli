package api.core.grails

import grails.gorm.transactions.Transactional

import static Function.log

@Transactional
class CommentService {

    static final Integer DEFAULT_COMMENT_LIMIT=20
    static final Integer MAX_COMMENT_LIMIT=100

    UserService userService

    ArrayList<Comment> getComments(Integer limit){

        return Comment.findAll([max: limit>0 && limit<MAX_COMMENT_LIMIT ? limit :  DEFAULT_COMMENT_LIMIT, sort: "date_created", order: "desc"])

    }

    Comment save(Comment comment){

        User user = userService.getUser(comment.user.id.toString())

        if(user==null){
            return null
        }

        Comment newComment = new Comment()
        newComment.description = comment.description
        newComment.date_created = new Date()
        newComment.user = user

        return newComment.save(flush: true, failOnError: true)

    }
}
