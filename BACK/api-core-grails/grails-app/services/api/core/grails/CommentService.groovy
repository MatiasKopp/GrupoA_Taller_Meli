package api.core.grails

import grails.gorm.transactions.Transactional

@Transactional
class CommentService {

    static final int DEFAULT_COMMENT_LIMIT= 20

    ArrayList<Comment> getComments(){
        Comment.findAll("FROM comments", [max: DEFAULT_COMMENT_LIMIT])
    }

    Comment save(Comment comment){

    }
}
