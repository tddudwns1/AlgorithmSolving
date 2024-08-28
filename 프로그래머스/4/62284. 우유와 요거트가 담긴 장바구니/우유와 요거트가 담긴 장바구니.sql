SELECT
    CART_ID
from
    (
    SELECT
        CART_ID
    from
        CART_PRODUCTS 
    where
        NAME in ('Milk', 'Yogurt')
    group by
        CART_ID, NAME
    ) s
group by
    CART_ID
having
    count(*) > 1
order by
    CART_ID