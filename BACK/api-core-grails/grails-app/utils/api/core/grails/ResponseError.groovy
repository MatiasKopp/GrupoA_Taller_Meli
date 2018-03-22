package api.core.grails;

 class ResponseError {

    private String message;

     ResponseError(String message, String... args){
        this.message = String.format(message,args);
    }

     ResponseError(Exception e){
        this.message = e.getMessage();
    }

     String getMessage(){
        return this.message;
    }
}
