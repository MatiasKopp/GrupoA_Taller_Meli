package api.core.grails

import grails.converters.JSON
import java.lang.reflect.InvocationTargetException

import static Function.log

class ItemController {
    ItemService itemService

    static responseFormats = ['json']

    def items() {

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

        //Visita  de un usuario
        if(params.user_id!=null){
            itemService.setItemVisited(params.user_id)
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
