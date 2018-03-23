package api.core.grails

import grails.gorm.transactions.Transactional

@Transactional
class ItemService {

    private static final String ITEMS_URL = "https://api.mercadolibre.com/sites/MLA/search?category=%s"
    private static final String ITEM_URL = "https://api.mercadolibre.com/items/%s"
    private static final String ITEM_DESCRIPTION_URL = "https://api.mercadolibre.com/items/%s/description"

    ArrayList<Category> getItems(){

        ArrayList<Item> items = Item.getAll()
        if(items.size()==0){
            items = this.populateDataBase()
        }
        return items
    }

    Category getItem(String id){

        Item item = Item.findById(id)

        return item
    }

    def populateDataBase(){

        Category.getAll().each{ cat ->
            def items_json = JsonUtil.getJsonFromUrl(String.format(ITEMS_URL, cat.id))
            items_json.each{ i ->
                Item item = new Item()
                item.id = i.id
                item.title = i.title
                item.initial_quantity = i.initial_quantity
                item.available_quantity = i.available_quantity
                item.description = JsonUtil.getJsonFromUrl(String.format(ITEM_DESCRIPTION_URL, cat.id)).plain_text
                item.city = i.seller_address.city.name
                item.country = i.seller_address.country.name
                item.state = i.seller_address.state.name

                def item_json = JsonUtil.getJsonFromUrl(String.format(ITEM_URL, i.id))

                item.latitude = item_json.geolocation.latitude
                item.longitude = item_json.geolocation.longitude

                item.date_created = Date.parse("yyyy.MM.dd'T'HH:mm:ss'.000z'", item_json.date_created)
                item.last_updated = Date.parse("yyyy.MM.dd'T'HH:mm:ss'.000z'", item_json.last_updated)

                item_json.pictures.each{
                    
                }

            }
        }

        ArrayList<Item> items = new ArrayList<Item>()

        categories_json.each{c ->
            Category cat = new Category()
            cat.id = c.id
            cat.name = c.name
            def category_json = JsonUtil.getJsonFromUrl(String.format(CATEGORY_URL, cat.id))
            cat.picture = category_json.picture
            cat.total_items_in_this_category = category_json.total_items_in_this_category
            cat.save()
            categories.add(cat)
        }

        return categories
    }
}
