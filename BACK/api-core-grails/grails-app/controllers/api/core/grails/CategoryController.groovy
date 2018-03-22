package api.core.grails

import grails.converters.JSON

class CategoryController {

    CategoryService categoryService

    static responseFormats = ['json']

    def categories() {

        def categories = categoryService.getCategoriesFromServer()
        grails.converters.JSON.use('deep')
        render(categories as JSON)

    }

    def category(){

    }
}
