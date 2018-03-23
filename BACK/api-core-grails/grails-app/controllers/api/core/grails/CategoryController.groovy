package api.core.grails

import grails.converters.JSON
import static Function.log;

class CategoryController {

    CategoryService categoryService

    static responseFormats = ['json']

    def categories() {

        def categories = categoryService.getCategoriesFromServer()
        grails.converters.JSON.use('deep')
        render(categories as JSON)

    }

    def category(String id){
        //log(id)
        Category cat = categoryService.getCategory(id)

        if(cat==null){
            response.status = 404
            render(new ResponseError("category_not_found: "+id) as JSON)
        }

        render(cat as JSON)
    }
}
