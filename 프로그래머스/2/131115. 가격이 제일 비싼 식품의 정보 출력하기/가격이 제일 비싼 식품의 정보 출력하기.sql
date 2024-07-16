SELECT
    f.product_id as product_id,
    f.product_name as product_name,
    f.product_cd as product_cd,
    f.category as category,
    f.price as price
from food_product f
order by price desc
limit 1;