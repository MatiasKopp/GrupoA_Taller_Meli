package api.core.grails

import grails.converters.JSON

class CommentController {

    CommentService commentService

    def getComments(){
        Integer limit

        try{
            limit = Integer.parseInt(params.limit)
        } catch(Exception e){
            limit = 0
        }

        render(this.commentService.getComments(limit) as JSON)
    }

    def postComment(Comment comment){

        if(comment==null){
            response.status = 400
            render(new ResponseError("missing_parameters") as JSON)
        }

        if(comment.user.id==null){
            response.status = 404
            render(new ResponseError("user_not_found") as JSON)
        }

        Comment savedComment = this.commentService.save(comment)

        if(savedComment==null){
            response.status = 400
            render(new ResponseError("invalid_parameters") as JSON)
        }


        render(savedComment as JSON)

    }
}
