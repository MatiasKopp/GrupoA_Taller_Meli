package api.core.grails

import grails.converters.JSON

class ItemController {
    ItemService itemService

    static responseFormats = ['json']

    def items() {

        def categories = itemService.getItems()
        grails.converters.JSON.use('deep')
        render(categories as JSON)

    }

    def item(String id){

        Item item = itemService.getItem(id)

        if(item==null){
            response.status = 404
            render(new ResponseError("item_not_found: "+id) as JSON)
        }

        render(item as JSON)
    }
}
