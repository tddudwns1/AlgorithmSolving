SELECT
    p.PRODUCT_ID as PRODUCT_ID,
    PRODUCT_NAME as PRODUCT_NAME,
    sum(AMOUNT) * PRICE as TOTAL_SALES
from
    FOOD_PRODUCT p
    join FOOD_ORDER o using(PRODUCT_ID)
where
    PRODUCE_DATE between "2022-05-01" and "2022-05-31"
group by
    p.PRODUCT_ID
order by
    TOTAL_SALES desc, p.PRODUCT_ID