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

    def searchItems(){

        String[] categories = null
        String title=null

        if(params.categories!=null){
            try{
                categories = params.categories.split(',')
            }catch(Exception e){
                render(new ResponseError("malformed_parameters") as JSON)
            }
        }


        if(params.title!=null){
            title = params.title
        }

        if(categories==null && title==null){
            render(new ResponseError("missing_parameters") as JSON)
        }

        ArrayList<Item> items = new ArrayList<Item>()

        if(categories!=null && title!=null){
            items = itemService.search(categories, title)
        } else if(categories!=null){
            items = itemService.search(categories)
        } else {
            items = itemService.search(title)
        }

        render((items!=null ? items : []) as JSON)

    }
}
