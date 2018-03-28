package api.core.grails

import grails.gorm.transactions.Transactional

import java.lang.reflect.InvocationTargetException

import static Function.log

@Transactional
class ItemService {

    private static final String ITEMS_URL = "https://api.mercadolibre.com/sites/MLA/search?category=%s"
    private static final String ITEM_URL = "https://api.mercadolibre.com/items/%s"
    private static final String ITEM_DESCRIPTION_URL = "https://api.mercadolibre.com/items/%s/description"

    private static final Integer MAX_ITEM = 20

    UserService userService
    StatsService statsService

    ArrayList<Item> getItems() {

        ArrayList<Item> items = Item.findAll([max: MAX_ITEM])
        if (items.size() == 0) {
            items = this.populateDataBase()
        }
        return items
    }

    Item getItem(String id) {

        Item item = Item.findById(id)

        if (item != null) {
            item.query_count++
            item.save(flush: true, failOnError: true)
        }


        return item
    }

    ArrayList<Item> getItemsByCategoryId(String categoryId) {
        Category category = Category.findById(categoryId)

        if (category == null) {
            return null
        }

        ArrayList<Item> items = Item.findAllByCategory(category)
        if (items.size() == 0) {
            items = populateDataBaseByCategory(category)
        }
        return items
    }

    boolean setItemVisited(String userId) {

        User user = userService.getUser(userId)

        if (user == null) {
            return false
        }
        log(user.id.toString())
        statsService.setItemVisited(user)

        return true

    }

    ArrayList<Item> search(String[] categories_id, String title) {

        ArrayList<Category> categories = new ArrayList<Category>()

        for (categoryId in categories_id) {
            Category cat = Category.findById(categoryId)

            if (cat != null) {
                categories.add(cat)
            }
        }
        title = "%" + title + "%"
        return this.findByCategoriesAndTitle(categories, title)

    }

    ArrayList<Item> search(String[] categories_id) {

        ArrayList<Category> categories = new ArrayList<Category>()
        if (categories != null) {
            for (categoryId in categories_id) {
                Category cat = Category.findById(categoryId)

                if (cat != null) {
                    categories.add(cat)
                }
            }
        }

        return this.findByCategories(categories)

    }

    ArrayList<Item> search(String title) {
        title = "%" + title + "%"
        return this.findByTitle(title)

    }

    ArrayList<Item> findByCategoriesAndTitle(ArrayList<Category> categories, String title) {
        return Item.findAllByCategoryInListAndTitleIlike(categories, title, [max: MAX_ITEM])
    }

    ArrayList<Item> findByCategories(ArrayList<Category> categories) {
        return Item.findAllByCategoryInList(categories, [max: MAX_ITEM])
    }

    ArrayList<Item> findByTitle(String title) {

        return Item.findAllByTitleLike(title, [max: MAX_ITEM])
    }


    ArrayList<Item> populateDataBase() {

        ArrayList<Item> items = new ArrayList<Item>()
        def cantItems = 0
        Category.getAll().each { cat ->
            def items_json = JsonUtil.getJsonFromUrl(String.format(ITEMS_URL, cat.id))
            int item_count = 0
            for (i in items_json.results) {
                cantItems++

                Item item = new Item()
                item.id = i.id
                item.title = i.title


                try {
                    def description = JsonUtil.getJsonFromUrl(String.format(ITEM_DESCRIPTION_URL, i.id))

                    item.description = description.plain_text != "" ? description.plain_text : "-"
                } catch (Exception e) {
                    item.description = "-"
                }

                item.city = i.seller_address.city.name
                item.country = i.seller_address.country.name
                item.state = i.seller_address.state.name

                def item_json = JsonUtil.getJsonFromUrl(String.format(ITEM_URL, i.id))

                item.latitude = item_json.geolocation.latitude
                item.longitude = item_json.geolocation.longitude



                item.date_created = Date.parse("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", item_json.date_created)
                item.last_updated = Date.parse("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", item_json.last_updated)

                item.category = cat

                try {
                    item.price = Double.parseDouble(item_json.price.toString())
                } catch (Exception e) {
                    item.price = 0
                }

                Random random = new Random()
                //devuelve un numero entre 1 y 10
                item.weight = random.nextInt(9) + 1
                item.query_count = 0
                item.bought_count = 0

                item.initial_quantity = item_json.initial_quantity
                item.available_quantity = item_json.available_quantity


                for (p in item_json.pictures) {
                    Picture pic = new Picture()
                    pic.url = p.url
                    pic.size = p.size
                    item.addToPictures(pic)
                }

                items.add(item)

                item_count++

                log(i.id)
                log(cantItems.toString())
                if (item_count == MAX_ITEM) {
                    log("Time to cut")
                    break
                }


            }
        }

        def itemsNotSaved = 0
        for (i in items) {

            log(i.id)

            try {
                i.save(flush: true, failOnError: true)
            } catch (Exception e) {
                itemsNotSaved++
            }

        }

        log("Cantidad de Items no grabados: " + itemsNotSaved.toString())

        return items
    }

