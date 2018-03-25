package api.core.grails

import grails.converters.JSON

import java.lang.reflect.InvocationTargetException

class ItemController {
    ItemService itemService

    static responseFormats = ['json']

    def items() {

        /*Item item = new Item()

        item.id = "MLA3332"
        item.title = "Esta"
        item.initial_quantity = 3
        item.available_quantity = 123


        item.description = "asdasd"


        item.city = "Cba"
        item.country = "Arg"
        item.state = "Cordoba"

        item.latitude = "1"
        item.longitude = "2"

        item.date_created = Date.parse("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "2017-09-14T15:54:09.000Z")
        item.last_updated = Date.parse("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "2017-09-14T15:54:09.000Z")
        item.pictures = new ArrayList<Picture>()

        item.price = 123
        item.weight = 5
        item.query_count = 1

        item.category = Category.findById("MLA1000")

        def item_saved = item.save(flush: true, failOnError: true)



        grails.converters.JSON.use('deep')
        render(item_saved as JSON)*/
        def items = itemService.getItems()
        grails.converters.JSON.use('deep')
        render(items as JSON)

    }

    def item(String id){

        Item item = itemService.getItem(id)

        if(item==null){
            response.status = 404
            render(new ResponseError("item_not_found: "+id) as JSON)
        }
        grails.converters.JSON.use('deep')
        render(item as JSON)
    }

    def itemsByCategory(){
        def items = itemService.getItemsByCategoryId(params.id)
        grails.converters.JSON.use('deep')
        render(items as JSON)
    }
}
