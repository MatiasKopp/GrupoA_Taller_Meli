package api.core.grails

import grails.gorm.transactions.Transactional
//import org.springframework.transaction.annotation.Transactional

@Transactional
class CategoryService {

    private static final String CATEGORIES_URL = "https://api.mercadolibre.com/sites/MLA/categories"
    private static final String CATEGORY_URL = "https://api.mercadolibre.com/categories/%s"

    ArrayList<Category> getCategoriesFromServer(){

        def categories_json = JsonUtil.getJsonFromUrl(CATEGORIES_URL)

        ArrayList<Category> categories = new ArrayList<Category>()

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

    Category getCategory(String id){


        Category category = null;



        return category;
    }
}