    ArrayList<Item> populateDataBaseByCategory(Category category) {

        ArrayList<Item> items = new ArrayList<Item>()
        def cantItems = 0

        def items_json = JsonUtil.getJsonFromUrl(String.format(ITEMS_URL, category.id))
        int item_count = 0
        for (i in items_json.results) {
            cantItems++

            Item item = new Item()
            item.id = i.id
            item.title = i.title


            try {
                def description = JsonUtil.getJsonFromUrl(String.format(ITEM_DESCRIPTION_URL, i.id))

                item.description = description.plain_text != "" ? description.plain_text : "-"
            } catch (Exception e) {
                item.description = "-"
            }

            item.city = i.seller_address.city.name
            item.country = i.seller_address.country.name
            item.state = i.seller_address.state.name

            def item_json = JsonUtil.getJsonFromUrl(String.format(ITEM_URL, i.id))

            item.latitude = item_json.geolocation.latitude
            item.longitude = item_json.geolocation.longitude



            item.date_created = Date.parse("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", item_json.date_created)
            item.last_updated = Date.parse("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", item_json.last_updated)

            item.category = category

            try {
                item.price = Double.parseDouble(item_json.price.toString())
            } catch (Exception e) {
                item.price = 0
            }

            Random random = new Random()
            //devuelve un numero entre 1 y 10
            item.weight = random.nextInt(9) + 1
            item.query_count = 0
            item.bought_count = 0

            item.initial_quantity = item_json.initial_quantity
            item.available_quantity = item_json.available_quantity


            for (p in item_json.pictures) {
                Picture pic = new Picture()
                pic.url = p.url
                pic.size = p.size
                item.addToPictures(pic)
            }

            items.add(item)

            item_count++

            log(i.id)
            log(cantItems.toString())
            if (item_count == MAX_ITEM) {
                log("Time to cut")
                break
            }


        }


        def itemsNotSaved = 0
        for (i in items) {

            log(i.id)

            try {
                i.save(flush: true, failOnError: true)
            } catch (Exception e) {
                itemsNotSaved++
            }

        }

        log("Cantidad de Items no grabados: " + itemsNotSaved.toString())

        return items
    }

    void buyItem(String id, Integer quantity) {

        Item item = Item.findById(id)

        if (item != null) {
            Integer newQuantity = item.bought_count + quantity
            System.out.println("new quantity: " + newQuantity)
            item.bought_count = newQuantity
            item.save(flush: true, failOnError: true)
        }

    }

    /* carrito */

    def Object getInfoItem(String itemId) {
        Item it = getItem(itemId)
    }

    def ArrayList armarListaItemCarrito(List<Item> lista){
        def listFormat = []
        for(Item item : lista){
            //listFormat << [idItem: item.id, cantidad: 1, precio: item.price, imagen: item.pictures, nombre: item.title]
            listFormat << [idItem: item.id, cantidad: 1, precio: item.price, nombre: item.title]
        }
        return listFormat
    }

}
