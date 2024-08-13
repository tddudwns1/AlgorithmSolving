SELECT
    f.CATEGORY,
    f.PRICE as MAX_PRICE,
    PRODUCT_NAME
from
    FOOD_PRODUCT f
    join (
        select
            max(price) as PRICE,
            category
        from
            FOOD_PRODUCT
        where
            CATEGORY in('과자', '국', '김치', '식용유')
        group by
            CATEGORY
    ) m ON f.CATEGORY = m.CATEGORY AND f.PRICE = m.PRICE
where
    f.CATEGORY in('과자', '국', '김치', '식용유')
group by
    f.CATEGORY
order by
    MAX_PRICE desc